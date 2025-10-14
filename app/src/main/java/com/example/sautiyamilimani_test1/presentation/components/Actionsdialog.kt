package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sautiyamilimani_test1.domain.model.Member

@Composable
fun ActionsDialog(
    onDismiss: () -> Unit,
    onDelete: () -> Unit,
) {

    var expanded by remember { mutableStateOf(false) }

    Column(){


    }

    var member by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        text = {
            Column {
                IconButton(onClick = {
                }) {
                    Icon(Icons.Outlined.Edit, "Edit")
                }
                Spacer(Modifier.height(8.dp))
                IconButton(onClick = {
                    onDelete()
                    onDismiss()
                }) {
                    Icon(Icons.Outlined.Delete, "Delete")
                }
            }

        },
        confirmButton = {},
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface

    )

}
