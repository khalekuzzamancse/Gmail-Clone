package com.khalekuzzaman.just.cse.gmailclone

import android.service.quicksettings.Tile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.concurrent.timer
@Composable
fun DemoList(){
    val items = (0..100).toList() // Example list of items
    LazyColumn {
        items(items) { item ->
          EmailItem()
        }
    }
}

@Composable
fun EmailItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        ProfileImage(drawableResource = R.drawable.profile_image, description = "")
        Column(

        ) {
            TitleAndTime("Md Khalekuzzaman", "13-03-23")
            Subject("This the Fisglsglskglskglsgklsgklsgklsgklsgklsgklkrst Mail")
            MessageAndBookMark(message = "Hello this the first message")

        }
    }

}

@Composable
fun Subject(subject: String) {
    Text(
        text = subject,
        style = MaterialTheme.typography.bodyLarge,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
fun TitleAndTime(title: String, time: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            //Make sure the when the content of the title increase
            //Or the size of the title then the time do not shrink or disappear
            // Ensure the first child takes the remaining space
            modifier = Modifier.weight(1f)
        )
        Text(
            text = time,
            style = MaterialTheme.typography.labelSmall,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun MessageAndBookMark(message: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            //Make sure the when the content of the title increase
            //Or the size of the title then the time do not shrink or disappear
            // Ensure the first child takes the remaining space
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_started),
                contentDescription = null
            )
        }
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
@Preview(showBackground = true)
private fun MessageAndBookmarkPreview() {
    // MessageAndBookMark(message = "Hi")
}
@Composable
@Preview(showBackground = true)
private fun InboxEmailListPreview() {
    DemoList()
}


@Composable
@Preview(showBackground = true)
private fun EmailItemPreview() {
    EmailItem()
}