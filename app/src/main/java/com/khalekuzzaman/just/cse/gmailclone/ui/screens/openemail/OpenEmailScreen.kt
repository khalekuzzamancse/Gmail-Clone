package com.khalekuzzaman.just.cse.gmailclone.ui.screens.openemail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BookmarkIcon


@Composable
private fun SubjectAndBookmark(
    modifier: Modifier = Modifier,
    subjectText: String,
    onBookmarkChange: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubjectShow(
            modifier.weight(1f),
            text = subjectText
        )
        BookmarkIcon(
            onBookmarkIconClick = onBookmarkChange
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun SubjectAndBookmarkPreview() {
    SubjectAndBookmark(
        subjectText = "A personal acccess token(classic) has been added to your account",
        onBookmarkChange = {}
    )
}

@Composable
private fun SubjectShow(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        fontSize = 20.sp,
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
@Preview(showBackground = true)
private fun SubjectPreview() {
    SubjectShow(text = "A personal acccess token(classic) has been added to your account")
}