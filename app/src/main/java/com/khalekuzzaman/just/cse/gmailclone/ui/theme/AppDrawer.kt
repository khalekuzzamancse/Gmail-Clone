package com.khalekuzzaman.just.cse.gmailclone.ui.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.node.modifierElementOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.sql.Struct

data class DrawerItem(
    val label: String,
    val icon: ImageVector,

    )


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppDrawer(
    drawerRoutes: LinkedHashMap<String, List<DrawerItem>>,
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
        modifier=Modifier
            .verticalScroll(scrollState)
            .width(drawerWidth),
        drawerState = rememberDrawerState(DrawerValue.Open),
        drawerContent = { DrawerContent(drawerRoutes = drawerRoutes) }) {
        Content()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(drawerRoutes: LinkedHashMap<String, List<DrawerItem>>) {
    ModalDrawerSheet() {
        drawerRoutes.forEach { (groupName, routesInTheGroup) ->
            DisplayGroupName(groupName);
            //placing the each drawer items
            routesInTheGroup.forEach { item ->
                NavigationDrawerItem(
                    icon = { Icon(item.icon, contentDescription = null) },
                    label = { Text(item.label) },
                    selected = false,
                    onClick = {

                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )
            }
        }

    }

}

@Composable
private fun DisplayGroupName(groupName: String) {
    Text(text = groupName)
}

@Composable
private fun Content() {
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
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

fun getGroup1(): List<DrawerItem> {
    return (
            listOf(
                DrawerItem("Primary", Icons.Default.Person),
                DrawerItem("Promotions", Icons.Default.Person),
                DrawerItem("Social", Icons.Default.Person),
                DrawerItem("Updates", Icons.Default.Person),
                DrawerItem("Forums", Icons.Default.Person),
            )
            )
}

fun getRecentLabels(): List<DrawerItem> {
    return (
            listOf(
                DrawerItem("[Imap]/Trash", Icons.Default.Person),
                DrawerItem("SMS", Icons.Default.Person),
            )
            )
}

fun getAllLabels(): List<DrawerItem> {
    return (
            listOf(
                DrawerItem("Started", Icons.Default.Person),
                DrawerItem("Snoozed", Icons.Default.Person),
                DrawerItem("Important", Icons.Default.Person),
                DrawerItem("Sent", Icons.Default.Person),
                DrawerItem("Schedule", Icons.Default.Person),
                DrawerItem("Outbox", Icons.Default.Person),
                DrawerItem("Drafts", Icons.Default.Person),
                DrawerItem("All mail", Icons.Default.Person),
                DrawerItem("Spam", Icons.Default.Person),
                DrawerItem("Bin", Icons.Default.Person),
            )
            )
}
fun getGoogleAppsLabels(): List<DrawerItem> {
    return (
            listOf(
                DrawerItem("Calender", Icons.Default.Person),
                DrawerItem("Contacts", Icons.Default.Person),
            )
            )
}
fun getLastLabels(): List<DrawerItem> {
    return (
            listOf(
                DrawerItem("Setting", Icons.Default.Person),
                DrawerItem("Help and feedback", Icons.Default.Person),
            )
            )
}