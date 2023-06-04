package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import kotlin.random.Random


@Composable
fun EmailList(
    modifier: Modifier = Modifier,
    emails: List<EmailModel>,
    onBookIconClick: (EmailModel) -> Unit,
    onEmailClick: (EmailModel) -> Unit,
    highlightedText: String,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(items = emails, key = { it.emailid }) { email ->
            EmailItem(
                email = email,
                onEmailItemClick = {
                    onEmailClick(email)
                },
                highLightedText = highlightedText,
                onChangeBookmark = {
                    onBookIconClick(email)
                }
            )

        }
    }
}

/*
The responsibility of the EmailItem() compose:
It will takes the email
From the email it will make the annotate the texts
and then call the EmailItemWithHighLight() composable
so there is only single responsibility of this composable,
 */
@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    email: EmailModel,
    onEmailItemClick: () -> Unit,
    onChangeBookmark: () -> Unit,
    highLightedText: String,
) {
    val highLightedSubject = getHighLightedString(email.subject, highLightedText)
    val highLightedMessage = getHighLightedString(email.message, highLightedText)
    val highLightedUserName = getHighLightedString(email.userName, highLightedText)
    EmailItemWithHighLight(
        modifier = modifier,
        userName = highLightedUserName,
        subject = highLightedSubject,
        message = highLightedMessage,
        onEmailItemClick = onEmailItemClick,
        onChangeBookmark = onChangeBookmark,
        isBookmarked = email.isBookMarked,
        timeOrDate = email.timeOrDate
    )

}

/*
The responsibility of the  EmailItemWithHighLight() compose:
It will takes userName,subject,message,time and then it will
layout it userName,subject,...at appropritat positon.
so Basically it will layout the email component,so it  has the
reposibility to layout,if the layout need to change then only this function
needed to change
 */
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailItemWithHighLight(
    modifier: Modifier = Modifier,
    userName: AnnotatedString,
    subject: AnnotatedString,
    message: AnnotatedString,
    onEmailItemClick: () -> Unit,
    onChangeBookmark: () -> Unit,
    isBookmarked: Boolean,
    timeOrDate: String,
) {

    Box(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth()
            .combinedClickable(
                onClick = onEmailItemClick

            )
            .background(
                MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            )
    ) {
        val margin = 10.dp
        Row(
            modifier = Modifier.padding(margin)
        ) {

            ProfileImage(drawableResource = R.drawable.profile_image)
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                TitleAndTime(
                    title = userName,
                    time = timeOrDate,
                )
                MessageSubjectBookmark(
                    subject = subject,
                    message = message,
                    isBookMarked = isBookmarked,
                ) {
                    onChangeBookmark()
                }
            }


        }
    }

}


@Composable
private fun TitleAndTime(title: AnnotatedString, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title(
            modifier = Modifier.weight(1f),
            title = title,
        )
        DateORTime(time = time)
    }
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
private fun Title(modifier: Modifier = Modifier, title: AnnotatedString) {
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
private fun MessageSubjectBookmark(
    subject: AnnotatedString,
    message: AnnotatedString,
    isBookMarked: Boolean = false,
    onBookmarkIconClick: () -> Unit = {},

    ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        SubjectAndMessage(
            modifier = Modifier.weight(1f),
            subject = subject,
            message = message,
        )
        BookmarkIcon(isBookMarked = isBookMarked, onBookmarkIconClick = onBookmarkIconClick)
    }
}

@Composable
private fun SubjectAndMessage(
    modifier: Modifier = Modifier,
    subject: AnnotatedString, message: AnnotatedString,
) {
    Column(modifier = modifier) {
        Subject(subject = subject)
        Message(message = message)
    }
}

@Composable
private fun Subject(modifier: Modifier = Modifier, subject: AnnotatedString) {
    Text(
        modifier = modifier,
        text = subject,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun Message(modifier: Modifier = Modifier, message: AnnotatedString) {
    Text(
        modifier = modifier,
        text = message,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}


//---------------------
//---------------------
//---------------------
//---------------------


@Composable
@Preview(showBackground = true)
private fun ProfileImagePreview() {
    ProfileImage(drawableResource = R.drawable.profile_image)
}


@Composable
@Preview(showBackground = true)
private fun TitlePreview() {
    Title(title = getHighLightedString("Md Khalekuzzaman", "md"))
}

@Composable
@Preview(showBackground = true)
private fun DateOrTimePreview() {
    DateORTime(time = "13-03-23")
}

@Composable
@Preview(showBackground = true)
private fun TitleAndDatePreview() {
    TitleAndTime(
        title = getHighLightedString(
            "Md Khalekuzzaman",
            "md"
        ),
        time = "13-03-23"
    )
}

@Composable
@Preview(showBackground = true)
private fun SubjectPreview() {
    Subject(
        subject = getHighLightedString("This is subject...", "je")
    )
}

@Composable
@Preview(showBackground = true)
private fun MessagePreview() {
    Message(
        message = getHighLightedString(
            "Dear,Md. Khalekuzzaman.How are you?," +
                    "Assuming that your are fine and toaday is..", "zza"
        )
    )
}

@Composable
@Preview(showBackground = true)
private fun SubjectAndMessagePreview() {
    SubjectAndMessage(
        subject = getHighLightedString(
            "Dear,Md. Khalekuzzaman.How are you?," +
                    "Assuming that your are fine and toaday is..", "are"
        ),
        message = getHighLightedString(
            "Dear,Md. Khalekuzzaman.How are you?,Assuming that your are fine and toaday is..",
            "ine"
        ),
    )
}

@Composable
@Preview(showBackground = true)
private fun BookmarkIconPreview() {
    Column {
        BookmarkIcon(isBookMarked = false)
        BookmarkIcon(isBookMarked = true)
    }
}


@Composable
@Preview(showBackground = true)
private fun EmailItemHightLightPreviews() {
    val userName = getHighLightedString("Md Khalekuzzaman", "lek")
    val subject = getHighLightedString(
        "This the subjct of the email,that will be used for testing purpose", "s"
    )
    val message = getHighLightedString(
        "Congratual Md,Abul ,this a gmail clone app,made " +
                "using jetpack compose" +
                " and the other tool.", "md"
    )
    val timeOrDate = "13-04-23";

    /*
    ------------
    ----------
     */
    Column() {
        EmailItemWithHighLight(
            onEmailItemClick = {},
            userName = userName,
            subject = subject,
            message = message,
            timeOrDate = timeOrDate,
            isBookmarked = false,
            onChangeBookmark = {}
        )
        //
        EmailItemWithHighLight(
            onEmailItemClick = {},
            userName = userName,
            subject = subject,
            message = message,
            timeOrDate = timeOrDate,
            isBookmarked = true,
            onChangeBookmark = {}
        )
    }

}

@Composable
@Preview(showBackground = true)
private fun EmailItemPreviews() {
    val email = EmailModel(
        emailid = Random.nextInt(),
        userName = "Md Khalekuzzaman",
        subject = "This the subjct of the email,that will be used for testing purpose",
        message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                " and the other tool.",
        isBookMarked = false,
        timeOrDate = "13-03-23",
        profileImageId = R.drawable.profile_image
    )

    Column() {
        EmailItem(
            email = email,
            onEmailItemClick = { /*TODO*/ },
            highLightedText = "md",
            onChangeBookmark = {}
        )
        EmailItem(
            email = email.copy(isBookMarked = true),
            onEmailItemClick = { /*TODO*/ },
            highLightedText = "a",
            onChangeBookmark = {}
        )
    }

}

