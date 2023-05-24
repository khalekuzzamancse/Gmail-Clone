package com.khalekuzzaman.just.cse.gmailclone

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRowDefaults.contentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DemoList() {
    val items = (0..100).toList() // Example list of items
    LazyColumn {
        items(items) { item ->
            EmailItem()
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailItem(
    onLongClick: () -> Unit = {},

    ) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    Box(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .combinedClickable(
            onClick = {},
            onLongClick = { isSelected = !isSelected }
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
            if (isSelected) {
                SelectedProfileImage()
            } else {
                ProfileImage(drawableResource = R.drawable.profile_image, description = "")
            }

            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                TitleAndTime(
                    "Md Khalekuzzaman ",
                    "13-03-23"
                )
                MessageSubjectBookmark(
                    subject = "This the subjct of the email,that will be used for testing purpose",
                    message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose and the other tool."
                )

            }


        }
    }


}


@Composable
fun TitleAndTime(title: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Title(
            modifier = Modifier.weight(1f),
            title = title
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
    )
}

@Composable
private fun Title(modifier: Modifier = Modifier, title: String) {
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
fun MessageSubjectBookmark(subject: String, message: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        SubjectAndMessage(
            modifier = Modifier.weight(1f),
            subject = subject,
            message = message
        )
        BookmarkIcon(
        )
    }
}

@Composable
fun SubjectAndMessage(
    modifier: Modifier = Modifier,
    subject: String, message: String,
) {
    Column(modifier = modifier) {
        Subject(subject = subject)
        Message(message = message)
    }
}

@Composable
fun Subject(modifier: Modifier = Modifier, subject: String) {
    Text(
        modifier = modifier,
        text = subject,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun Message(modifier: Modifier = Modifier, message: String) {
    Text(
        modifier = modifier,
        text = message,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}


@Composable
fun BookmarkIcon(modifier: Modifier = Modifier) {
    var bookmarked by remember {
        mutableStateOf(false)
    }
    CustomIconButton(
        onClick = { bookmarked = !bookmarked },
        modifier = modifier

    ) {
        val icon = if (bookmarked) R.drawable.ic_bookmarked
        else R.drawable.ic_bookmark
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
    }

}

@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    /*
    Note:
    The icon button that is provided by compose library has some minimum size so that is can
    match the target touch,but in this case we want the icon button size,should be the same as the
    icon size so that we can easily place it with the other content,
    that is why we we edited the IconButton() source code and create a customIcon button
    that will take the exact size of it content size

     */

    Box(
        modifier =
        modifier
            .wrapContentSize()
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides contentColor, content = content)
    }
}

@Composable
fun ProfileImage(
    drawableResource: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Image(
        modifier = modifier
            .size(40.dp)
            .clip(CircleShape),
        painter = painterResource(id = drawableResource),
        contentDescription = description,
    )
}

@Composable
fun SelectedProfileImage(modifier: Modifier = Modifier) {
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


@Composable
@Preview(showBackground = true)
private fun SelectedImagePreview() {
    SelectedProfileImage()
}

@Composable
@Preview(showBackground = true)
private fun MessageAndBookmarkPreview() {
    // MessageAndBookMark(message = "Hi")
}

@Composable
@Preview(showBackground = true)
private fun InboxEmailListPreview() {
    //  DemoList()
}


@Composable
@Preview(showBackground = true)
private fun EmailItemPreview() {
    EmailItem()
}

@Composable
@Preview(showBackground = true)
private fun SelectedEmailItemPreview() {
    EmailItem()
}
