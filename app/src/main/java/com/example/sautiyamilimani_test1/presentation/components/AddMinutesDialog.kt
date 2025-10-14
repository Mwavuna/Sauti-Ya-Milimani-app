package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sautiyamilimani_test1.domain.model.Role
import com.example.sautiyamilimani_test1.domain.model.Status


@Composable
fun AddMinutesDialog() {
    var openDialog by remember { mutableStateOf(true) }
    var pickedDate by remember { mutableStateOf("") }

    if (openDialog)
        AlertDialog(
            onDismissRequest = { openDialog = false },
            title = { Title(onCloseDialog = { openDialog = false }) },
            text = { Content() },
            confirmButton = {},
            modifier = Modifier.wrapContentWidth(),
            containerColor = MaterialTheme.colorScheme.surface,
            titleContentColor = MaterialTheme.colorScheme.onSurface

        )


}

@Composable
fun Title(
    onCloseDialog: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(0.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Add New Member",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            IconButton(
                onClick = {
                    onCloseDialog()

                },
                Modifier.size(24.dp)

            ) {
                Icon(
                    Icons.Filled.Close,
                    contentDescription = "Close",
                    Modifier.size(24.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Content() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var phone2 by remember { mutableStateOf("") }
    var role by remember { mutableStateOf(Role.MEMBER) }
    var status by remember { mutableStateOf(Status.PENDING) }
    Column {

        //Full name label and field
        InputField(
            label="Full Name",
            placeholder = "Enter Full Name",
            value = name,
            onValueChange = { name = it },
            type = FieldType.TEXT
        )

        Spacer(Modifier.height(12.dp))

        //Email label and field
        InputField(
            label="Email",
            placeholder = "Enter Email",
            value=email,
            onValueChange = { email = it },
            type = FieldType.EMAIL
        )

        Spacer(Modifier.height(12.dp))

        //Phone1 and Phone2 row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {

            //Phone 1 label and field
            Box(
                modifier = Modifier.weight(1f)
            ) {
                InputField(
                    label="Phone Number 1",
                    placeholder = "+254 ...",
                    value = phone,
                    onValueChange = { phone = it },
                    type = FieldType.PHONE
                )
            }

            //phone2 label and field
            Box(
                modifier = Modifier.weight(1f)
            ) {
                InputField(
                    label="Phone Number 2",
                    placeholder = "+254 ...",
                    value = phone2,
                    onValueChange = { phone2 = it },
                    type = FieldType.PHONE
                )
            }


        }

        //End of phone row
        Spacer(Modifier.height(12.dp))

        //Role and status row
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            //Role label and dropDown
            Column(
                modifier = Modifier.weight(1f)
            ) {
                val roles = listOf("Member", "Leader")
                Text("Role")
                DropDownField(title = "roles", list = roles, selectedItem = {})
            }

            //Status label and dropDown
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text("Initial status")
                val status = listOf("Active", "Inactive", "Pending")
                DropDownField(title = "status", list = status, selectedItem = {})
            }
        }

        //End role and status row
        Spacer(Modifier.height(12.dp))

        //Buttons row
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            
            //Add member button
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                onClick = {}
            ) {
                Text("Add Member")
            }


            //Save as Draft button
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                shape = RoundedCornerShape(8.dp),
                onClick = {}
            ) {
                Text(
                    text = "Save As Draft",
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

