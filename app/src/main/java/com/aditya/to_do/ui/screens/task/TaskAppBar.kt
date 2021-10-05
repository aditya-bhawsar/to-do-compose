package com.aditya.to_do.ui.screens.task

import android.app.Notification
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.aditya.to_do.R
import com.aditya.to_do.components.DisplayAlertDialog
import com.aditya.to_do.data.models.ToDoTask
import com.aditya.to_do.ui.theme.topAppBarBackgroundColor
import com.aditya.to_do.ui.theme.topAppBarContentColor
import com.aditya.to_do.util.Action

@Composable
fun TaskAppBar(
    selectedTask: ToDoTask?,
    navigateToListScreen: (Action) -> Unit
){
    if(selectedTask == null){
        NewTaskAppBar(navigateToListScreen = navigateToListScreen)
    }else{
        ExistingTaskAppBar(navigateToListScreen = navigateToListScreen)
    }
}

@Composable
fun NewTaskAppBar(navigateToListScreen: (Action)-> Unit){
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = navigateToListScreen)
        },
        title = {
            Text(
                text = stringResource(id = R.string.new_task_heading),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            AddAction(onAddClicked = navigateToListScreen)
        }
    )
}


@Composable
fun ExistingTaskAppBar(navigateToListScreen: (Action) -> Unit){
    TopAppBar(
        navigationIcon = {
            BackAction(onBackClicked = {
                navigateToListScreen(Action.NO_ACTION)
            })
        },
        title = {
            Text(
                text = stringResource(id = R.string.edit_task_heading),
                color = MaterialTheme.colors.topAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
        actions = {
            ExistingTaskAppBarActions(navigateToListScreen = navigateToListScreen)
        }
    )
}

@Composable
fun ExistingTaskAppBarActions(navigateToListScreen: (Action) -> Unit){
    var openDialog by remember { mutableStateOf(false) }

    DisplayAlertDialog(
        title = stringResource(id = R.string.delete_task),
        msg = stringResource(id = R.string.delete_task_confirmation),
        openDialog = openDialog,
        onCloseDialog = { openDialog = false },
        onYesClicked = { navigateToListScreen(Action.DELETE) }
    )

    UpdateAction(onUpdateClicked = navigateToListScreen)
    DeleteAction(onDeleteClicked = { openDialog = true } )

}

@Composable
fun BackAction(
    onBackClicked: (Action)-> Unit
){
    IconButton(onClick = {
        onBackClicked(Action.NO_ACTION)
    }) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = stringResource(id = R.string.back_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun AddAction(
    onAddClicked: (Action)-> Unit
){
    IconButton(onClick = {
        onAddClicked(Action.ADD)
    }) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = stringResource(id = R.string.add_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun DeleteAction(
    onDeleteClicked: (Action)-> Unit
){
    IconButton(onClick = {
        onDeleteClicked(Action.DELETE)
    }) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = stringResource(id = R.string.delete_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}

@Composable
fun UpdateAction(
    onUpdateClicked: (Action)-> Unit
){
    IconButton(onClick = {
        onUpdateClicked(Action.UPDATE)
    }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_save),
            contentDescription = stringResource(id = R.string.update_action),
            tint = MaterialTheme.colors.topAppBarContentColor
        )
    }
}