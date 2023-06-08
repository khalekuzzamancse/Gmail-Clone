package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonScreenX

import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
              //  ModalDrawerNavHost()
              //  ContextualTopAppbarMenu()
                CommonScreenX()


            }
        }

    }


    @Composable
    @Preview(showBackground = true, showSystemUi = true)
    private fun Previews() {

    }

}

