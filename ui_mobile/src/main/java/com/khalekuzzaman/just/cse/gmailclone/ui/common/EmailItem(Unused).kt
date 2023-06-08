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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.utils.TextFinder
import kotlin.random.Random




@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    emailModel: EmailModel,
    onLongClick: (itemID: Int) -> Unit = {},
    isSelected: Boolean = false,
    onEmailItemClick: (EmailModel) -> Unit,
    highLightedText: String="",
    onChangeBookmark: (itemID: Int) -> Unit = {},
) {

    Box(modifier = modifier
        .padding(10.dp)
        .fillMaxWidth()
        .combinedClickable(
            onClick = {
                onEmailItemClick(emailModel)
            },
            onLongClick = {
                onLongClick(emailModel.emailId)
            }
        )
        .background(
            if (isSelected) MaterialTheme.colorScheme.primaryContainer
            else MaterialTheme.colorScheme.surface,
            shape = MaterialTheme.shapes.small
        )) {
        val margin = 10.dp
        Row(
            modifier = Modifier.padding(margin)
        ) {
            //

            if (isSelected) {
                SelectedProfileImage()
            } else {
                ProfileImage(drawableResource = R.drawable.profile_image)
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                TitleAndTime(
                    emailModel.userName,
                    emailModel.timeOrDate,
                    highLightedText = highLightedText
                )
                MessageSubjectBookmark(
                    subject = emailModel.subject,
                    message = emailModel.message,
                    isBookMarked = emailModel.isBookMarked,
                    highLightedText = highLightedText,
                ) {
                    onChangeBookmark(emailModel.emailId)
                }

            }


        }
    }


}


@Composable
private fun TitleAndTime(title: String, time: String, highLightedText: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title(
            modifier = Modifier.weight(1f),
            title = title,
            highLightedText = highLightedText
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
private fun Title(modifier: Modifier = Modifier, title: String, highLightedText: String) {
    Text(
        text = getHighLightedString(title, highLightedText),
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
    subject: String,
    message: String,
    isBookMarked: Boolean = false,
    highLightedText: String,
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
            highLightedText = highLightedText
        )
        BookmarkIcon(isBookMarked = isBookMarked, onBookmarkIconClick = onBookmarkIconClick)
    }
}

@Composable
private fun SubjectAndMessage(
    modifier: Modifier = Modifier,
    subject: String, message: String, highLightedText: String,
) {
    Column(modifier = modifier) {
        Subject(subject = subject, highLightedText = highLightedText)
        Message(message = message, highLightedText = highLightedText)
    }
}

@Composable
private fun Subject(modifier: Modifier = Modifier, subject: String, highLightedText: String) {
    Text(
        modifier = modifier,
        text = getHighLightedString(subject, highLightedText),
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun Message(modifier: Modifier = Modifier, message: String, highLightedText: String) {
    Text(
        modifier = modifier,
        text = getHighLightedString(message, highLightedText),
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

fun getHighLightedString(text: String, highLightedText: String): AnnotatedString {
    val annotatedEmailString: AnnotatedString = buildAnnotatedString {
        val str = text
        append(str)
        val urls = TextFinder().findText(text, highLightedText)
        urls.forEach { pair ->
            addStyle(
                style = SpanStyle(
                    background = Color.Yellow,
                    textDecoration = TextDecoration.None
                ),
                start = pair.first,
                end = pair.second + 1
            )
        }
    }
    return annotatedEmailString
}

@Composable
private fun SelectedProfileImage(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
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
private fun SelectedImagePreview() {
    SelectedProfileImage()
}

@Composable
@Preview(showBackground = true)
private fun TitlePreview() {
    Title(title = "Md Khalekuzzaman", highLightedText = "")
}

@Composable
@Preview(showBackground = true)
private fun DateOrTimePreview() {
    DateORTime(time = "13-03-23")
}

@Composable
@Preview(showBackground = true)
private fun TitleAndDatePreview() {
    TitleAndTime(title = "Md Khaleuzzaman", time = "13-03-23", highLightedText = "")
}

@Composable
@Preview(showBackground = true)
private fun SubjectPreview() {
    Subject(subject = "This is subject...", highLightedText = "")
}

@Composable
@Preview(showBackground = true)
private fun MessagePreview() {
    Message(
        message = "Dear,Md. Khalekuzzaman.How are you?,Assuming that your are fine and toaday is..",
        highLightedText = "Md"
    )
}

@Composable
@Preview(showBackground = true)
private fun SubjectAndMessagePreview() {
    SubjectAndMessage(
        subject = "Congratulation for cloning the gmail app,with jetpack compose",
        message = "Dear,Md. Khalekuzzaman.How are you?,Assuming that your are fine and toaday is..",
        highLightedText = ""
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
private fun EmailItemPreviews() {
    Column() {
        val email = EmailModel(
            emailId = Random.nextInt(),
            userName = "Md Khalekuzzaman",
            subject = "This the subjct of the email,that will be used for testing purpose",
            message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                    " and the other tool.",
            timeOrDate = "13-03-23",
            profileImageId = R.drawable.profile_image,
            isBookMarked = false,
            sender = "khalekuzzaman91@gmail.com",
            receiver = "abulkalam80@gmail.com"
        )
        EmailItem(emailModel = email,
            onEmailItemClick = {},
            highLightedText = "MD",
        )
        EmailItem(
            emailModel = email.copy(emailId = Random.nextInt()),
            isSelected = true,
            onEmailItemClick = {},

        )
        EmailItem(
            emailModel = email.copy(emailId = Random.nextInt(), isBookMarked = true),
            onEmailItemClick = {},

        )
        EmailItem(
            emailModel = email.copy(emailId = Random.nextInt(), isBookMarked = true),
            isSelected = true,
            onEmailItemClick = {},

        )
    }

}

