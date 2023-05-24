package com.khalekuzzaman.just.cse.gmailclone

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onChangeBookmark: (itemID: Int) -> Unit = {},
) {
    //List of selected email
    var selectedEmailIds by remember {
        mutableStateOf(emptySet<Int>())
    }


    LazyColumn() {
        items(items = emails, key = { it.emailid }) { email ->
            EmailItem(
                info = email,
                isSelected = selectedEmailIds.contains(email.emailid),
                onLongClick = { clickedEmailId ->
                    selectedEmailIds = if (selectedEmailIds.contains(email.emailid))
                        selectedEmailIds.minus(email.emailid) else selectedEmailIds.plus(email.emailid)

                },
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
    EmailList(emails = getFakeEmails())
}
fun getFakeEmails(): List<EmailModel> {
    val list: MutableList<EmailModel> = mutableListOf()
    for (i in 1..10) {
        val email = EmailModel(
            emailid = i,
            userName = "Md Khalekuzzaman : $i",
            subject = "This the subjct of the email,that will be used for testing purpose",
            message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                    " and the other tool.",
            isBookMarked = false,
            timeOrDate = "13-03-23",
            profileImageId = R.drawable.profile_image
        )
        list.add(email)
    }
    return list
}
