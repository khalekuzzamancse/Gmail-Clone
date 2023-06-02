package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection

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
        drawerGroups = DrawerItemsProvider.drawerGroups,
        onNavigate = onNavigate,
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
    Box(
    ) {

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
                    CommonTopAppbar(
                        onNavigationIconClick = openDrawer,
                        onSearchOpenerClick = {
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

@OptIn(ExperimentalMaterial3Api::class)
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

@OptIn(ExperimentalMaterial3Api::class)
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