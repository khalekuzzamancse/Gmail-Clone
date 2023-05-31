package com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawer(
    drawerGroups: List<DrawerGroup>,
    //Make sure later,that the  hashmap is immutable,
    //so that no one can add or remove item from it
    //also mark it as stable or immutable so  that
    //compose compiler can optimize it
    onNavigate: (navigateTo: String) -> Unit,
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Open),
    coroutineScope: CoroutineScope,
) {
    val scrollState = rememberScrollState()
    val drawerWidth = with(LocalDensity.current) {
        (LocalConfiguration.current.screenWidthDp * 0.9).dp
    }
    ModalNavigationDrawer(
        modifier = Modifier
            .verticalScroll(scrollState)
            .width(drawerWidth),
        drawerState = drawerState,
        drawerContent = {
            DrawerContent(
                drawerGroups,
                drawerState = drawerState,
                onNavigate = onNavigate,
                coroutineScope = coroutineScope
            )
        }) {
        Content()

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DrawerContent(
    drawerGroups: List<DrawerGroup>,
    onNavigate: (navigateTo: String) -> Unit,
    coroutineScope: CoroutineScope,
    drawerState: DrawerState,
) {
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
                        onNavigate(drawerItem.label)
                        coroutineScope.launch {
                            drawerState.close()
                        }
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

@Composable
private fun Content() {
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ModalDrawerPreview() {

    ModalDrawer(
        drawerGroups = DrawerItemsProvider().drawerGroups,
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        coroutineScope = rememberCoroutineScope(),
        onNavigate = {},
    )
}







