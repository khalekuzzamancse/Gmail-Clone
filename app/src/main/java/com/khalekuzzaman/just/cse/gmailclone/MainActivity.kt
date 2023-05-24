package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                Column {
                    var emails by remember {
                        mutableStateOf(
                            listOf(
                                EmailModel(
                                    emailid = 1,
                                    userName = "Abdur Razzak",
                                    subject = "This the subjct of the email,that will be used for testing purpose",
                                    message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                                            " and the other tool.",
                                    isBookMarked = false,
                                    timeOrDate = "13-03-23",
                                    profileImageId = R.drawable.profile_image
                                ),
                                EmailModel(
                                    emailid = 2,
                                    userName = "Md Khalekuzzaman",
                                    subject = "This the subjct of the email,that will be used for testing purpose",
                                    message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                                            " and the other tool.",
                                    isBookMarked = false,
                                    timeOrDate = "13-03-23",
                                    profileImageId = R.drawable.profile_image
                                ),
                            )
                        )
                    }

                    EmailList(emails = emails, onChangeBookmark = { emailId ->
                        emails = BookmarkUpdater(emails).update(emailId)
                    })

//                AppDrawer2(drawerGroups) {
//                }
                }
            }
        }
    }

}

class BookmarkUpdater(private val emails: List<EmailModel>) {
    private val updatedEmailList: MutableList<EmailModel> = emails.toMutableList();

    fun update(emailId: Int): List<EmailModel> {
        val position = getEmailPositionById(emailId)
        val email = getEmailByPosition(position)
        val updatedEmail = email.copy(
            isBookMarked = updateBookmark(oldBookmark = email.isBookMarked)
        )
        updateEmailAtPosition(updatedEmail, position)
        return updatedEmailList
    }

    private fun getEmailPositionById(emailId: Int): Int {
        return emails.indexOfFirst { it.emailid == emailId }
    }

    private fun updateEmailAtPosition(updatedEmail: EmailModel, position: Int) {
        updatedEmailList[position] = updatedEmail;
    }

    private fun getEmailByPosition(position: Int): EmailModel {
        return updatedEmailList[position]
    }

    private fun updateBookmark(oldBookmark: Boolean): Boolean {
        return !oldBookmark
    }
}

