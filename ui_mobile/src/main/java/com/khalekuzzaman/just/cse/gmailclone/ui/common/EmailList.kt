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
fun EmailList(
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
            EmailItem(
                email = email,
                onEmailItemClick = {
                    onEmailClick(email)
                },
                highLightedText = highlightedText,
                onChangeBookmark = {
                    onBookIconClick(email.emailid)
                }
            )

        }
    }
}

@Composable
fun EmailListUsingSlotEmailItemDemo() {
    EmailListUsingSlotEmailItem(
        emails = FakeEmailList().getFakeEmails(),
        onChangeBookmark = {},
        onEmailSelectedOrDeselected = {},
        selectedEmailIds = emptySet(),
        onEmailItemClick = {}
    )
}

@Composable
fun EmailListUsingSlotEmailItem(
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

            EmailItem(
                userName = email.userName,
                subject = email.subject,
                message = email.message,
                timeOrDate = email.timeOrDate,
                isBookmarked = false,
                isSelected = false,
                onClick = {
                    onEmailItemClick(email)
                    Log.i("onClickExecuted:", "SingleClick${email.emailid}")
                },
                onLongClick = {
                    onEmailSelectedOrDeselected(email.emailid)
                    Log.i("onClickExecuted:", "LongClick${email.emailid}")
                },
                onProfileImageClick = {
                    onEmailSelectedOrDeselected(email.emailid)
                    Log.i("onClickExecuted:", "ProfileImageClick${email.emailid}")
                },
                onBookmarkIconClick = {
                    onChangeBookmark(email.emailid)
                    Log.i("onClickExecuted:", "onBookmarkClick${email.emailid}")
                },

                )

        }
    }
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
            EmailItem(
                email = email,
                onLongClick = {
                    onEmailSelectedOrDeselected(email.emailid)
                },
                isSelected = selectedEmailIds.contains(email.emailid),
                onEmailItemClick = { onEmailItemClick(email) },
                onChangeBookmark = {
                    onChangeBookmark(email.emailid)
                }
            )

        }
    }
}


/*

 */
@Composable
fun EmailList(
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
    EmailList(
        emails = FakeEmailList().getFakeEmails(),
        onChangeBookmark = {},
        onEmailSelectedOrDeselected = {},
        selectedEmailIds = emptySet(),
        highlightedText = "a",
        onEmailItemClick = {}
    )
}
