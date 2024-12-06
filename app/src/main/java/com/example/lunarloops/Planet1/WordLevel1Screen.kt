package com.example.lunarloops.Planet1

import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize

internal val LocalDraggableItemInfo = compositionLocalOf { DraggableItemInfo() }

@Composable
fun GameScreen(content:@Composable BoxScope.() -> Unit){
    val state = remember { DraggableItemInfo() }

    CompositionLocalProvider(LocalDraggableItemInfo provides state) {
        Box(modifier = Modifier.fillMaxSize()){
            content()

            if(state.isDragging){
                var targetSize by remember { mutableStateOf(IntSize.Zero) }

                Box(modifier = Modifier.graphicsLayer {
                    val offset = (state.dragStartOffset + state.dragCurrentOffset)
                    scaleX = 1.3f
                    scaleY = 1.3f
                    translationX = offset.x.minus(targetSize.width/2)
                    translationY = offset.y.minus(targetSize.width)
                }.onGloballyPositioned {
                    targetSize = it.size
                }){
                    state.draggableComposable?.invoke()
                }
            }
        }
    }
}

@Composable
fun <T> DraggableView(
    modifier: Modifier,
    dataToDrop:T,
    content: @Composable (()->Unit)
){
    var currentPosition by remember { mutableStateOf(Offset.Zero) }
    val currentState = LocalDraggableItemInfo.current

    Box(modifier = modifier
        .onGloballyPositioned { layoutCoordinates ->
            currentPosition = layoutCoordinates.localToWindow(Offset.Zero)
        }
        .pointerInput(Unit){
            detectDragGesturesAfterLongPress(
                onDragStart = { startOffset->
                    currentState.dataToDrop = dataToDrop
                    currentState.isDragging = true
                    currentState.dragStartOffset = currentPosition+startOffset
                    currentState.draggableComposable = content
                },
                onDrag = {change, dragAmount ->
                    change.consume()
                    currentState.dragCurrentOffset += Offset(dragAmount.x, dragAmount.y)
                },
                onDragEnd = {
                    currentState.isDragging = false
                    currentState.dragCurrentOffset = Offset.Zero
                },
                onDragCancel = {
                    currentState.isDragging = false
                    currentState.dragCurrentOffset = Offset.Zero
                }
            )
        }) {
        content()
    }
}

@Composable
fun <T> DropTarget(modifier: Modifier, content:@Composable (BoxScope.(isInBound:Boolean, T?) -> Unit)){
    val dragInfo = LocalDraggableItemInfo.current
    val dragPosition = dragInfo.dragStartOffset
    val dragOffset = dragInfo.dragCurrentOffset

    var isCurrentDropTarget by remember { mutableStateOf(false) }

    Box(modifier = modifier.onGloballyPositioned { layoutCoordinates ->
        layoutCoordinates.boundsInWindow().let { rect: Rect ->
            isCurrentDropTarget = rect.contains(dragPosition+dragOffset)
        }
    }){
        val data = if(isCurrentDropTarget && dragInfo.isDragging) dragInfo.dataToDrop as T? else null
        content(isCurrentDropTarget, data)
    }
}

internal class DraggableItemInfo{
    var isDragging by mutableStateOf(false)
    var dragStartOffset by mutableStateOf(Offset.Zero)
    var dragCurrentOffset by mutableStateOf(Offset.Zero)
    var draggableComposable by mutableStateOf<(@Composable ()-> Unit)?>(null)
    var dataToDrop by mutableStateOf<Any?>(null)
}