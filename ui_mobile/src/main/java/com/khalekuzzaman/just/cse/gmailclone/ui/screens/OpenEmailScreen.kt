package com.khalekuzzaman.just.cse.gmailclone.ui.screens


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmail
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BookmarkIcon
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BottomNavigationBar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BottomNavigationItemInfo
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ContextualTopAppbar
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CustomIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.common.FormLayout
import com.khalekuzzaman.just.cse.gmailclone.utils.TextFinder

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ReadEmailScreenPreview() {
    ReadEmailScreen(email = FakeEmail.email)
}

@Composable
fun ReadEmailScreen(
    email: EmailModel,
) {
    var shouldShowRecipientInfo by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            ContextualTopAppbar(
                onBackArrowClick = { /*TODO*/ },
                selectedEmailCount = 0,
                onMenuItemClick = {}
            )
        },
        bottomBar = {
            BottomNavigationBar(
                items = BottomNavigationItemInfo.items,
                onBottomNavItemClick = {}
            )
        }
    ) {
        ReadEmailLayoutSlot(
            modifier = Modifier.padding(it),
            subjectSection = {
                SubjectShow(text = email.subject)
            },
            bookMarkSection = {
                BookmarkIcon(
                    onBookmarkIconClick = {}
                )
            },
            moreInfoSection = {
                SenderInfoHeader(
                    userName = email.userName,
                    time = email.timeOrDate,
                    profileImageId = R.drawable.ic_profile_2,
                    onExpandClick = { shouldShowRecipientInfo = !shouldShowRecipientInfo }
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
                EmailBody(message =email.message)
            },
            footerSection = {
                BottomButtonSection(
                    onReplyClick = { /*TODO*/ },
                    onReplyAllClick = { /*TODO*/ },
                    onForwardClick = {}
                )
            }

        )

    }

}

@Preview(showBackground = true)
@Composable
fun ReadEmailLayoutSlotPreview() {
    var shouldShowRecipientInfo by remember {
        mutableStateOf(false)
    }
    ReadEmailLayoutSlot(
        subjectSection = {
            SubjectShow(text = FakeEmail.email.subject)
        },
        bookMarkSection = {
            BookmarkIcon(
                onBookmarkIconClick = {}
            )
        },
        moreInfoSection = {
            SenderInfoHeader(
                userName = FakeEmail.email.userName,
                time = FakeEmail.email.timeOrDate,
                profileImageId = R.drawable.ic_profile_2,
                onExpandClick = { shouldShowRecipientInfo = !shouldShowRecipientInfo }
            )

            if (shouldShowRecipientInfo) {
                EmailRecipientInfo(
                    from = FakeEmail.email.sender,
                    to = FakeEmail.email.receiver,
                    date = "26 Mar 2023, 8:26 pm",
                )
            }
        },
        messageSection = {
            EmailBody(message = FakeEmail.email.message)
        },
        footerSection = {
            BottomButtonSection(
                onReplyClick = { /*TODO*/ },
                onReplyAllClick = { /*TODO*/ },
                onForwardClick = {}
            )
        }

    )

}

@Composable
fun ReadEmailLayoutSlot(
    modifier: Modifier = Modifier,
    subjectSection: @Composable () -> Unit,
    bookMarkSection: @Composable () -> Unit,
    moreInfoSection: @Composable () -> Unit,
    messageSection: @Composable () -> Unit,
    footerSection: @Composable () -> Unit,
) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) { subjectSection() }
            bookMarkSection()
        }
        moreInfoSection()
        Box(Modifier.weight(1f)) { messageSection() }
            footerSection()

    }


}


@Composable
private fun EmailBody(modifier: Modifier = Modifier, message: String) {
    SelectionContainer {
        val annotatedEmailString: AnnotatedString = buildAnnotatedString {
            append(message)
            val urls = TextFinder().findUrls(message)
            urls.forEach { pair ->
                addStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = pair.first,
                    end = pair.second + 1
                )
            }

        }
        ClickableText(
            modifier = modifier,
            text = annotatedEmailString,
            onClick = {

            }
        )
    }


}

