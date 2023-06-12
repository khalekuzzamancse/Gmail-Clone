package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContextualTopAppbar(
    onBackArrowClick: () -> Unit,
    selectedEmailCount: Int,
    onMenuItemClick: (itemName: String) -> Unit,
    menuItems: List<String>,
) {


    TopAppBar(
        title = {},
        navigationIcon = {
            CommonIconButton(
                imageVector = Icons.Default.ArrowBack,
                onClick = onBackArrowClick
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        ),
        actions = {
            if (selectedEmailCount > 0) {
                Text(text = "$selectedEmailCount")
            }

            Spacer(modifier = Modifier.width(40.dp))
            CommonIconButton(
                resourceId = R.drawable.ic_archive,
                onClick = { onMenuItemClick(PopUpMenuItemName.ARCHIVE) }
            )
            CommonIconButton(
                resourceId = R.drawable.ic_delete,
                onClick = { onMenuItemClick(PopUpMenuItemName.DELETE) }
            )
            CommonIconButton(
                resourceId = R.drawable.ic_mark_as_unread,
                onClick = { onMenuItemClick(PopUpMenuItemName.MARK_AS_READ_OR_UNREAD) }
            )
            Menu(
                onMenuItemClick = onMenuItemClick,
                menuItems = menuItems,
            )
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun ContextualTopAppbarPreview() {
    ContextualTopAppbar(
        onBackArrowClick = {},
        selectedEmailCount = 0,
        {

        },
 emptyList<String>(),
    )
}


@Composable
@Preview(showBackground = false)
private fun ContextualTopAppbarPreview2() {
    Scaffold(
        topBar = {
            ContextualTopAppbar(
                onBackArrowClick = {},
                selectedEmailCount = 0,
                {},
 emptyList<String>(),
            )
        }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(3) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }


}




