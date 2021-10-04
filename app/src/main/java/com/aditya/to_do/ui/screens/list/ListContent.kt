package com.aditya.to_do.ui.screens.list

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.ui.theme.LARGE_PADDING
import com.aditya.to_do.ui.theme.MEDIUM_PADDING
import com.aditya.to_do.ui.theme.PRIORITY_INDICATOR_SIZE
import com.aditya.to_do.ui.theme.TASK_ITEM_ELEVATION
import com.aditya.to_do.ui.theme.taskItemBackgroundColor
import com.aditya.to_do.ui.theme.taskItemTextColor
import com.aditya.to_do.util.RequestState

@ExperimentalMaterialApi
@Composable
fun ListContent(
    toDoTaskList: RequestState<List<ToDoTask>>,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    if(toDoTaskList is RequestState.Success){
        if(toDoTaskList.data.isEmpty()){
            EmptyContent()
        }else{
            DisplayTasks(
                toDoTaskList = toDoTaskList.data,
                navigateToTaskScreen = navigateToTaskScreen
            )
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayTasks(
    toDoTaskList: List<ToDoTask>,
    navigateToTaskScreen: (taskId: Int) -> Unit
){
    LazyColumn{
        items(
            items = toDoTaskList,
            key = { task-> task.id }
        ){ task ->
            TaskItem(toDoTask = task, navigateToTaskScreen = navigateToTaskScreen)
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun TaskItem(
    toDoTask: ToDoTask,
    navigateToTaskScreen: (taskId: Int)-> Unit
){
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.taskItemBackgroundColor,
        shape = RectangleShape,
        elevation = TASK_ITEM_ELEVATION,
        onClick = {
            navigateToTaskScreen(toDoTask.id)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(all = LARGE_PADDING)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    modifier = Modifier.weight(8f),
                    text = toDoTask.title,
                    color = MaterialTheme.colors.taskItemTextColor,
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    contentAlignment = Alignment.TopEnd
                ){
                  Canvas(
                      modifier = Modifier
                          .size(PRIORITY_INDICATOR_SIZE)
                  ) {
                    drawCircle(color = toDoTask.priority.color)
                  }
                }
            }
            Text(
                text = toDoTask.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MEDIUM_PADDING),
                color = MaterialTheme.colors.taskItemTextColor,
                style = MaterialTheme.typography.subtitle1,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}