package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList

@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onChangeBookmark: (itemID: Int) -> Unit,
    onEmailSelectedOrDeselected: (email: Int) -> Unit,
    selectedEmailIds: Set<Int>,
    highlightedText: String="",
    onEmailItemClick: (EmailModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = emails, key = { it.emailid }) { email ->
            EmailItem(
                emailModel = email,
                onLongClick = onEmailSelectedOrDeselected,
                isSelected = selectedEmailIds.contains(email.emailid),
                onEmailItemClick = onEmailItemClick,
                highLightedText = highlightedText,
                onChangeBookmark = onChangeBookmark
            )

        }
    }
}

//-------------
//----------
//------------
@Composable
@Preview(showBackground = true)
private fun EmailListPreview() {
    EmailList(
        emails = FakeEmailList().getFakeEmails(),
        onChangeBookmark = {},
        onEmailSelectedOrDeselected = {},
        selectedEmailIds = emptySet(),
        highlightedText = "a",
        onEmailItemClick = {}
    )
}
