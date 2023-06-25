package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmail
import com.khalekuzzaman.just.cse.gmailclone.utils.TextFinder

@Composable
fun EmailBody(modifier: Modifier = Modifier, message: String) {
    val annotatedEmailString: AnnotatedString = buildAnnotatedString {
        append(message)
        val urls = TextFinder().findUrls(message)
        urls.forEach { pair ->
            addStyle(
                style = SpanStyle(
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline
                ),
                start = pair.first,
                end = pair.second + 1
            )
        }

    }
    Column(modifier = Modifier
        .wrapContentSize(align = Alignment.TopCenter)
    ) {
        ClickableText(
            modifier = modifier,
            text = annotatedEmailString,
            onClick = {

            }
        )
    }


}

@Composable
@Preview(
    showBackground = true,
)
@Preview(
   widthDp = 800,
    heightDp = 500
)
private fun EmailBodyPreview() {
    EmailBody(message = FakeEmail.email.message)
}
