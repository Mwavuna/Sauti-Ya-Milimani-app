package com.example.sautiyamilimani_test1.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Resource
import com.example.sautiyamilimani_test1.domain.usecases.Members.MembersUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class MembersViewModel @Inject constructor(
    private val membersUseCases: MembersUseCases
) : ViewModel() {
    private val _membersListState = MutableStateFlow<MembersListState>(MembersListState.IsLoading)
    val membersListState: StateFlow<MembersListState> = _membersListState

    private val _members = MutableStateFlow<List<Member>>(emptyList())
    val members: StateFlow<List<Member>> = _members

    private val _recentActionState = MutableStateFlow<ActionState>(ActionState.Loading)
    val recentActionState: StateFlow<ActionState> = _recentActionState


    val memberCount = _members.map { it.size }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
    /*
    -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    |   Every time _members updates (new list emitted),map transforms that list into just its size.
    |   Every time _members updates (new list emitted),map transforms that list into just its size.
    |   stateIn() converts the Flow from .map { it.size } into a StateFlow —so you can collect it easily in Compose using collectAsState().
    |   You just define the scope of the data,when viewmodel die it dies if its view model scope,
    |
    |   Parameter	                            Meaning
    |   viewModelScope                      	Coroutine scope that manages its lifecycle (auto-cancel on ViewModel clear)
    |   SharingStarted.WhileSubscribed(5000)	It keeps collecting data as long as someone (like the UI) is observing. If everyone stops, it waits 5 seconds before pausing (to save resources).
    |   0                                       Initial value before any emissions — i.e., memberCount starts from 0.
    |-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
     */

    init {
            observeMembers()

    }

    fun observeMembers(){
        viewModelScope.launch {
            membersUseCases.getMembersUseCase() .distinctUntilChanged().collect{
                result->
                when (result) {
                    Resource.Loading -> {
                        _membersListState .value = MembersListState.IsLoading
                    }
                    is Resource.Failure -> {
                        _membersListState .value = MembersListState.HasError(result.message?:"Unknown Error Occurred!")
                    }
                    is Resource.Success -> {
                        val data = result.data ?: emptyList()
                        _membersListState.value = MembersListState.WasSuccessful(data)
                        _members.value = data
                    }
                    else->Unit
                }
            }
        }
    }



    fun addMember(member: Member) {
        viewModelScope.launch {
            membersUseCases.addMembersUseCase(member)
               .collect { result ->
                    when (result) {
                        Resource.Loading -> {
                            _recentActionState.value = ActionState.Loading
                        }

                        is Resource.Failure -> {
                            _recentActionState.value  =
                                ActionState.HasError(result.message ?: "Unknown Error Occurred!")
                        }

                        is Resource.Success-> {
                            _recentActionState.value = ActionState.Added(member)
                        }
                        else->Unit
                    }

                }
        }
    }

    fun editMember(member: Member) {
        viewModelScope.launch {
            membersUseCases.editMembersUseCase(member)
                .collect { result ->
                    when (result) {
                        Resource.Loading -> {
                            _recentActionState.value = ActionState.Loading
                        }

                        is Resource.Failure -> {
                            _recentActionState.value  =
                                ActionState.HasError(result.message ?: "Unknown Error Occurred!")
                        }

                        is Resource.Success-> {
                            _recentActionState.value = ActionState.Edited(member)
                        }
                        else->Unit
                    }
                }
        }
    }

    fun deleteMember(memberId: Int?) {
        viewModelScope.launch {
            membersUseCases.deleteMembersUseCase(memberId)
                .collect { result ->
                    when (result) {
                        Resource.Loading -> {
                            _recentActionState.value = ActionState.Loading
                        }

                        is Resource.Failure -> {
                            _recentActionState.value =
                                ActionState.HasError(result.message ?: "Unknown Error Occurred!")
                        }
                        is Resource.Success-> {
                            val deletedMember=members.value.find { it.id==memberId }
                            deletedMember?.let{
                                _recentActionState.value = ActionState.Deleted(it)
                            }
                        }
                        else->Unit
                    }
                }


        }
    }
}


sealed class MembersListState {
    object IsLoading :  MembersListState ()
    data class WasSuccessful(val members: List<Member>?) :  MembersListState()
    data class HasError(val error: String?) : MembersListState ()

}



sealed class ActionState{
    object Loading:ActionState()
    data class Edited(val member: Member) : ActionState()
    data class Deleted(val member: Member?) : ActionState()
    data class Added(val member: Member) : ActionState()
    data class HasError(val error: String?) : ActionState()
}