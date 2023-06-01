package com.khalekuzzaman.just.cse.gmailclone.ui.screens.Common

import android.annotation.SuppressLint
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
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ShowFAB
import com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.DrawerItemsProvider
import com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.ModalDrawer
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
    modifier: Modifier = Modifier,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    openDrawer: () -> Unit,
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
            openDrawer = openDrawer
        )
    }

}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenScaffold(
    modifier: Modifier = Modifier,
    screenContent: @Composable () -> Unit,
    openDrawer: () -> Unit,
) {
    var selectedEmails by remember {
        mutableStateOf(0)
    }

    var shouldShowExpendedFab by remember {
        mutableStateOf((true))
    }
    val nestedScrollConnection = CustomNestedScrollConnection { _, scrollDir ->
        shouldShowExpendedFab = (scrollDir != ScrollDirection.UP)
    }
    Scaffold(
        modifier = modifier
            .nestedScroll(nestedScrollConnection),
        floatingActionButton = {
            ShowFAB(
                shouldShowExpandedFAB = shouldShowExpendedFab,
                onClick = {}
            )

        },
        topBar = {
            if (selectedEmails <= 0)
                CommonTopAppbar(
                    onNavigationIconClick = openDrawer
                )
            else
                ContextualTopAppbar(
                    selectedEmails = selectedEmails
                )
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

@OptIn(ExperimentalMaterial3Api::class)
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
        openDrawer = {}
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
       openDrawer = {}
    ) {}
}