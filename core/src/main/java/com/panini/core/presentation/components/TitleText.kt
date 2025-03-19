package com.panini.core.presentation.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.panini.core.presentation.theme.DeliciousTheme
import com.panini.core.presentation.theme.fontFamily
import com.panini.core.presentation.theme.leagueScript
import com.panini.core.R


@Composable
fun TitleText(
    modifier: Modifier = Modifier,
    @StringRes primaryText: Int,
    @StringRes accentText: Int,
) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primary,
            MaterialTheme.colorScheme.secondary
        ),
        start = Offset(0f, 0f),
        end = Offset(1000f, 0f)
    )

    Text(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    fontFamily = fontFamily,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            ) {
                append(stringResource(primaryText))
                append(" ")
            }
            withStyle(
                style = SpanStyle(
                    fontFamily = leagueScript,
                    fontSize = 54.sp,
                    brush = gradient
                )
            ) {
                append(stringResource(accentText))
            }
        },
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}

@Preview(showBackground = true)
@Composable
fun TitleTextPreview() {
    DeliciousTheme {
        TitleText(
            primaryText = R.string.try_something,
            accentText = R.string.delicious
        )
    }
}