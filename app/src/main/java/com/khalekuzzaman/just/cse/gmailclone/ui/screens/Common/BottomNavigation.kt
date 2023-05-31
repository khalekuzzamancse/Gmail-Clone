package com.khalekuzzaman.just.cse.gmailclone.ui.screens.Common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import com.khalekuzzaman.just.cse.gmailclone.EmailList
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater
import com.khalekuzzaman.just.cse.gmailclone.utils.CustomNestedScrollConnection
import com.khalekuzzaman.just.cse.gmailclone.utils.ScrollDirection

@Composable
fun BottomNavigationBar() {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null
                )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = {},
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_video_chat),
                    contentDescription = null
                )
            }
        )
    }
}

@Composable
@Preview
private fun BottomNavbarPreview() {
    BottomNavigationBar()
}

@Composable
fun ShowFAB(
    modifier: Modifier = Modifier,
    shouldShowExpandedFAB: Boolean = true,
    onClick: () -> Unit,
) {
    if (shouldShowExpandedFAB) {
        ExtendedFloatingActionButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            ComposeIcon()
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Compose"
            )
        }
    } else {
        FloatingActionButton(
            modifier = modifier,
            onClick = onClick
        ) {
            ComposeIcon()
        }
    }
}

@Composable
private fun ComposeIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_compose),
        contentDescription = null
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationDemo() {

    var shouldShowExpendedFab by remember { mutableStateOf((true))
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
            BottomNavigationBar()
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            var emails by remember {
                mutableStateOf(FakeEmailList().getFakeEmails())
            }
            //List of selected email
            var selectedEmailIds by remember {
                mutableStateOf(emptySet<Int>())
            }
            EmailList(
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
                }
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