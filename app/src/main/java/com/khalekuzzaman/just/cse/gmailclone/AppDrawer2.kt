package com.khalekuzzaman.just.cse.gmailclone

import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class ModalDrawerItem(
    val label: String,
    val icon: Int,//resourceId will be given
    val badgeCount: Int,
)

data class DrawerGroup(
    val groupName: String,
    val items: List<ModalDrawerItem>,
    val showGroupName: Boolean = true,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer2(
    drawerGroups: List<DrawerGroup>,
    //Make sure later,that the  hashmap is immutable,
    //so that no one can add or remove item from it
    //also mark it as stable or immutable so  that
    //compose compiler can optimize it
    onNavigate: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val drawerWidth = with(LocalDensity.current) {
        (LocalConfiguration.current.screenWidthDp * 0.9).dp
    }
    ModalNavigationDrawer(
        modifier = Modifier
            .verticalScroll(scrollState)
            .width(drawerWidth),
        drawerState = rememberDrawerState(DrawerValue.Open),
        drawerContent = { DrawerContent(drawerGroups) }) {
        Content()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(drawerGroups: List<DrawerGroup>) {
    ModalDrawerSheet() {

        val firstGroupFirstItem = drawerGroups[0].items[0];
        val selectedItem = remember { mutableStateOf(firstGroupFirstItem) }
        drawerGroups.forEach { group ->

            val items = group.items
            //
            if (group.showGroupName) {
                DisplayGroupName(group.groupName)
            } else {
                DisplayDivider()
            }
            //placing the each drawer items
            items.forEach { drawerItem ->
                NavigationDrawerItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = drawerItem.icon),
                            contentDescription = null
                        )
                    },
                    label = { Text(drawerItem.label) },
                    selected = drawerItem == selectedItem.value,
                    onClick = {
                        selectedItem.value = drawerItem
                    },

                    )
            }
        }


    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DisplayGroupName(groupName: String) {
    Row(
        //We want to align the start of the group name
        //with the start of the NavigationDrawer icon
        //that is why we have to use the exact padding
        //and the layout that is used by the  NavigationDrawer()
        //compose,so take the padding value and the alignmentValue
        //from the  NavigationDrawer() composable
        Modifier.padding(start = 16.dp, end = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = groupName)
    }
}

@Composable
private fun DisplayDivider() {
    Divider()
}

@Composable
private fun Content() {
}

@Preview(showBackground = true)
@Composable
private fun AppDrawer2Preview() {
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
class DrawerItemsProvider {
    companion object {
        fun getAllInboxes(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("All inboxes", R.drawable.ic_all_inboxes, 0),
        )
        fun getGroup01(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("Primary", R.drawable.ic_primary, 0),
            ModalDrawerItem("Promotions", R.drawable.ic_promotion, 0),
            ModalDrawerItem("Social", R.drawable.ic_social, 0),
            ModalDrawerItem("Updates", R.drawable.ic_updates, 0),
            ModalDrawerItem("Forums", R.drawable.ic_forums, 0)
        )

        fun getGroup02(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("[Imap]/Trash", R.drawable.ic_label, 0),
            ModalDrawerItem("SMS", R.drawable.ic_label, 0)
        )
        fun getAllLabels(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("Started", R.drawable.ic_started, 0),
            ModalDrawerItem("Snoozed", R.drawable.ic_snoozed, 0),
            ModalDrawerItem("Important", R.drawable.ic_important, 0),
            ModalDrawerItem("Sent", R.drawable.ic_sent, 0),
            ModalDrawerItem("Scheduled", R.drawable.ic_schedule, 0),
            ModalDrawerItem("Outbox", R.drawable.ic_outbox, 0),
            ModalDrawerItem("Draft", R.drawable.ic_draft, 0),
            ModalDrawerItem("All Mail", R.drawable.ic_sent, 0),
            ModalDrawerItem("Spam", R.drawable.ic_spam, 0),
            ModalDrawerItem("Bin", R.drawable.ic_bin, 0),
            ModalDrawerItem("[Imap]/Sent", R.drawable.ic_label, 0),
            ModalDrawerItem("[Imap]/Trash", R.drawable.ic_label, 0),
            ModalDrawerItem("Call log", R.drawable.ic_label, 0),
            ModalDrawerItem("SMS", R.drawable.ic_label, 0),
        )
        fun getGoogleApps(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("Calender", R.drawable.ic_calendar, 0),
            ModalDrawerItem("Contact", R.drawable.ic_contact, 0),
        )
        fun getLastGroup(): List<ModalDrawerItem> = listOf(
            ModalDrawerItem("Setting", R.drawable.ic_setting, 0),
            ModalDrawerItem("Help and feedback", R.drawable.ic_help_and_feedback, 0),
        )

    }
}






