package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.allinbox.AllInboxScreen
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.compose_email.ComposeEmail
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.openemail.SenderInfoHeader
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.openemail.SubjectAndSenderInfo
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                var selectedEmails by remember {
                    mutableStateOf(0)
                }
                Scaffold(
                    topBar = {
                        if (selectedEmails <= 0)
                            CommonTopAppbar()
                        else
                            ContextualTopAppbar(
                                selectedEmails = selectedEmails
                            )
                    }) {
                    Column(modifier = Modifier.padding(it)) {
//                        AllInboxScreen(
//                            onEmailSelectedCountChange = { count ->
//                                selectedEmails = count
//                            }
//                        )
                       // SubjectAndSenderInfo()
                        ComposeEmail()
                    }

                }

            }
        }
    }

}




