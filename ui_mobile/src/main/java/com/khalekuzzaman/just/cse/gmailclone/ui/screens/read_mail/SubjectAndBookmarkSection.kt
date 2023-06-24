package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BookmarkIcon


@Composable
fun SubjectAndBookmarkRow(
    onBookmarkIconClick: () -> Unit,
    isBookmarked: Boolean,
    modifier: Modifier = Modifier,
    subject: String,
) {
    Row(modifier = modifier) {
        Text(
            fontSize = 20.sp,
            modifier = Modifier.weight(1f),
            text = subject,
            style = MaterialTheme.typography.bodyLarge
        )
        BookmarkIcon(
            isBookMarked = isBookmarked,
            onBookmarkIconClick = onBookmarkIconClick
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun SubjectPreview() {
    SubjectAndBookmarkRow(
        onBookmarkIconClick = {},
        isBookmarked = false,
        subject = "A personal access token(classic) has been added to your account"
    )
}
