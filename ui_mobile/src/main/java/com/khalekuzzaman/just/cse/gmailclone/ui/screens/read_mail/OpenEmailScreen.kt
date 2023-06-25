package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmail
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BottomNavigationBar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BottomNavigationItemInfo
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.common.PopUpMenuItemName

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ReadEmailScreenPreview() {
    ReadEmailScreen(
        email = FakeEmail.email,
        onForwardButtonClick = {},
        onMenuItemClick = {},
        onTopBarMenuItemClick = {},
        onReplyAllButtonClick = {},
        onReplyButtonClick = {},
        onBackArrowClick = {},
        onBookmarkIconClick = {},
        onBottomNavItemClick = {},
        isExpandedScreen = false
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.TABLET
)
@Composable
fun ReadEmailScreenTabletPreview() {
    ReadEmailScreen(
        email = FakeEmail.email,
        onForwardButtonClick = {},
        onMenuItemClick = {},
        onTopBarMenuItemClick = {},
        onReplyAllButtonClick = {},
        onReplyButtonClick = {},
        onBackArrowClick = {},
        onBookmarkIconClick = {},
        onBottomNavItemClick = {},
        isExpandedScreen = true
    )
}


@Composable
fun ReadEmailScreen(
    email: EmailModel,
    onForwardButtonClick: () -> Unit,
    onMenuItemClick: (menuItem: String) -> Unit,
    onReplyButtonClick: () -> Unit,
    onReplyAllButtonClick: () -> Unit,
    onTopBarMenuItemClick: (menuItem: String) -> Unit,
    onBackArrowClick: () -> Unit,
    onBookmarkIconClick: () -> Unit,
    onBottomNavItemClick: (String) -> Unit,
    isExpandedScreen: Boolean,
) {
    var shouldShowRecipientInfo by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            ContextualTopAppbar(
                shouldShowBackArrow = !isExpandedScreen,
                onBackArrowClick = onBackArrowClick,
                selectedEmailCount = 0,
                onMenuItemClick = onTopBarMenuItemClick,
                menuItems = PopUpMenuItemName.listForReadEmailScreenTopBarMenu
            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = BottomNavigationItemInfo.items,
                onBottomNavItemClick = onBottomNavItemClick
            )
        }
    ) {
        ReadEmailLayoutSlot(
            modifier = Modifier.padding(it),
            subjectSection = {
                SubjectAndBookmarkRow(
                    onBookmarkIconClick = onBookmarkIconClick,
                    isBookmarked = false,
                    subject = email.subject
                )
            },
            moreInfoSection = {
                SenderInfoHeader(
                    userName = email.userName,
                    time = email.timeOrDate,
                    profileImageId = R.drawable.ic_profile_2,
                    onExpandClick = { shouldShowRecipientInfo = !shouldShowRecipientInfo },
                    onMenuItemClick = onMenuItemClick,
                    onReplyIconArrowClick = onReplyButtonClick,
                    onForwardArrowClick = onForwardButtonClick,
                    menuItems = PopUpMenuItemName.listForReadEmailScreenInfo
                )

                if (shouldShowRecipientInfo) {
                    EmailRecipientInfo(
                        from = email.sender,
                        to = email.receiver,
                        date = email.timeOrDate,
                    )
                }
            },
            messageSection = {
                EmailBody(message = email.message)
            }
        ,
            footerSection = {
                FooterSection(
                    onReplyButtonClick = onReplyButtonClick,
                    onReplyAllButtonClick = onReplyAllButtonClick,
                    onForwardButtonClick = onForwardButtonClick,
                )
            }

        )

    }

}

