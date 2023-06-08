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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerItemsProvider.drawerGroups
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection


/*
1.0 It will decide which pass to send to the CommonScreenWithModalDrawer()
1.1 Based on situation it will pass different type of content
1.2 Since it the owner of the content it will handle the content

2.0:It does not and need not how to place content

 */


@Composable
fun CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
    modifier: Modifier = Modifier,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    openDrawer: () -> Unit,
    onFabClick: () -> Unit,
    shouldShowContextualTopAppbar: Boolean,
    selectedEmailCount: Int,
    onBackArrowClick: () -> Unit,
    screenContent: @Composable () -> Unit,

    ) {
    ModalDrawer(
        modifier = modifier,
        drawerGroups = drawerGroups,
        onDrawerItemClick = onNavigate,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
    ) {
        ScreenScaffold(
            modifier = Modifier.fillMaxSize(),
            screenContent = screenContent,
            openDrawer = openDrawer,
            onFabClick = onFabClick,
            selectedEmailCount = selectedEmailCount,
            shouldShowContextualTopAppbar = shouldShowContextualTopAppbar,
            onBackArrowClick = onBackArrowClick,
        )
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenScaffold(
    modifier: Modifier = Modifier,
    screenContent: @Composable () -> Unit,
    openDrawer: () -> Unit,
    onFabClick: () -> Unit,
    selectedEmailCount: Int,
    shouldShowContextualTopAppbar: Boolean,
    onBackArrowClick: () -> Unit,
) {
    Box {

        var shouldShowExpendedFab by remember {
            mutableStateOf((true))
        }
        val nestedScrollConnection = CustomNestedScrollConnection { _, scrollDir ->
            shouldShowExpendedFab = (scrollDir != ScrollDirection.UP)
        }

        var showSearchBar by remember {
            mutableStateOf(false)
        }
        var shouldShowDialogBox by remember {
            mutableStateOf(false)
        }
        if (showSearchBar) {
            SearchBar(
                modifier = Modifier.matchParentSize(),
                onBackClick = {
                    showSearchBar = false
                }
            )
        }

        Scaffold(
            modifier = modifier
                .nestedScroll(nestedScrollConnection),
            floatingActionButton = {
                ShowFAB(
                    shouldShowExpandedFAB = shouldShowExpendedFab,
                    onClick = onFabClick
                )

            },
            topBar = {
                if (shouldShowContextualTopAppbar) {
                    ContextualTopAppbar(
                        onBackArrowClick = onBackArrowClick,
                        selectedEmailCount = selectedEmailCount,
                        onMenuItemClick = {}
                    )
                } else {
                    CommonTopAppbarForListScreen(
                        profileImageResourceId = R.drawable.ic_profile_2,
                        onNavigationIconClick = openDrawer,
                        onProfileIconClick = {
                            shouldShowDialogBox = true

                        },
                        onSearchTextClick = {
                            showSearchBar = true
                        }
                    )

                }

            },
            bottomBar = {
                BottomNavigationBar(bottomNavigationItems)
            }

        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                if (shouldShowDialogBox) {
                    Dialogue(
                        modifier = Modifier
                            .fillMaxHeight(.5f)
                            .fillMaxWidth(.8f),
                        onCrossButtonClick = {
                            shouldShowDialogBox = false
                        }
                    )
                }
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
private fun CommonScreenXPreview() {


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
            BottomNavigationBar(bottomNavigationItems)
        },
        fab = {
            ShowFAB(
                shouldShowExpandedFAB = false,
                onClick = onFabClick
            )

        },
        closeDrawer = closeDrawer,
        onDrawerItemClick = onDrawerItemClick,
        drawerState = drawerState
    ) {

    }

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
    CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
        onNavigate = {},
        closeDrawer = { /*TODO*/ },
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        openDrawer = {},
        onFabClick = {},
        shouldShowContextualTopAppbar = false,
        selectedEmailCount = 0,
        onBackArrowClick = {}

    ) {}

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonScreenDemo3() {
    CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
        onNavigate = {},
        closeDrawer = { /*TODO*/ },
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        openDrawer = {},
        onFabClick = {},
        shouldShowContextualTopAppbar = true,
        selectedEmailCount = 0,
        onBackArrowClick = {}
    ) {}

}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun CommonScreenDemo2() {
    CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
        onNavigate = {},
        closeDrawer = { /*TODO*/ },
        drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
        openDrawer = {},
        onFabClick = {},
        shouldShowContextualTopAppbar = false,
        selectedEmailCount = 0,
        onBackArrowClick = {}
    ) {}
}