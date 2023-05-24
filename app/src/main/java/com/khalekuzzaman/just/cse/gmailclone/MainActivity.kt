package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                Scaffold(
                    topBar = {
                        CommonTopAppbar()
                    }) {
                    LazyColumn(modifier = Modifier.padding(it)) {
                        items(10) { index ->
                            Text(
                                "I'm item $index", modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
//                Column {
//                    var emails by remember {
//                        mutableStateOf(getFakeEmails())
//                    }
//
//                    EmailList(emails = emails, onChangeBookmark = { emailId ->
//                        emails = BookmarkUpdater(emails).update(emailId)
//                    })
//
////                AppDrawer2(drawerGroups) {
////                }
//                }
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

