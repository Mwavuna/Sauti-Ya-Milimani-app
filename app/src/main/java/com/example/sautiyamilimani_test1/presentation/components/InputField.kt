package com.example.sautiyamilimani_test1.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Numbers
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material.icons.outlined.TextFields
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class FieldType {
    TEXT,
    NUMBER,
    EMAIL,
    PASSWORD,
    PHONE
}

@Composable
fun InputField(
    label: String,
    placeholder: String,
    value: String,
    onValueChange: (String) -> Unit,
    type: FieldType = FieldType.TEXT,
    autoFocus: Boolean = false
) {
    val keyboardType = when (type) {
        FieldType.NUMBER -> KeyboardType.Number
        FieldType.EMAIL -> KeyboardType.Email
        FieldType.PASSWORD -> KeyboardType.Password
        FieldType.PHONE -> KeyboardType.Phone
        else -> KeyboardType.Text
    }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    var isFocused by rememberSaveable { mutableStateOf(false) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val borderColor by animateColorAsState(
        if (isFocused) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary.copy(
            .5f
        )
    )
    val borderWidth by animateDpAsState(if (isFocused) 2.dp else 1.dp)

    // Optional auto-focus
    if (autoFocus) {
        LaunchedEffect(Unit) { focusRequester.requestFocus() }
    }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(Modifier.height(6.dp))

        // Single clickable on the outer box only. This avoids multiple handlers.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp)
                .border(borderWidth, borderColor, MaterialTheme.shapes.small)
                .background(
                    if (isFocused) MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) else MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.05f
                    ), MaterialTheme.shapes.small
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusRequester.requestFocus() }
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = when (type) {
                        FieldType.TEXT -> Icons.Outlined.TextFields
                        FieldType.NUMBER -> Icons.Outlined.Numbers
                        FieldType.EMAIL -> Icons.Outlined.Email
                        FieldType.PASSWORD -> Icons.Outlined.Lock
                        FieldType.PHONE -> Icons.Outlined.Phone
                    },
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = if (isFocused) 1f else 0.7f),
                    modifier = Modifier.size(20.dp)
                )

                Spacer(Modifier.width(8.dp))

                // Text area: fill the remaining space so placeholder/cursor baseline match
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
                    if (value.isEmpty() && !isFocused) {
                        Text(
                            text = placeholder,
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f),
                            fontSize = 14.sp
                        )
                    }

                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = true,
                        textStyle = TextStyle(
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 14.sp
                        ),
                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = keyboardType,
                            imeAction = if (type == FieldType.PASSWORD) ImeAction.Done else ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Down) },
                            onDone = { focusManager.clearFocus() }
                        ),
                        visualTransformation = if (type == FieldType.PASSWORD && !isPasswordVisible)
                            androidx.compose.ui.text.input.PasswordVisualTransformation()
                        else
                            androidx.compose.ui.text.input.VisualTransformation.None,
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)            // <-- Attach the requester
                            .onFocusChanged { isFocused = it.isFocused } // <-- track focus
                    )
                }

                if (type == FieldType.PASSWORD) {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Outlined.Visibility else Icons.Outlined.VisibilityOff,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.9f),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}
