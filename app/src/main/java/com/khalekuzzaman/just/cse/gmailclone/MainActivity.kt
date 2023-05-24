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
                     val selected by remember{
                         mutableStateOf(false)
                     }
                    EmailItem(){
                        selected!=selected
                        Log.i("EmailItem", selected.toString())
                    }
                    EmailItem()
                    EmailItem()
                    EmailItem()
                    EmailItem()
                    EmailItem()

                }

//                val drawerGroups= listOf(
//                    DrawerGroup("Group 1", DrawerItemsProvider.getAllInboxes(),false),
//                    DrawerGroup("Group 1", DrawerItemsProvider.getGroup01(),false),
//                    DrawerGroup("Recent labels", DrawerItemsProvider.getGroup02(),true),
//                    DrawerGroup("All Labels", DrawerItemsProvider.getAllLabels(),true),
//                    DrawerGroup("Google apps", DrawerItemsProvider.getGoogleApps(),true),
//                    DrawerGroup("Last Group", DrawerItemsProvider.getLastGroup(),false),
//
//                    )
//                AppDrawer2(drawerGroups) {
//                }
            }
        }
    }
}

