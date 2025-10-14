package com.example.sautiyamilimani_test1.presentation.pages.admins

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.LottieConstants
import com.example.sautiyamilimani_test1.R
import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Status
import com.example.sautiyamilimani_test1.presentation.components.AddMemberDialog
import com.example.sautiyamilimani_test1.presentation.pages.auth.TabItem
import com.example.sautiyamilimani_test1.presentation.viewmodels.ActionState
import com.example.sautiyamilimani_test1.presentation.viewmodels.MembersListState
import com.example.sautiyamilimani_test1.presentation.viewmodels.MembersViewModel
import com.example.sautiyamilimani_test1.ui.theme.Yellow100
import com.example.sautiyamilimani_test1.ui.theme.Yellow200
import com.example.sautiyamilimani_test1.ui.theme.Yellow800
import com.example.sautiyamilimani_test1.ui.theme.green50
import com.example.sautiyamilimani_test1.ui.theme.mutedForeground

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MemberManagementPage(navController: NavController,membersViewModel: MembersViewModel) {

    val actionState by membersViewModel.recentActionState.collectAsState()

    val membersState by membersViewModel.membersListState.collectAsState()

    val snackBarHostState = remember { SnackbarHostState() }

    val composition by rememberLottieComposition(spec= LottieCompositionSpec.RawRes(R.raw.no_result_found))

    val progress by animateLottieCompositionAsState(composition,iterations=LottieConstants.IterateForever)
    val colors = MaterialTheme.colorScheme

    var membersCount by remember {
        mutableIntStateOf(0)
    }
    var selectedTab by remember {
        mutableIntStateOf(0)

    }
    var isAdding by remember {
        mutableStateOf(false)
    }

    var isEditing by remember {
        mutableStateOf(false)
    }
    var beingEdited by remember {
        mutableStateOf<Member?>(null)
    }


    val filterChips = listOf<TabItem>(
        TabItem("All Members   [$membersCount]"),
        TabItem("Recent Activity")
    )

    var showAddDialog by remember {
        mutableStateOf(false)
    }


    LaunchedEffect(
        actionState
    ) {
        when (actionState) {
            is ActionState.Added -> snackBarHostState.showSnackbar(
                message = "${(actionState as ActionState.Added).member.fullName} was added! "
            )

            is ActionState.Deleted -> snackBarHostState.showSnackbar(
                message = "${(actionState as ActionState.Deleted).member?.fullName} was deleted! "
            )

            is ActionState.Edited -> snackBarHostState.showSnackbar(
                message = "${(actionState as ActionState.Edited).member.fullName} has been Edited! "
            )

            is ActionState.HasError -> snackBarHostState.showSnackbar(
                message = "${((actionState as ActionState.HasError).error)}"
            )

            ActionState.Loading -> {}
        }
    }






    Scaffold(

        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }, modifier = Modifier.weight(.1f)) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
                Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.Center) {
                    Text("Members")
                }

            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    isAdding = true
                },

            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Member",
                )
            }
        }
    ) { innerPadding ->
        //Main Screen
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {
            //Tabs for filtering
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = colors.primary.copy(0.05f),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, colors.primary.copy(0.5f), RoundedCornerShape(24.dp))
                    .clip(RoundedCornerShape(24.dp))
            ) {
                filterChips.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTab,
                        selectedContentColor = colors.surface,
                        unselectedContentColor = colors.mutedForeground,
                        onClick = { selectedTab = index },
                        modifier = Modifier
                            .weight(1f)
                            .height(36.dp)
                            .background(color = if (selectedTab == index) colors.surface else Color.Transparent),
                        text = {
                            Text(
                                item.title,
                                color = if (selectedTab == index)
                                    colors.onSurface
                                else
                                    colors.primary,
                                modifier = Modifier.padding(vertical = 4.dp)

                            )
                        }
                    )

                }
            }
            Spacer(Modifier.height(24.dp))



            if (isAdding || isEditing) {
                AddMemberDialog(
                    editingMember = if (isEditing) beingEdited else null,
                    onDismis = {
                        isAdding = false
                        isEditing = false
                    },
                    onAdd = { membersViewModel.addMember(it) },
                    onEditComplete = {
                        membersViewModel.editMember(it)
                        beingEdited = null
                        isEditing = false
                    }
                )
            }



            when (membersState) {
                is MembersListState.HasError -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = (membersState as MembersListState.HasError).error
                                ?: "Unknown error"
                        )
                    }
                }

                MembersListState.IsLoading -> {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is MembersListState.WasSuccessful -> {
                    val members = (membersState as MembersListState.WasSuccessful).members
                        ?: emptyList()

                    membersCount = members.count()

                    if (members.isEmpty()) {
                        Column(Modifier.fillMaxSize(), horizontalAlignment=Alignment.CenterHorizontally,
                            verticalArrangement=Arrangement.Center) {
                            LottieAnimation(
                                composition = composition,
                                progress={progress},
                                modifier = Modifier.size(300.dp)
                            )

                            Spacer(Modifier.height(8.dp))
                            Text("No Data Found")
                        }
                    }
                    LazyColumn {


                        items(
                            members
                        ) { member ->
                            MemberCard(
                                member,
                                onDelete = { memberId ->
                                    membersViewModel.deleteMember(memberId)
                                },
                                onStartEdit = {
                                    beingEdited = member
                                    isEditing = true
                                }
                            )
                            Spacer(Modifier.height(8.dp))
                        }


                    }

                }
            }
        }


    }


}

