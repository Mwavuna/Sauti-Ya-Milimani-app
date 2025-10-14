package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.exp

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun <T> DropDownField(
    title:String="",
    list:List<T> = emptyList(),
    selectedItem:(selected: T)->Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var itemTitle by remember { mutableStateOf(title) }


    Column() {
        //Activate dropDown button
        Button(
            onClick = {
                expanded = true
            },
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary.copy(0.05f),
                contentColor = MaterialTheme.colorScheme.primary.copy(0.9f),
            )
        ) {

            //Text Displayer
            Text(
                text = itemTitle,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal
            )


            //DropDown icon
            Icon(
                Icons.Outlined.ArrowDropDown,
                contentDescription = "Select Role",
                tint = MaterialTheme.colorScheme.primary.copy(0.9f)
            )


            //DropDown menu itself
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                }
            ) {
               list.forEach { currentItem->
                    DropdownMenuItem(
                        text = {
                            Text(currentItem.toString())
                        },
                        onClick = {
                            itemTitle = currentItem.toString()
                            selectedItem(currentItem)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}