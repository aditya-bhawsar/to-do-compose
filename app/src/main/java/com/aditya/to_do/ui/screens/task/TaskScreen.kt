package com.aditya.to_do.ui.screens.task

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import com.aditya.to_do.data.models.Priority
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.ui.viewmodels.SharedViewModel
import com.aditya.to_do.util.Action

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask?
) {
    val title: String by sharedViewModel.title
    val desc: String by sharedViewModel.desc
    val priority: Priority by sharedViewModel.priority

    val ctx = LocalContext.current

    BackHandler {
        navigateToListScreen(Action.NO_ACTION)
    }

    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = { action ->
                    if (action == Action.NO_ACTION) {
                        navigateToListScreen(action)
                    } else {
                        if (sharedViewModel.validateFields()) {
                            navigateToListScreen(action)
                        } else {
                            displayToast(ctx = ctx)
                        }
                    }
                },
                selectedTask = selectedTask
            )
        },
        content = {
            TaskContent(
                title = title,
                onTitleChanged = {
                    sharedViewModel.updateTitle(it)
                },
                description = desc,
                onDescriptionChanged = {
                    sharedViewModel.desc.value = it
                },
                priority = priority,
                onPrioritySelected = {
                    sharedViewModel.priority.value = it
                }
            )
        }
    )
}

fun displayToast(ctx: Context) {
    Toast.makeText(ctx, "Fields can't be Empty", Toast.LENGTH_LONG).show()
}
