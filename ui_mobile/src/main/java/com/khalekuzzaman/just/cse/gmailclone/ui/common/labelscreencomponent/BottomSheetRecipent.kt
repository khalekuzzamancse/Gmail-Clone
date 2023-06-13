package com.khalekuzzaman.just.cse.gmailclone.ui.common.labelscreencomponent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel



/*
-

 */

@Composable
@Preview(showSystemUi = true)
fun BottomSheetRecipientDemoPreview() {
    BottomSheetRecipientDemo()
}

@Composable
fun BottomSheetRecipientDemo() {
    BottomSheetRecipient(list = FakeEmailList.getFakeEmails())
}

@Composable
fun BottomSheetRecipient(
    list: List<EmailModel>,
) {
    LabelBottomSheetSlot(
        dismissButton = {
            Icon(
                modifier = Modifier.padding(start = 24.dp),
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = null
            )
        },
        title = {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = "Form"
            )
        },
        searchContent = {
            Spacer(modifier = Modifier.height(48.dp))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            RecipientBottomSheetSuggestionList(
                list = list
            )
            RecipientList(
                emailList = list
            )


        }
    }
}




@Composable
@Preview(showBackground = true)
fun RecipientListPreview() {
    RecipientList(
        emailList = FakeEmailList.getFakeEmails()
    )
}

@Composable
fun RecipientList(
    emailList: List<EmailModel>,
) {
    val emailMap: HashMap<String, MutableList<EmailModel>> = HashMap()
    for (email in emailList) {
        val firstChar = email.receiver.firstOrNull()?.uppercaseChar()?.toString()
        if (firstChar != null) {
            if (emailMap.containsKey(firstChar)) {
                emailMap[firstChar]?.add(email)
            } else {
                emailMap[firstChar] = mutableListOf(email)
            }
        }
    }
    LazyColumn {
        emailMap.forEach { (key, value) ->
            item {
                Text(
                    text = key,
                    style = MaterialTheme.typography.titleMedium
                )
            }
            items(value) { item ->
                RecipientBottomSheetItem(
                    profileImageResourceId = item.profileImageId,
                    title = item.receiver,
                    subTitle = item.receiver,
                )
            }
        }

    }

}

@Composable
@Preview(showSystemUi = true)
fun RecipientBottomSheetSuggestionListPreview() {
    RecipientBottomSheetSuggestionList(
        list = FakeEmailList.getFakeEmails()
    )
}

@Composable
fun RecipientBottomSheetSuggestionList(
    list: List<EmailModel>,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Suggestions")
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_updates),
                contentDescription = null
            )
        }
        LazyColumn {

            items(items = list) { item ->
                RecipientBottomSheetItem(
                    profileImageResourceId = item.profileImageId,
                    title = item.receiver,
                    subTitle = item.receiver,
                )
            }
        }

    }

}

@Composable
fun RecipientBottomSheetItem(
    profileImageResourceId: Int,
    title: String,
    subTitle: String,
) {
    RecipientBottomSheetSlot(
        profileImage = {
            Icon(
                painter = painterResource(id = profileImageResourceId),
                contentDescription = null
            )
        },
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
        },
        subTitle = {
            Text(
                text = subTitle,
                style = MaterialTheme.typography.titleSmall
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun RecipientBottomSheetSlotPreview() {
    RecipientBottomSheetSlot(
        profileImage = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile_2),
                contentDescription = null
            )
        },
        title = {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "khalekuzzaman91@gmail.com",
                style = MaterialTheme.typography.titleLarge
            )
        },
        subTitle = {
            Text(
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                text = "khalekuzzaman91@gmail.com",
                style = MaterialTheme.typography.titleSmall
            )
        }
    )
}

@Composable
fun RecipientBottomSheetSlot(
    modifier: Modifier = Modifier,
    profileImage: @Composable () -> Unit,
    title: @Composable () -> Unit,
    subTitle: @Composable () -> Unit,
) {
    val margin = 8.dp
    Row(
        modifier = modifier
            .padding(margin)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(48.dp)) { profileImage() }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            title()
            Spacer(modifier = Modifier.height(8.dp))
            subTitle()
        }
    }

}