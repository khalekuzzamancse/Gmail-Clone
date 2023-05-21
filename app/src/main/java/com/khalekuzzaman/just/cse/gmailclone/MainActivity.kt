package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                val drawerGroups= listOf(
                    DrawerGroup("Group 1", DrawerItemsProvider.getAllInboxes(),false),
                    DrawerGroup("Group 1", DrawerItemsProvider.getGroup01(),false),
                    DrawerGroup("Recent labels", DrawerItemsProvider.getGroup02(),true),
                    DrawerGroup("All Labels", DrawerItemsProvider.getAllLabels(),true),
                    DrawerGroup("Google apps", DrawerItemsProvider.getGoogleApps(),true),
                    DrawerGroup("Last Group", DrawerItemsProvider.getLastGroup(),false),

                    )
                AppDrawer2(drawerGroups) {
                }
            }
        }
    }
}

