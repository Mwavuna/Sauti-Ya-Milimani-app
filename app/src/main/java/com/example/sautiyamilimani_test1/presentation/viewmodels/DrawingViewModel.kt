package com.example.sautiyamilimani_test1.presentation.viewmodels

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.PathData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

//Structure of a drawing state
data class DrawingState(
    val selectedColor: Color? = Color.Black,
    val currentPath: PathData? = null,
    val paths: List<PathData?> = emptyList()
)

//Structure of a path
data class PathData(
    val id: String,
    val color: Color?,
    val path: List<Offset>
)

val allColors=listOf(
    Color.Black,
    Color.Red,
    Color.Yellow,
    Color.Green,
    Color.Blue,
    Color.Cyan,
    Color.Magenta
)


//All actions ui can access from viewmodel
sealed interface DrawingAction {
    data class OnSelectedColor(val color: Color) : DrawingAction
    data object OnNewPathStart : DrawingAction
    data class OnDraw(val offset: Offset) : DrawingAction
    data object OnPathEnd : DrawingAction
    data object OnClearCanvas : DrawingAction
}

class DrawingViewModel : ViewModel() {
    private val _state= MutableStateFlow(DrawingState())
    val state=_state.asStateFlow()

    fun onAction(action: DrawingAction){
        when(action) {
            DrawingAction.OnClearCanvas -> onClearCanvas()
            is DrawingAction.OnDraw -> onDraw(action.offset)
            DrawingAction.OnNewPathStart -> onNewPathStart()
            DrawingAction.OnPathEnd -> onPathEnd()
            is DrawingAction.OnSelectedColor -> onSelectedColor(action.color)
        }
    }

    private fun onSelectedColor(color: Color) {
        _state.update { it.copy(selectedColor=color) }
    }

    private fun onPathEnd() {
        val currentPathData=_state.value.currentPath?:return
        _state.update {
            it.copy(
                currentPath = null,
                paths=it.paths+currentPathData

            )
        }
    }

    private fun onNewPathStart() {

        _state.update { it.copy(
            currentPath = PathData(
                id= System.currentTimeMillis().toString(),
                color = it.selectedColor,
                path = emptyList()
            )
        ) }


    }

    private fun onDraw(offset: Offset) {
        val currentPathData=_state.value.currentPath?:return
        _state.update {
            it.copy(
                currentPath=currentPathData.copy(
                    path = currentPathData.path+offset
                )


            )
        }

    }

    private fun onClearCanvas() {
        _state.update { it.copy(
            currentPath = null,
            paths = emptyList()
        ) }

    }


}