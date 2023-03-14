package com.gonharry.basic_compose.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ProvideTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gonharry.basic_compose.ui.theme.Shapes
import com.gonharry.basic_compose.ui.theme.Typography

@Composable
fun CustomButton(
    onClick: ()-> Unit,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
            contentColor = Color.White,
            disabledBackgroundColor = Color.Red.copy(alpha = 0.12f),
            disabledContentColor = Color.White.copy(ContentAlpha.disabled)
        ),
        shape = Shapes.large,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 2.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp
        ),
        modifier = modifier.width(120.dp),
        content = {
            ProvideTextStyle(value = Typography.button) {
                content()
            }
    })

}