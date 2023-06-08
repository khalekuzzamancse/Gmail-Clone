package com.khalekuzzaman.just.cse.gmailclone.ui.screens

import androidx.compose.material3.DrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonScreenWithModalDrawerAndBottomNavigationAndFAB
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater


@Composable

fun CommonEmailListScreen(
    modifier: Modifier = Modifier,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    openDrawer: () -> Unit,
    onFabClick: () -> Unit,
    onEmailItemClick: (EmailModel) -> Unit,
    drawerState: DrawerState,
    emails: List<EmailModel>,
) {

    var emails by remember {
        mutableStateOf(emails)
    }
    //List of selected email
    var selectedEmailIds by remember {
        mutableStateOf(emptySet<Int>())
    }
    var shouldShowContextualTopAppbar by remember {
        mutableStateOf(false)
    }
    var selectedEmailCount by remember {
        mutableIntStateOf(0)
    }
    val onEmailSelectedCountChange: (totalSelected: Int) -> Unit = {
        selectedEmailCount = it
        shouldShowContextualTopAppbar = (it > 0)
    }
    val onBackArrowClick: () -> Unit = {
        shouldShowContextualTopAppbar = false
        selectedEmailCount = 0
        selectedEmailIds= emptySet()
    }




    CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
        modifier = modifier,
        onNavigate = onNavigate,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        openDrawer = openDrawer,
        onFabClick = onFabClick,
        shouldShowContextualTopAppbar = shouldShowContextualTopAppbar,
        selectedEmailCount = selectedEmailCount,
        onBackArrowClick = onBackArrowClick
    ) {
        EmailList(
            emails = emails,
            onChangeBookmark = { emailId ->
                emails = BookmarkUpdater(emails).update(emailId)
            },
            onEmailSelectedOrDeselected = { emailId ->
                selectedEmailIds = if (selectedEmailIds.contains(emailId)) {
                    selectedEmailIds.minus(emailId)
                } else {
                    selectedEmailIds.plus(emailId)
                }
                onEmailSelectedCountChange(selectedEmailIds.size)
            },
            selectedEmailIds = selectedEmailIds,
            onEmailItemClick =onEmailItemClick
        )
    }
}


@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun CommonEmailListScreenPreview() {

}
