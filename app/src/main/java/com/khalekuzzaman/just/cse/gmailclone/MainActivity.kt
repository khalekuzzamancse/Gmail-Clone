package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.allinbox.AllInboxScreen
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.allinbox.AllInboxScreenContent
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                val coroutineScope = rememberCoroutineScope()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val openDrawer: () -> Unit = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }
                val closeDrawer: () -> Unit = {
                    coroutineScope.launch {
                        drawerState.close()
                    }
                }

                AllInboxScreen(
                    onNavigate = {},
                    closeDrawer = closeDrawer,
                    drawerState = drawerState,
                    openDrawer = openDrawer
                )
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreen() {
        var selectedEmails by remember {
            mutableStateOf(0)
        }
        Scaffold(
            topBar = {
                if (selectedEmails <= 0)
                    CommonTopAppbar {}
                else
                    ContextualTopAppbar(
                        selectedEmails = selectedEmails
                    )
            }) {
            Column(modifier = Modifier.padding(it)) {
                AllInboxScreenContent(
                    onEmailSelectedCountChange = { count ->
                        selectedEmails = count
                    }
                )
                // SubjectAndSenderInfo()
                // ComposeEmail()
            }

        }
    }

    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    private fun Previews() {
        MainScreen()
    }

}

