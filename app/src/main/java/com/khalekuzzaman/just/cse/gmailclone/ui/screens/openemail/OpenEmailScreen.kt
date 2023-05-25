package com.khalekuzzaman.just.cse.gmailclone.ui.screens.openemail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
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
import com.khalekuzzaman.just.cse.gmailclone.ui.common.BookmarkIcon
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CustomIconButton
import javax.security.auth.Subject


@Composable
fun SubjectAndSenderInfo() {
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        SubjectAndBookmark(
            subjectText = "A personal acccess token(classic) has been added to your account",
            onBookmarkChange = {})
        SenderInfoHeader(
            userName = "Md Abul Kalam Azad",
            time = "5 days ago",
            profileImageId = R.drawable.ic_profile_2
        )
        EmailBody(message = "")
    }

}

@Composable
@Preview(showBackground = true)
private fun SubjectAndSenderInfoPreview() {
    SubjectAndSenderInfo()
}

@Composable
private fun EmailBody(modifier: Modifier = Modifier, message: String) {
    //This text is used a annoted string string
    val annotatedEmailString: AnnotatedString = buildAnnotatedString {
        val str = "We will hold AtCoder Grand Contest 062. This contest counts for GP30 scores.\n" +
                "\n" +
                "    Contest URL: https://atcoder.jp/contests/agc062\n" +
                "    Start Time: http://www.timeanddate.com/worldclock/fixedtime.html?iso=20230521T2100&p1=248\n" +
                "    Duration: 180 minutes\n" +
                "    Number of Tasks: 6\n" +
                "    Writer: chinerist\n" +
                "    Tester: maspy, IH19980412\n" +
                "    Rated range: 1200 ~\n" +
                "\n" +
                "The point values will be 400-700-800-1100-1300-2000.\n" +
                "\n" +
                "We are looking forward to your participation!\n" +
                "\n" +
                "- How to Unsubscribe Email Newsletters\n" +
                "1: First, please sign in => https://atcoder.jp/login\n" +
                "2: Clear all \"Notification settings\" from => https://atcoder.jp/settings\n" +
                "-----\n" +
                "AtCoder Development Team\n" +
                "https://atcoder.jp/"
        val startIndex = str.indexOf("link")
        val endIndex = startIndex + 4
        append(str)
        addStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            ),
            start = startIndex,
            end = endIndex
        )
        // attach a string annotation that stores a URL to the text "link"
        addStringAnnotation(
            tag = "Contest URL: https://atcoder.jp/contests/agc062\n",
            annotation = "Contest URL: https://atcoder.jp/contests/agc062\n",
            start = startIndex,
            end = endIndex
        )
    }

// UriHandler parse and opens URI inside AnnotatedString Item in Browser
    val uriHandler = LocalUriHandler.current

// Clickable text returns position of text that is clicked in onClick callback
    ClickableText(
        text = annotatedEmailString,
        onClick = {
            annotatedEmailString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    uriHandler.openUri(stringAnnotation.item)
                }
        }
    )

}
@Composable
@Preview(showBackground = true, showSystemUi = true)
private fun EmailBodyPreview(){
    EmailBody(message = "")
}
@Composable
private fun SubjectAndBookmark(
    modifier: Modifier = Modifier,
    subjectText: String,
    onBookmarkChange: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        SubjectShow(
            modifier.weight(1f),
            text = subjectText
        )
        BookmarkIcon(
            onBookmarkIconClick = onBookmarkChange
        )
    }

}

@Composable
fun SenderInfoHeader(
    userName: String,
    time: String,
    profileImageId: Int,
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
            painter = painterResource(id = R.drawable.ic_profile_2),
            contentDescription = null
        )
        EmailInfo(
            modifier = Modifier.weight(.5f),
            userName = userName,
            time = time,
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
) {
    Column(modifier = modifier) {
        SenderNameAndTime(userName = userName, time = time)
        Row() {
            Text(
                text = "to me"
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = null
            )

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
        userName = "Md Abul Kalam Azad",
        time = "5 days ago",
        profileImageId = R.drawable.ic_profile_2
    )
}


@Composable
@Preview(showBackground = true)
private fun SubjectAndBookmarkPreview() {
    SubjectAndBookmark(
        subjectText = "A personal acccess token(classic) has been added to your account",
        onBookmarkChange = {}
    )
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
    SubjectShow(text = "A personal acccess token(classic) has been added to your account")
}