@Composable
private fun EmailRecipientInfo(
    from: String,
    to: String,
    date: String,
) {

    Surface(
        tonalElevation = 5.dp
    ) {
        FormLayout(eachRow1stChildMaxWidth = 100.dp) {
            Text(text = "From")
            Text(text = from)
            Text(text = "to")
            Text(text = to)
            Text(text = "Date")
            Text(text = date)
        }
    }


}

@Composable
@Preview(showBackground = true)
private fun EmailRecipientInfoPreview() {
    EmailRecipientInfo(
        from = "khalekuzzaman91@gmail.com",
        to = "khalekuzzaman91@gmail.com",
        date = "26 Mar 2023, 8:26 pm",
    )
}

@Composable
private fun BottomButtonSection(
    modifier: Modifier = Modifier,
    onReplyClick: () -> Unit,
    onReplyAllClick: () -> Unit,
    onForwardClick: () -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_reply, label = "Reply", onClick = {})
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_reply_all, label = "Reply all", onClick = {})
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_forward, label = "Forward", onClick = {})
    }

}

@Composable
@Preview(showBackground = true)
private fun BottomButtonSectionPreview() {
    BottomButtonSection(
        onReplyClick = {},
        onReplyAllClick = {},
        onForwardClick = {}
    )
}

@Composable
private fun BottomSectionButton(
    modifier: Modifier = Modifier,
    leftIconId: Int,
    label: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(3.dp),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        //using content padding allows to do not shrink the button
        //when it has small size.it shrink all the button when width is
        //less
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = ButtonDefaults.buttonElevation(5.dp)
    ) {
        Icon(painter = painterResource(id = leftIconId), contentDescription = null)
        Text(
            maxLines = 1,
            text = label,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomSectionButtonPreview() {
    BottomSectionButton(leftIconId = R.drawable.ic_reply, label = "Reply") {
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = false)
private fun EmailBodyPreview() {
    EmailBody(message = FakeEmail.email.message)
}


@Composable
fun SenderInfoHeader(
    userName: String,
    time: String,
    profileImageId: Int,
    onExpandClick: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier
                .weight(.1f)
                .padding(5.dp),
            painter = painterResource(profileImageId),
            contentDescription = null
        )
        EmailInfo(
            modifier = Modifier.weight(.5f),
            userName = userName,
            time = time,
            onExpandClick = onExpandClick
        )
        CommonIconButton(
            modifier = Modifier.weight(.1f),
            resourceId = R.drawable.ic_reply
        )
        CommonIconButton(
            modifier = Modifier.weight(.1f),
            imageVector = Icons.Default.MoreVert
        )
    }
}

@Composable
private fun EmailInfo(
    modifier: Modifier = Modifier,
    userName: String, time: String,
    onExpandClick: () -> Unit,
) {
    Column(modifier = modifier) {
        SenderNameAndTime(userName = userName, time = time)
        Row {
            Text(
                text = "to me"
            )
            CustomIconButton(onClick = onExpandClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_down_arrow),
                    contentDescription = null
                )
            }

        }
    }

}

@Composable
private fun SenderNameAndTime(userName: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SenderName(
            modifier = Modifier.weight(1f),
            title = userName
        )
        DateORTime(time = time)
    }
}

@Composable
private fun SenderName(modifier: Modifier = Modifier, title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        //Make sure the when the content of the title increase
        //Or the size of the title then the time do not shrink or disappear
        // Ensure the first child takes the remaining space
        modifier = modifier
    )
}

@Composable
private fun DateORTime(modifier: Modifier = Modifier, time: String) {
    Text(
        text = time,
        style = MaterialTheme.typography.labelSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Composable
@Preview(showBackground = true)
private fun SenderHeaderPreview() {
    SenderInfoHeader(
        userName = "Mr. Bean",
        time = "5 days ago",
        profileImageId = R.drawable.ic_profile_2
    ) {}
}


@Composable
private fun SubjectShow(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        fontSize = 20.sp,
        modifier = modifier,
        text = text,
        style = MaterialTheme.typography.bodyLarge
    )
}

@Composable
@Preview(showBackground = true)
private fun SubjectPreview() {
    SubjectShow(text = "A personal access token(classic) has been added to your account")
}
