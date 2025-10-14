package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sautiyamilimani_test1.domain.model.Member
import com.example.sautiyamilimani_test1.domain.model.Role
import com.example.sautiyamilimani_test1.domain.model.Status
import com.example.sautiyamilimani_test1.presentation.Mappers.roleToString
import com.example.sautiyamilimani_test1.presentation.Mappers.statusToString
import com.example.sautiyamilimani_test1.presentation.Mappers.stringToRole
import com.example.sautiyamilimani_test1.presentation.Mappers.stringToStatus


@Composable
fun PreviewAddMemberDialog() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray) // just to see context
    ) {

    }
}

@Composable
fun AddMemberDialog(
    onDismis: () -> Unit,
    onAdd: (member: Member) -> Unit,
    editingMember: Member? = null,
    onEditComplete: (member: Member) -> Unit
) {


    var member by remember { mutableStateOf(null) }

    AlertDialog(
        onDismissRequest = { onDismis() },
        title = {
            TitleContent(
                onCloseDialog = { onDismis() },
                editingMember
            )
        },
        text = {
            MainContent(
                onAdd = { it ->
                    onAdd(it)
                },
                onDismiss = {
                    onDismis()
                },
                editingMember,
                onDoneEditing = {
                    onEditComplete(it)
                }
            )

        },
        confirmButton = {},
        modifier = Modifier.wrapContentWidth(),
        containerColor = MaterialTheme.colorScheme.surface,
        titleContentColor = MaterialTheme.colorScheme.onSurface

    )

}


@Composable
fun TitleContent(
    onCloseDialog: () -> Unit,
    editingMember: Member?
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
                text = if (editingMember != null) {
                    "Edit Member"
                } else {
                    "Add New Member"
                },
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

//@Preview(showBackground = true)
@Composable
fun MainContent(
    onAdd: (member: Member) -> Unit,
    onDismiss: () -> Unit,
    editingMember: Member?,
    onDoneEditing: (Member) -> Unit
) {
    // Prefill from editingMember if provided, otherwise use defaults.
    var name by remember(editingMember) { mutableStateOf(editingMember?.fullName ?: "") }
    var email by remember(editingMember) { mutableStateOf(editingMember?.email ?: "") }
    var phone by remember(editingMember) { mutableStateOf(editingMember?.phone1 ?: "") }
    var phone2 by remember(editingMember) { mutableStateOf(editingMember?.phone2 ?: "") }
    var status by remember(editingMember) {
        mutableStateOf(
            editingMember?.status ?: Status.PENDING
        )
    }
    var role by remember(editingMember) { mutableStateOf(editingMember?.role ?: Role.MEMBER) }

    fun clearFields() {
        name = ""
        email = ""
        phone = ""
        phone2 = ""
        status = Status.PENDING
        role = Role.MEMBER
    }


    Column {
        InputField(
            label = "Full Name",
            placeholder = "Enter Full Name",
            value = name,
            onValueChange = { name = it },
            type = FieldType.TEXT
        )

        Spacer(Modifier.height(12.dp))

        InputField(
            label = "Email",
            placeholder = "Enter Email",
            value = email,
            onValueChange = { email = it },
            type = FieldType.EMAIL
        )

        Spacer(Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)

        ) {
            Box(
                modifier = Modifier.weight(1f)
            ) {
                InputField(
                    label = "Phone Number 1",
                    placeholder = "+254 ...",
                    value = phone,
                    onValueChange = { phone = it },
                    type = FieldType.PHONE
                )
            }

            Box(
                modifier = Modifier.weight(1f)
            ) {
                InputField(
                    label = "Phone Number 2",
                    placeholder = "+254 ...",
                    value = phone2,
                    onValueChange = { phone2 = it },
                    type =   FieldType.PHONE
                )

        }


    }

    Spacer(Modifier.height(12.dp))

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            val roles = listOf("Member", "Leader")
            Text("Role")
            DropDownField(
                title = if (editingMember != null) {
                    roleToString(role)
                } else {
                    "Select role"
                },
                list = roles,
                selectedItem = { selected ->
                    role = stringToRole(selected)
                }
            )
        }

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text("Active Status")
            val statuses = listOf("Active", "Inactive", "Pending")
            DropDownField(
                title = if (editingMember != null) {
                    statusToString(status)
                } else {
                    "Select status"
                },
                list = statuses,
                selectedItem = { selected ->
                    status = stringToStatus(selected)
                }
            )
        }
    }


    Spacer(Modifier.height(12.dp))


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),
            onClick = {

                if (editingMember != null) {
                    val member = editingMember.copy(
                        fullName = name,
                        email = email,
                        phone1 = phone,
                        phone2 = phone2,
                        status = status,
                        role = role,
                        attendanceRate = 0f,
                        addedBy = "Graham",
                        timestamp = System.currentTimeMillis()
                    )
                    onDoneEditing(member)

                } else {
                    val member = Member(
                        fullName = name,
                        email = email,
                        phone1 = phone,
                        phone2 = phone2,
                        status = status,
                        role = role,
                        attendanceRate = 0f,
                        addedBy = "Graham",
                        timestamp = System.currentTimeMillis()
                    )

                    onAdd(member)
                }
                clearFields()
                onDismiss()


            }
        ) {
            Text(
                if (editingMember != null) {
                    "Save Changes"
                } else {
                    "Add Member"
                }
            )
        }

        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            shape = RoundedCornerShape(8.dp),

            onClick = {
                clearFields()
                onDismiss()
            }
        ) {
            Text(
                text = "Cancel",
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
}





