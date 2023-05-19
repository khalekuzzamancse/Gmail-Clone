package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.AppDrawer
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.getAllLabels
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.getGoogleAppsLabels
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.getGroup1
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.getLastLabels
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.getRecentLabels

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                val drawerItems = linkedMapOf(
                    Pair("Group1", getGroup1()),
                    Pair("Recent labels", getRecentLabels()),
                    Pair("All labels", getAllLabels()),
                    Pair("Google apps", getGoogleAppsLabels()),
                    Pair("", getLastLabels()),
                )
                AppDrawer(drawerRoutes = drawerItems) {
                }
            }
        }
    }
}

