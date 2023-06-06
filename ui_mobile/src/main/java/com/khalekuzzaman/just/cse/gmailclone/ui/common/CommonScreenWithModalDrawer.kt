package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerItemsProvider.drawerGroups
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
fun CommonScreenWithModalDrawerDemo() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val openDrawer: () -> Unit = {
        coroutineScope.launch { drawerState.open() }
    }
    val closeDrawer: () -> Unit = {
        coroutineScope.launch { drawerState.close() }
    }

    CommonScreenWithModalDrawer(
        topAppbar = {

        },
        bottomBar = { BottomNavigationBar(bottomNavigationItems) },
        onDrawerItemClick = {
            Log.i("Clicked:DrawerItem", it)
        },
        closeDrawer = closeDrawer,
        drawerState = drawerState
    ) {

    }

}

@Composable
@Preview(showBackground = true)
private fun CommonScreenWithModalDrawerDemoPreview() {
    CommonScreenWithModalDrawerDemo()
}

/*
CommonScreenWithModalDrawer():
1.it just provide the  drawer and place the passing content,
2.It does not and need not know how to handle the content(except the drawer)
3.It need not and does not contain and maintain any state

Explanation:
pass the handler for the drawer because It has built in drawer positioned
It will just know how to handle the drawer and place the passed content
   to the appropriate position,
It does not and need know how to handle the passed control.
    while passing the content,use the appropriate handler to them.



 */
@Composable
fun CommonScreenWithModalDrawer(
    modifier: Modifier = Modifier,
    topAppbar: @Composable () -> Unit,
    bottomBar: @Composable () -> Unit,
    onDrawerItemClick: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    fab: @Composable () -> Unit = {},
    fabPosition: FabPosition = FabPosition.End,
    screenContent: @Composable () -> Unit,
) {
    ModalDrawer(
        modifier = modifier,
        drawerGroups = drawerGroups,
        onDrawerItemClick = onDrawerItemClick,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
    ) {
        Scaffold(
            topBar = topAppbar,
            bottomBar = bottomBar,
            floatingActionButton = fab,
            floatingActionButtonPosition = fabPosition

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
    onArchiveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onMarkAsUnReadButtonClick: () -> Unit,
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
            onArchiveButtonClick = onArchiveButtonClick,
            onBackArrowClick = onBackArrowClick,
            onDeleteButtonClick = onDeleteButtonClick,
            onMarkAsUnReadButtonClick = onMarkAsUnReadButtonClick,
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
    onArchiveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onMarkAsUnReadButtonClick: () -> Unit,
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
                        selectedEmailCount = selectedEmailCount,
                        onArchiveButtonClick = onArchiveButtonClick,
                        onBackArrowClick = onBackArrowClick,
                        onDeleteButtonClick = onDeleteButtonClick,
                        onMarkAsUnReadButtonClick = onMarkAsUnReadButtonClick
                    )
                } else {

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
        onBackArrowClick = {},
        onArchiveButtonClick = {},
        onDeleteButtonClick = {},
        onMarkAsUnReadButtonClick = {}

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
        onBackArrowClick = {},
        onArchiveButtonClick = {},
        onDeleteButtonClick = {},
        onMarkAsUnReadButtonClick = {}
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
        onBackArrowClick = {},
        onArchiveButtonClick = {},
        onDeleteButtonClick = {},
        onMarkAsUnReadButtonClick = {}
    ) {}
}