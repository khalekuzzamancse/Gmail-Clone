package com.khalekuzzaman.just.cse.gmailclone.ui.screens.labels

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerDestinations

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
        onChecked = {},
        onCrossButtonClick = {},
        list = labelItemList
    )
}

@Composable
@Preview(showBackground = true)
fun BottomSheetDemoPreview() {
    BottomSheetDemo()
}

@Composable
fun LabelSheet(
    onChecked: (itemName: String) -> Unit,
    onCrossButtonClick: () -> Unit,
    list: List<LabelSheetItem>,
) {
    LabelBottomSheetSlot(
        dismissButton = {
            Icon(
                modifier = Modifier.
                padding(start = 24.dp)
                    .clickable{
                              onCrossButtonClick()
                    },
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


