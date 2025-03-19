package com.panini.recipe_detail.presentation.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.panini.core.presentation.theme.DeliciousTheme
import com.panini.recipe_detail.R

@Composable
fun TextWithIcon(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int,
    titleStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    iconColor: Color = MaterialTheme.colorScheme.secondary
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = title,
            style = titleStyle,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}

@Preview
@Composable
fun TextWithIconPreview() {
    DeliciousTheme {
        TextWithIcon(
            title = "Servings 4",
            icon = R.drawable.ic_meal
        )
    }
}