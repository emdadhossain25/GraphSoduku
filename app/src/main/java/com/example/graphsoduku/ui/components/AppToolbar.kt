package com.example.graphsoduku.ui.components

import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.graphsoduku.ui.textColorDark
import com.example.graphsoduku.ui.textColorLight


/**
 * we can create this modifier and use it in this widget
 * but reusing is then not possible
 * usable component here is Parent composable will dictate via modifier (position size etc)
 * same as modifier icon will be dictated by icons
 */
@Composable
fun AppToolbar(
    modifier: Modifier,
    title: String,
    icons: @Composable () -> Unit
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.primary,
        //this will choose primary color based on dark and light mode
        contentColor = Color.White,
        title = {
            Text(
                text = title,
                style = typography.h6,
                color = if (MaterialTheme.colors.isLight) textColorLight
                else textColorDark,
                textAlign = TextAlign.Start,
                maxLines = 1

            )
        },
        actions = {
            icons()
        }
    )


}