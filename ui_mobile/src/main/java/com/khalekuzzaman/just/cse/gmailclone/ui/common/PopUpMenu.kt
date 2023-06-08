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
    Menu {
        Log.i("Clicked",it)
    }
}



/*
As per the requirement of Gmail app the popup menu is covered the
top app bar,that is why we have to move the menu by using
  offset = DpOffset(0.dp, (-200).dp),
I think this not the better solution but at this moment this the only solution
that comes into my mind,find the better solution  later
 */

@Composable
fun Menu(onMenuItemClick: (ItemName: String) -> Unit) {
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
            for (i in 1..7) {
                DropdownMenuItem(text = {
                    Text(text = "Option $i")
                }, onClick = {
                    onMenuItemClick( "Option $i")
                    expanded = false

                })
            }
        }
    }


}

