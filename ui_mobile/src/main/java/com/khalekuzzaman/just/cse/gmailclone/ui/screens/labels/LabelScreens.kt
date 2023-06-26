package com.khalekuzzaman.just.cse.gmailclone.ui.screens.labels

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonScreenX
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.common.SearchBar
import com.khalekuzzaman.just.cse.gmailclone.utils.BookmarkUpdater


@Composable
@Preview(showSystemUi = true, showBackground = true)
private fun ScreenDemo() {
    CommonLabelListScreen(
        onDrawerItemClick = {},
        onBottomNavigationIconClick = {},
        closeDrawer = {},
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
        openDrawer = {},
        profileImageResourceId = R.drawable.ic_profile_2,
        onFabIconClick = {},
        onEmailClick = {},
        emails = FakeEmailList.getFakePrimaryEmails()
    )
}


@Composable
fun CommonLabelListScreen(
    onDrawerItemClick: (ItemName: String) -> Unit,
    onBottomNavigationIconClick: (itemName: String) -> Unit,
    closeDrawer: () -> Unit,
    drawerState: DrawerState,
    openDrawer: () -> Unit,
    profileImageResourceId: Int,
    onFabIconClick: () -> Unit,
    onEmailClick: (EmailModel) -> Unit,
    emails: List<EmailModel>,
) {

    //
    var showSearchBox by remember {
        mutableStateOf(false)
    }
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
        onBottomNavigationIconClick(it)
        Log.i("Clicked:BottomNavItem->", it)

    }
    val onDrawerItemClick2: (String) -> Unit = {
        onDrawerItemClick(it)
        Log.i("Clicked:DrawerItem->", it)
    }
    val searchTextClick: () -> Unit = {
        showSearchBox = true
        Log.i("Clicked:GeneralTop bar->", "searchText")
    }
    val onProfileIconClick: () -> Unit = {
        Log.i("Clicked:GeneralTop bar->", "profileIcon")
    }
    val onFabClick: () -> Unit = {
        onFabIconClick()
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
        onEmailClick(it)
    }
    val onSearchBoxBackIconClick: () -> Unit = {
        showSearchBox = false
    }
    val searchBox: @Composable () -> Unit = {
        SearchBar(onBackClick = onSearchBoxBackIconClick)
    }
    var label by remember { mutableStateOf("") }


    val emptyComposable: @Composable () -> Unit = {}
    val dismissSheet: () -> Unit = { label = "" }

    CommonScreenX(
        closeDrawer = closeDrawer,
        drawerState = drawerState,
        onDrawerItemClick = onDrawerItemClick2,
        onNavigationIconClick = openDrawer,
        onSearchTextClick = searchTextClick,
        profileImageResourceId = profileImageResourceId,
        onProfileIconClick = onProfileIconClick,
        onBackArrowClick = onBackArrowClick,
        numberOfSelectedEmails = selectedEmailCount,
        onPopUpMenuItemClick = onContextualTopAppbarItemClick,
        onFabClick = onFabClick,
        onBottomNavigationIconClick = onBottomNavigationItemClick,
        overlappingFullScreenContent =
        {
            when (label) {
                DropDownLabels.LABEL -> LabelSheet(
                    onChecked = {},
                    onCrossButtonClick = dismissSheet,
                    list = labelItemList
                )

                DropDownLabels.TO ->
                    BottomSheetRecipient(
                        onCrossButtonClick = dismissSheet,
                        list = FakeEmailList.getFakeEmails(),
                        title = "TO"
                    )

                DropDownLabels.FROM ->
                    BottomSheetRecipient(
                        onCrossButtonClick = dismissSheet,
                        list = FakeEmailList.getFakeEmails(),
                        title = "FROM"
                    )

                DropDownLabels.DATE -> DatesBottomSheet(
                    onCrossClick = dismissSheet,
                    dateOrderList
                )

                else -> emptyComposable()
            }
        }

    ) {//Screen content
        Column {
            LabelButtons(
                onButtonClick = {labelName->
                    label = labelName
                    Log.i("LabelClick:",label)
                }

            )
            EmailList(
                emails = emails,
                onChangeBookmark = onChangeBookmark,
                onEmailSelectedOrDeselected = onEmailSelectedOrDeselected,
                selectedEmailIds = selectedEmailIds,
                onEmailItemClick = onEmailItemClick
            )

        }

    }
}

object DropDownLabels {
    const val LABEL = "Label"
    const val FROM = "From"
    const val TO = "To"
    const val ATTACHMENT = "Attachment"
    const val DATE = "Date"
    const val IS_UNREAD = "Is unread"
    const val EXCLUDE_CALENDAR_UPDATES = "Exclude calendar updates"
}

@Composable
fun LabelButtons(
    onButtonClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.horizontalScroll(rememberScrollState())
    ) {
        // Usage
        DropDownButton(
            onClick = { onButtonClick(DropDownLabels.LABEL) },
            label = DropDownLabels.LABEL
        )
        DropDownButton(
            onClick = { onButtonClick(DropDownLabels.FROM) },
            label = DropDownLabels.FROM
        )
        DropDownButton(
            onClick = { onButtonClick(DropDownLabels.TO) },
            label = DropDownLabels.TO
        )
        DropDownButton(
            onClick = { onButtonClick(DropDownLabels.ATTACHMENT) },
            label = DropDownLabels.ATTACHMENT
        )
        DropDownButton(
            onClick = { onButtonClick(DropDownLabels.DATE) },
            label = DropDownLabels.DATE
        )
        DropDownButtonWithoutIcon(
            onClick = { onButtonClick(DropDownLabels.IS_UNREAD) },
            label = DropDownLabels.IS_UNREAD
        )
        DropDownButtonWithoutIcon(
            onClick = { onButtonClick(DropDownLabels.EXCLUDE_CALENDAR_UPDATES) },
            label = DropDownLabels.EXCLUDE_CALENDAR_UPDATES
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun DropDownButtonPreview() {
    LabelButtons(
        onButtonClick = {}
    )
}


@Composable
fun DropDownButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
) {
    Button(
        modifier = modifier
            .padding(4.dp)
            .wrapContentWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        )

    ) {
        Text(text = label)
        Icon(
            painter = painterResource(id = R.drawable.ic_down_button),
            contentDescription = null
        )


    }
}

@Composable
fun DropDownButtonWithoutIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: String,
) {
    Button(
        modifier = modifier
            .padding(4.dp)
            .wrapContentWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 5.dp
        )

    ) {
        Text(text = label)
    }
}