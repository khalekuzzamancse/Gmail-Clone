package com.khalekuzzaman.just.cse.gmailclone

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailItem
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel

@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onChangeBookmark: (itemID: Int) -> Unit,
    onEmailSelectedOrDeselected: (email: Int) -> Unit,
    selectedEmailIds: Set<Int>,
) {


    LazyColumn() {
        items(items = emails, key = { it.emailid }) { email ->
            EmailItem(
                info = email,
                isSelected = selectedEmailIds.contains(email.emailid),
                onLongClick = onEmailSelectedOrDeselected,
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
        selectedEmailIds = emptySet()
    )
}
