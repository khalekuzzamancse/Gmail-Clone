package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailItemDemo
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.navigation.ModalDrawerNavHost
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
               // ModalDrawerNavHost()
                EmailItemDemo()
            }
        }

    }


    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    private fun Previews() {

    }

}

