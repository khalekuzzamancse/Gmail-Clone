package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection

data class BottomNavigationItem(
    val label: String,
    val iconId: Int,
)

object BottomNavigationItemInfo {
    object ItemNames {
        const val MAIL = "Mail"
        const val VIDEO_CHAT = "Video chat"
    }

    val items = listOf(
        BottomNavigationItem(ItemNames.MAIL, R.drawable.ic_email),
        BottomNavigationItem(ItemNames.VIDEO_CHAT, R.drawable.ic_video_chat),
    )
}


@Composable
fun BottomNavigationBar(
    items: List<BottomNavigationItem>,
    onBottomNavItemClick: (itemName: String) -> Unit,
) {
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
    ) {
        var selected by remember {
            mutableStateOf(items[0])
        }
        items.forEach { item ->
            NavigationBarItem(
                selected = selected == item,
                onClick = {
                    onBottomNavItemClick(item.label)
                    selected = item
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null
                    )
                }
            )
        }
    }
}

@Composable
@Preview
private fun BottomNavbarPreview() {
    BottomNavigationBar(BottomNavigationItemInfo.items) {}
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationDemo() {

    var shouldShowExpendedFab by remember {
        mutableStateOf((true))
    }
    val nestedScrollConnection = CustomNestedScrollConnection { _, scrollDir ->
        shouldShowExpendedFab = (scrollDir != ScrollDirection.UP)
    }

    Scaffold(
        modifier = Modifier.nestedScroll(nestedScrollConnection),
        floatingActionButton = {
            ShowFAB(
                shouldShowExpandedFAB = shouldShowExpendedFab,
                onClick = {}
            )

        },
        bottomBar = {
            BottomNavigationBar(BottomNavigationItemInfo.items) {}
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            var emails by remember {
                mutableStateOf(FakeEmailList.getFakeEmails())
            }
            //List of selected email
            var selectedEmailIds by remember {
                mutableStateOf(emptySet<Int>())
            }
            EmailListForSearchResult(
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
                },
                selectedEmailIds = selectedEmailIds,
                highlightedText = "",
                onEmailItemClick = {}
            )
        }

    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun BottomNavigationDemoPreview() {
    BottomNavigationDemo()
}

@Composable
@Preview(showBackground = true)
private fun FABPreviews() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ShowFAB(onClick = {}, modifier = Modifier.padding(5.dp))
        ShowFAB(
            shouldShowExpandedFAB = false,
            onClick = {},
            modifier = Modifier
        )
    }
}