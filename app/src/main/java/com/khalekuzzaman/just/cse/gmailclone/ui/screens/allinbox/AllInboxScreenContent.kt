package com.khalekuzzaman.just.cse.gmailclone.ui.screens.allinbox

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.EmailList
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonScreenWithModalDrawerAndBottomNavigationAndFAB
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllInboxScreen(
    modifier: Modifier = Modifier,
    onNavigate: (navigateTo: String) -> Unit,
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    openDrawer: () -> Unit,
) {

    CommonScreenWithModalDrawerAndBottomNavigationAndFAB(
        modifier = modifier,
        onNavigate = onNavigate,
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        openDrawer = openDrawer
    ) {
        //Screen Content
        AllInboxScreenContent(
            onEmailSelectedCountChange = {
            })

    }
}

@Composable
fun AllInboxScreenContent(
    modifier: Modifier = Modifier,
    onChangeBookmark: (itemID: Int) -> Unit = {},
    onEmailSelectedCountChange: (totalSelected: Int) -> Unit,
) {

    var emails by remember {
        mutableStateOf(FakeEmailList().getFakeEmails())
    }
    //List of selected email
    var selectedEmailIds by remember {
        mutableStateOf(emptySet<Int>())
    }

    EmailList(
        modifier = modifier,
        emails = emails,
        onChangeBookmark = { emailId ->
            emails = BookmarkUpdater(emails).update(emailId)
        },
        selectedEmailIds = selectedEmailIds,
        onEmailSelectedOrDeselected = { emailId ->
            selectedEmailIds = if (selectedEmailIds.contains(emailId)) {
                selectedEmailIds.minus(emailId)
            } else {
                selectedEmailIds.plus(emailId)
            }
            onEmailSelectedCountChange(selectedEmailIds.size)
        }
    )

}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun AllInboxScreenPreview() {
    AllInboxScreenContent(
        onEmailSelectedCountChange = {}
    )
}
