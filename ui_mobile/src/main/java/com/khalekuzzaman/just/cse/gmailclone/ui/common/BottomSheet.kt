package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList

@Composable
@Preview(showBackground = true)
private fun LabelItemPreview() {
    LabelItem(
        iconResourceId = R.drawable.ic_started,
        label = "Started",
        onCheckChanged = {},
        isChecked = true
    )
}

data class LabelSheetItem(
    val label: String,
    val resourceId: Int,
    val isChecked: Boolean = false,
)

val labelItemList = listOf(
    LabelSheetItem(
        label = DrawerDestinations.STARTED,
        resourceId = R.drawable.ic_started
    ),
    LabelSheetItem(
        label = DrawerDestinations.SNOOZED,
        resourceId = R.drawable.ic_snoozed
    ),
    LabelSheetItem(
        label = DrawerDestinations.IMPORTANT,
        resourceId = R.drawable.ic_important
    ),
    LabelSheetItem(
        label = DrawerDestinations.SENT,
        resourceId = R.drawable.ic_sent
    ),
    LabelSheetItem(
        label = DrawerDestinations.SCHEDULED,
        resourceId = R.drawable.ic_schedule
    ),
    LabelSheetItem(
        label = DrawerDestinations.DRAFT,
        resourceId = R.drawable.ic_draft
    ),
    LabelSheetItem(
        label = DrawerDestinations.ALL_MAIL,
        resourceId = R.drawable.ic_email
    ),
    LabelSheetItem(
        label = DrawerDestinations.IMAP_SENT,
        resourceId = R.drawable.ic_label
    ),
    LabelSheetItem(
        label = DrawerDestinations.IMAP_TRASH,
        resourceId = R.drawable.ic_label
    ),
    LabelSheetItem(
        label = DrawerDestinations.CALL_LOG,
        resourceId = R.drawable.ic_label
    ),
    LabelSheetItem(
        label = DrawerDestinations.SMS,
        resourceId = R.drawable.ic_label
    ),
)

@Composable
fun BottomSheetDemo() {
    LabelSheet(
        list = labelItemList,
        onChecked = {})
}

@Composable
@Preview(showBackground = true)
fun BottomSheetDemoPreview() {
    BottomSheetDemo()
}

@Composable
fun LabelSheet(
    list: List<LabelSheetItem>,
    onChecked: (itemName: String) -> Unit,
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
                text = "Label"
            )
        },
        searchContent = {
            Row(modifier = Modifier.height(48.dp)) {
            }
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            list.forEach {
                LabelItem(
                    modifier = Modifier,
                    iconResourceId = it.resourceId,
                    label = it.label,
                    onCheckChanged = {
                    },
                    isChecked = it.isChecked
                )
            }

        }
    }
}

/*
We have to align the LabelItem Icon with the Dismiss button start
the button use start padding for  content 24.dp
 */
@Composable
fun LabelItem(
    modifier: Modifier = Modifier,
    iconResourceId: Int,
    label: String,
    onCheckChanged: (Boolean) -> Unit,
    isChecked: Boolean,
) {

    Row(
        modifier
            .padding(start = 24.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Icon(
            painter = painterResource(id = iconResourceId),
            contentDescription = null
        )
        Text(
            text = label
        )
        Spacer(modifier = Modifier.weight(1f))
        Checkbox(
            modifier = Modifier,
            checked = isChecked,
            onCheckedChange = onCheckChanged
        )
    }

}


@Composable
fun LabelBottomSheetSlot(
    dismissButton: @Composable () -> Unit,
    title: @Composable () -> Unit,
    searchContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier) { dismissButton() }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier) { title() }

        }
        Divider()
        Column(modifier = Modifier.fillMaxWidth()) {
            searchContent?.let {
                searchContent()
                Divider()
            }
        }
        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) { content() }
    }


}

/*
-

 */
@Composable
fun DatesBottomSheetDemo() {
    val list = listOf(
        "Any time",
        "Older than a week",
        "Older than a month",
        "Older than a six months",
        "Older than a year",
    )
    DatesBottomSheet(list)
}

@Composable
@Preview(showBackground = true)
fun DatesBottomSheetPreview() {
    DatesBottomSheetDemo()
}

@Composable
fun DatesBottomSheet(
    list: List<String>,
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
                text = "Dates"
            )
        },

        ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            list.forEach {
                SortByTimeItem(
                    onClick = { /*TODO*/ },
                    text = it
                )
            }

        }
    }
}

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
fun SortByTimeItemPreview() {
    SortByTimeItem(
        onClick = { /*TODO*/ },
        text = "Any time"
    )
}

@Composable
fun SortByTimeItem(
    onClick: () -> Unit,
    selected: Boolean = false,
    text: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onClick)
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text)
    }
}


@Composable
fun RecipientListDemo() {

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