package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerItemsProvider.drawerGroups
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection
import kotlinx.coroutines.launch


/*
1.0 It will decide which pass to send to the CommonScreenWithModalDrawer()
1.1 Based on situation it will pass different type of content
1.2 Since it the owner of the content it will handle the content

2.0:It does not and need not how to place content

 */




@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonListScreenPreview() {
    CommonListScreenDemo()
}

@Composable
fun CommonListScreenDemo() {
    val drawerState = DrawerState(initialValue = DrawerValue.Open)
    val coroutineScope = rememberCoroutineScope()
    val closeDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.close()
        }
    }
    val openDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.open()
        }
    }
    CommonListScreen(
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        openDrawer = openDrawer,
        profileImageResourceId = R.drawable.ic_profile_2,
        emails = FakeEmailList().getFakeEmails()
    )
}

@Composable
fun CommonListScreen(
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    openDrawer: () -> Unit,
    profileImageResourceId: Int,
    emails: List<EmailModel>,
) {

    var emails by remember {
        mutableStateOf(emails)
    }
    //List of selected email
    var selectedEmailIds by remember {
        mutableStateOf(emptySet<Int>())
    }

    var selectedEmailCount by remember {
        mutableIntStateOf(0)
    }
    val onEmailSelectedCountChange: (totalSelected: Int) -> Unit = {
        selectedEmailCount = it
    }
    val onBackArrowClick: () -> Unit = {
        selectedEmailCount = 0
        selectedEmailIds = emptySet()
    }
    val onContextualTopAppbarItemClick: (String) -> Unit = {
        Log.i("Clicked:ContextualItem->", it)

    }
    val onBottomNavigationItemClick: (String) -> Unit = {
        Log.i("Clicked:BottomNavItem->", it)

    }
    val onDrawerItemClick: (String) -> Unit = {
        Log.i("Clicked:DrawerItem->", it)
    }
    val searchTextClick: () -> Unit = {
        Log.i("Clicked:GeneralTopbar->", "searchText")
    }
    val onProfileIconClick: () -> Unit = {
        Log.i("Clicked:GeneralTopbar->", "profileIcon")
    }
    val onFabClick: () -> Unit = {
        Log.i("Clicked:FAB->", "fab")
    }
    val onChangeBookmark: (Int) -> Unit = { emailId ->
        emails = BookmarkUpdater(emails).update(emailId)
    }
    val onEmailSelectedOrDeselected: (Int) -> Unit = { emailId ->
        selectedEmailIds = if (selectedEmailIds.contains(emailId)) {
            selectedEmailIds.minus(emailId)
        } else {
            selectedEmailIds.plus(emailId)
        }
        onEmailSelectedCountChange(selectedEmailIds.size)
    }
    val onEmailItemClick: (EmailModel) -> Unit = {

    }

    CommonScreenX(
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        onDrawerItemClick = onDrawerItemClick,
        onNavigationIconClick = openDrawer,
        onSearchTextClick = searchTextClick,
        profileImageResourceId = profileImageResourceId,
        onProfileIconClick = onProfileIconClick,
        onBackArrowClick = onBackArrowClick,
        numberOfSelectedEmails = selectedEmailCount,
        onPopUpMenuItemClick = onContextualTopAppbarItemClick,
        onFabClick = onFabClick,
        onBottomNavigationIconClick = onBottomNavigationItemClick
    ) {//Screen content
        EmailList(
            emails = emails,
            onChangeBookmark = onChangeBookmark,
            onEmailSelectedOrDeselected = onEmailSelectedOrDeselected,
            selectedEmailIds = selectedEmailIds,
            onEmailItemClick = onEmailItemClick
        )
    }
}

@Composable
fun CommonScreenX(
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    onDrawerItemClick: (ItemName: String) -> Unit,
    onNavigationIconClick: () -> Unit,
    onSearchTextClick: () -> Unit,
    profileImageResourceId: Int,
    onProfileIconClick: () -> Unit,
    onBackArrowClick: () -> Unit,
    numberOfSelectedEmails: Int,
    onPopUpMenuItemClick: (itemName: String) -> Unit,
    onFabClick: () -> Unit,
    onBottomNavigationIconClick: (itemName: String) -> Unit,
    screenContent: @Composable () -> Unit,
) {
    val shouldShowContextualTopAppbar = numberOfSelectedEmails > 0
    CommonScreenSlot(

        topAppbar = {
            if (shouldShowContextualTopAppbar) {
                ContextualTopAppbar(
                    onBackArrowClick = onBackArrowClick,
                    selectedEmailCount = numberOfSelectedEmails,
                    onMenuItemClick = onPopUpMenuItemClick
                )

            } else {
                CommonTopAppbarForListScreen(
                    onNavigationIconClick = onNavigationIconClick,
                    onSearchTextClick = onSearchTextClick,
                    onProfileIconClick = onProfileIconClick,
                    profileImageResourceId = profileImageResourceId,
                )
            }

        },

        bottomAppbar = {
            BottomNavigationBar(
                BottomNavigationItemInfo.items,
                onBottomNavItemClick = onBottomNavigationIconClick
            )
        },
        fab = {
            ShowFAB(
                shouldShowExpandedFAB = false,
                onClick = onFabClick
            )

        },
        closeDrawer = closeDrawer,
        onDrawerItemClick = onDrawerItemClick,
        drawerState = drawerState,
        screenContent = screenContent
    )

}


@Composable
fun CommonScreenSlot(
    drawerState: DrawerState,
    closeDrawer: () -> Unit,
    onDrawerItemClick: (ItemName: String) -> Unit,
    topAppbar: @Composable () -> Unit,
    bottomAppbar: @Composable () -> Unit,
    fab: @Composable () -> Unit,
    screenContent: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = Modifier,
        drawerGroups = drawerGroups,
        onDrawerItemClick = onDrawerItemClick,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
    ) {
        Scaffold(
            floatingActionButton = fab,
            topBar = topAppbar, bottomBar = bottomAppbar
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                screenContent()
            }
        }
    }


}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonScreenDemo1() {
   

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonScreenDemo3() {
   

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonScreenDemo2() {
   
}