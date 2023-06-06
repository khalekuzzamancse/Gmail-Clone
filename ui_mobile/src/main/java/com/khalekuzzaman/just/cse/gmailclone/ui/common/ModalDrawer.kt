package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
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
import com.khalekuzzaman.just.cse.gmailclone.R


@Composable

fun ModalDrawer(
    modifier: Modifier,
    drawerGroups: List<DrawerGroup>,
    //Make sure later,that the  hashmap is immutable,
    //so that no one can add or remove item from it
    //also mark it as stable or immutable so  that
    //compose compiler can optimize it
    onDrawerItemClick: (navigateTo: String) -> Unit,
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    content: @Composable () -> Unit,

    ) {


    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        drawerContent = {
            val drawerWidth = with(LocalDensity.current) {
                (LocalConfiguration.current.screenWidthDp * 0.80).dp
            }
            DrawerContent(
                modifier = Modifier.width(drawerWidth),
                drawerGroups,
                onNavigate = onDrawerItemClick,
                closeDrawer = closeDrawer,
            )
        }) {
        content()
    }
}

@Composable
private fun DrawerContent(
    modifier: Modifier = Modifier,
    drawerGroups: List<DrawerGroup>,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
) {
    val scrollState = rememberScrollState()
    ModalDrawerSheet(
        modifier = modifier.verticalScroll(scrollState)
    ) {

        val firstGroupFirstItem = drawerGroups[0].items[0]
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
                        onNavigate(drawerItem.label)
                        closeDrawer()
                    },
                )
            }
        }


    }

}


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


@Preview(showBackground = true)
@Composable
private fun ModalDrawerPreview() {
    ModalDrawer(
        drawerGroups = DrawerItemsProvider.drawerGroups,
        onDrawerItemClick = {},
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        closeDrawer = {},
        modifier = Modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Hello")
        }
    }
}


/*
--------------
----------------
----------
 */
data class ModalDrawerItem(
    val label: String,
    val icon: Int,//resourceId will be given
    val badgeCount: Int,
)

/*
--------------
----------------
----------
 */
data class DrawerGroup(
    val groupName: String,
    val items: List<ModalDrawerItem>,
    val showGroupName: Boolean = true,
)

/*
--------------
----------------
----------
 */
class DrawerDestinations {
    companion object {
        const val ALL_INBOXES = "All inboxes"
        const val PRIMARY = "Primary"
        const val PROMOTIONS = "Promotions"
        const val SOCIAL = "Social"
        const val UPDATES = "Updates"
        const val FORUMS = "Forums"
        const val IMAP_TRASH = "[Imap]/Trash"
        const val SMS = "SMS"
        const val STARTED = "Started"
        const val SNOOZED = "Snoozed"
        const val IMPORTANT = "Important"
        const val SENT = "Sent"
        const val SCHEDULED = "Scheduled"
        const val OUTBOX = "Outbox"
        const val DRAFT = "Draft"
        const val ALL_MAIL = "All Mail"
        const val SPAM = "Spam"
        const val BIN = "Bin"
        const val IMAP_SENT = "[Imap]/Sent"
        const val CALL_LOG = "Call log"
        const val CALENDAR = "Calendar"
        const val CONTACT = "Contact"
        const val SETTINGS = "Settings"
        const val HELP_AND_FEEDBACK = "Help and feedback"
    }
}

object DrawerItemsProvider {
    val drawerGroups = listOf(
        DrawerGroup("Group 1", getAllInboxes(), false),
        DrawerGroup("Group 1", getGroup01(), false),
        DrawerGroup("Recent labels", getGroup02(), true),
        DrawerGroup("All Labels", getAllLabels(), true),
        DrawerGroup("Google apps", getGoogleApps(), true),
        DrawerGroup("Last Group", getLastGroup(), false),
    )

    private fun getAllInboxes(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.ALL_INBOXES, R.drawable.ic_all_inboxes, 0),
    )

    private fun getGroup01(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.PRIMARY, R.drawable.ic_primary, 0),
        ModalDrawerItem(DrawerDestinations.PROMOTIONS, R.drawable.ic_promotion, 0),
        ModalDrawerItem(DrawerDestinations.SOCIAL, R.drawable.ic_social, 0),
        ModalDrawerItem(DrawerDestinations.UPDATES, R.drawable.ic_updates, 0),
        ModalDrawerItem(DrawerDestinations.FORUMS, R.drawable.ic_forums, 0)
    )

    private fun getGroup02(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.IMAP_TRASH, R.drawable.ic_label, 0),
        ModalDrawerItem(DrawerDestinations.SMS, R.drawable.ic_label, 0)
    )

    private fun getAllLabels(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.STARTED, R.drawable.ic_started, 0),
        ModalDrawerItem(DrawerDestinations.SNOOZED, R.drawable.ic_snoozed, 0),
        ModalDrawerItem(DrawerDestinations.IMPORTANT, R.drawable.ic_important, 0),
        ModalDrawerItem(DrawerDestinations.SENT, R.drawable.ic_sent, 0),
        ModalDrawerItem(DrawerDestinations.SCHEDULED, R.drawable.ic_schedule, 0),
        ModalDrawerItem(DrawerDestinations.OUTBOX, R.drawable.ic_outbox, 0),
        ModalDrawerItem(DrawerDestinations.DRAFT, R.drawable.ic_draft, 0),
        ModalDrawerItem(DrawerDestinations.ALL_MAIL, R.drawable.ic_sent, 0),
        ModalDrawerItem(DrawerDestinations.SPAM, R.drawable.ic_spam, 0),
        ModalDrawerItem(DrawerDestinations.BIN, R.drawable.ic_bin, 0),
        ModalDrawerItem(DrawerDestinations.IMAP_SENT, R.drawable.ic_label, 0),
        ModalDrawerItem(DrawerDestinations.IMAP_TRASH, R.drawable.ic_label, 0),
        ModalDrawerItem(DrawerDestinations.CALL_LOG, R.drawable.ic_label, 0),
        ModalDrawerItem(DrawerDestinations.SMS, R.drawable.ic_label, 0),
    )

    private fun getGoogleApps(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.CALENDAR, R.drawable.ic_calendar, 0),
        ModalDrawerItem(DrawerDestinations.CONTACT, R.drawable.ic_contact, 0),
    )

    private fun getLastGroup(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(DrawerDestinations.SETTINGS, R.drawable.ic_setting, 0),
        ModalDrawerItem(DrawerDestinations.HELP_AND_FEEDBACK, R.drawable.ic_help_and_feedback, 0),
    )

}
