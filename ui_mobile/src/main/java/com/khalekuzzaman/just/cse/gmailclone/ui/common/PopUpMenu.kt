package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun Preview() {
    val onMenuItemClick: (String) -> Unit = { it ->
        Log.i("Clicked", it)
    }
    Menu(
        menuItems = PopUpMenuItemName.listForInboxes,
        onMenuItemClick = onMenuItemClick,
    )
}


/*
As per the requirement of Gmail app the popup menu is covered the
top app bar,that is why we have to move the menu by using
  offset = DpOffset(0.dp, (-200).dp),
I think this not the better solution but at this moment this the only solution
that comes into my mind,find the better solution  later
 */
object PopUpMenuItemName {
    const val ARCHIVE = "Archive"
    const val DELETE = "Delete"
    const val MARK_AS_READ_OR_UNREAD= "Mark as read"
    const val MOVE_TO = "Move to"
    const val SNOOZE = "Snooze"
    const val CHANGE_LABELS = "Change labels"
    const val ADD_STAR = "Add star"
    const val MARK_AS_NOT_IMPORTANT = "Mark as not important"
    const val MUTE = "Mute"
    const val REPORT_SPAM = "Report spam"
    val listForInboxes = listOf(
        MOVE_TO, SNOOZE, CHANGE_LABELS, ADD_STAR, MARK_AS_NOT_IMPORTANT, MUTE, REPORT_SPAM
    )
}

@Composable
fun Menu(onMenuItemClick: (ItemName: String) -> Unit, menuItems: List<String>) {
    var expanded by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            onClick = { expanded = true },
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More"
            )
        }
        DropdownMenu(
            offset = DpOffset(0.dp, (-200).dp),
            modifier = Modifier,
            expanded = expanded,
            onDismissRequest = {
                expanded = false

            }
        ) {
            menuItems.forEach {
                DropdownMenuItem(text = {
                    Text(text = it)
                }, onClick = {
                    onMenuItemClick(it)
                    expanded = false

                })
            }
        }
    }


}

