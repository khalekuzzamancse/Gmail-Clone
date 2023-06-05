package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList

/*
updated

 */

@Composable
fun EmailListForSearchResult(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onBookIconClick: (itemId: Int) -> Unit,
    onEmailClick: (EmailModel) -> Unit,
    highlightedText: String,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = emails, key = { it.emailid }) { email ->

            EmailItemForSearchList(
                userName = email.userName,
                subject = email.subject,
                message = email.message,
                onClick = {
                    onEmailClick(email)
                    Log.i("onClickExecuted:", "SingleClick")
                },
                searchedText = highlightedText,
                onBookmarkIconClick = {
                    onBookIconClick(email.emailid)
                    Log.i("onClickExecuted:", "onBookmarkClick")
                },
                isBookmarked = email.isBookMarked,
                timeOrDate = email.timeOrDate,
            )

        }
    }
}

@Composable
fun EmailListUsingSlotEmailItemDemo() {
    EmailListForSearchResult(
        emails = FakeEmailList().getFakeEmails(),
        onChangeBookmark = {},
        onEmailSelectedOrDeselected = {},
        selectedEmailIds = emptySet(),
        onEmailItemClick = {}
    )
}

@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onChangeBookmark: (itemID: Int) -> Unit,
    onEmailSelectedOrDeselected: (itemID: Int) -> Unit,
    selectedEmailIds: Set<Int>,
    onEmailItemClick: (EmailModel) -> Unit,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = emails, key = { it.emailid }) { email ->
            val isSelected= selectedEmailIds.contains(email.emailid)

            EmailItem(
                userName = email.userName,
                subject = email.subject,
                message = email.message,
                timeOrDate = email.timeOrDate,
                isBookmarked = email.isBookMarked,
                isSelected = isSelected,
                onClick = {
                    onEmailItemClick(email)
                },
                onLongClick = {
                    onEmailSelectedOrDeselected(email.emailid)
                },
                onProfileImageClick = {
                    onEmailSelectedOrDeselected(email.emailid)
                },
                onBookmarkIconClick = {
                    onChangeBookmark(email.emailid)
                },

                )

        }
    }
}




/*

 */
@Composable
fun EmailListForSearchResult(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onChangeBookmark: (itemID: Int) -> Unit,
    onEmailSelectedOrDeselected: (email: Int) -> Unit,
    selectedEmailIds: Set<Int>,
    highlightedText: String = "",
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
    EmailListUsingSlotEmailItemDemo()
}