//MemberDisplayCard
@Composable
fun MemberCard(member: Member, onDelete: (Int?) -> Unit, onStartEdit: () -> Unit) {
    var showActionsDialog by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface
            )
            .border(1.dp, MaterialTheme.colorScheme.primary.copy(.3f), RoundedCornerShape(8.dp))
            .padding(12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {

        //Initials Circle
        Column(modifier = Modifier) {
            Text(
                getInitials(member), modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        MaterialTheme.colorScheme.primary.copy(.3f)
                    )
                    .padding(12.dp)

            )
        }

        //Content Holder
        Column() {

            //Name,Status,percentage
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                //Name
                Text(
                    text = member.fullName,
                    modifier = Modifier,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )

                //Dot
                Spacer(
                    Modifier
                        .size(4.dp)
                        .background(
                            color = when (member.status) {
                                Status.ACTIVE -> MaterialTheme.colorScheme.green50
                                Status.INACTIVE -> MaterialTheme.colorScheme.error
                                Status.PENDING -> Yellow800
                            },
                            CircleShape
                        )
                )

                //Badge(Status indicator)
                Text(
                    text = when (member.status) {
                        Status.ACTIVE -> "Active"
                        Status.INACTIVE -> "Inactive"
                        Status.PENDING -> "Pending"
                    },

                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            color = when (member.status) {
                                Status.ACTIVE -> MaterialTheme.colorScheme.green50.copy(.2f)
                                Status.INACTIVE -> MaterialTheme.colorScheme.error
                                Status.PENDING -> Yellow100
                            }
                        )
                        .border(
                            width = 1.dp,
                            color = when (member.status) {
                                Status.ACTIVE -> MaterialTheme.colorScheme.green50
                                Status.INACTIVE -> MaterialTheme.colorScheme.error
                                Status.PENDING -> Yellow200
                            },
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = when (member.status) {
                        Status.ACTIVE -> MaterialTheme.colorScheme.green50
                        Status.INACTIVE -> Color.White
                        Status.PENDING -> Yellow800
                    },

                    fontWeight = FontWeight.Bold


                )

                //Dot
                Spacer(
                    Modifier
                        .size(4.dp)
                        .background(MaterialTheme.colorScheme.green50, CircleShape)
                )

                //Percentage
                Text(
                    text = "${member.attendanceRate.toString()} %", modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            MaterialTheme.colorScheme.green50.copy(.2f)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.green50.copy(1f),
                    fontWeight = FontWeight.Bold


                )



                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    //more button
                    IconButton(
                        onClick = {
                            showActionsDialog = true
                            Log.d("Member Id", "Clicked : ${member.id}")
                        }
                    ) {
                        Icon(
                            Icons.Default.MoreVert,
                            "more",
                        )


                    }


                }

                Column(
                    modifier = Modifier.wrapContentSize()
                ) {

                    DropdownMenu(
                        modifier = Modifier.width(48.dp),
                        expanded = showActionsDialog,
                        onDismissRequest = {
                            showActionsDialog = false
                        }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Icon(Icons.Outlined.Edit, "Edit")
                            },
                            onClick = {
                                onStartEdit()
                                showActionsDialog = false
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Icon(Icons.Outlined.Delete, "Edit")
                            },
                            onClick = {
                                onDelete(member.id)
                                showActionsDialog = false
                            }
                        )


                    }
                }


            }

            //Space for second row
            Spacer(Modifier.height(8.dp))

            //Bottom Row(Phone,email)
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,

                ) {
                items(1) {

                    //Phone and its icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(Icons.Outlined.Phone, "Email", Modifier.size(12.dp))
                        Text(
                            text = member.phone1 ?: "Unknown",
                            style = MaterialTheme.typography.labelSmall,
                        )

                    }

                    //horizontal space
                    Spacer(Modifier.width(8.dp))

                    //Email and its icon
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Email,
                            contentDescription = member.email,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = member.email ?: "unknown",
                            style = MaterialTheme.typography.labelSmall,
                        )


                    }

                }
            }
        }
    }

}

private fun ColumnScope.getInitials(member: Member): String {
    var initials: String = "??"
    try {
        val names: List<String> = member.fullName.trim().split(" ")
        initials = if (names.isEmpty()) {
            "??"
        } else {
            ((names[0].trim()[0]).toString().trim() + (names[1].trim()[0]).toString()
                .trim()).toUpperCase()
        }
        Log.d("Initials", initials)
    } catch (e: Exception) {
        Log.d("Error", "Empty")
    }
    return initials
}

