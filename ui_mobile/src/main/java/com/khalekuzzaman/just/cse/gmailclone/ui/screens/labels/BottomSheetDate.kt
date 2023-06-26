package com.khalekuzzaman.just.cse.gmailclone.ui.screens.labels

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
val dateOrderList = listOf(
    "Any time",
    "Older than a week",
    "Older than a month",
    "Older than a six months",
    "Older than a year",
)
@Composable
fun DatesBottomSheetDemo() {
    
    DatesBottomSheet(onCrossClick = {}, dateOrderList)
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
@Preview(showBackground = true)
fun DatesBottomSheetPreview() {
    DatesBottomSheetDemo()
}

@Composable
fun DatesBottomSheet(
    onCrossClick: () -> Unit,
    list: List<String>,
) {
    LabelBottomSheetSlot(
        dismissButton = {
            Icon(
                modifier = Modifier
                    .padding(start = 24.dp)
                    .clickable{
                              onCrossClick()
                    },
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
