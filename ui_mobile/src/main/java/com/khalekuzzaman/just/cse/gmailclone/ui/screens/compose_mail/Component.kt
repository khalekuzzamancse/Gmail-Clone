package com.khalekuzzaman.just.cse.gmailclone.ui.screens.compose_mail

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.Menu
import com.khalekuzzaman.just.cse.gmailclone.ui.common.PopUpMenuItemName


@Composable
 fun InputField(
    label: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    maxLine: Int = 1,
    hints: String = "",
    initialText: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier,
            text = label
        )
        ComposableScreenTextField(
            modifier = modifier,
            onTextChange = onTextChange,
            maxLine = maxLine,
            hints = hints,
            initialText = initialText,
        )

    }

}

@Composable
 fun ComposableScreenTextField(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    maxLine: Int = 1,
    hints: String,
    initialText: String,
) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(initialText))
    }
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onTextChange(text.text)
        },
        colors = TextFieldDefaults.colors(
         //   con = MaterialTheme.colorScheme.surface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface,
        ),
        maxLines = maxLine,
        shape = OutlinedTextFieldDefaults.shape,
        placeholder = {
            Text(
                text = hints
            )
        }
    )
}

@Composable
@Preview
private fun ComposeEmailTopAppbarDemo() {
    ComposeEmailTopAppbar(
        onBackArrowClick = { /*TODO*/ },
        {},
        {},
        {},
 Modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeEmailTopAppbar(
    onBackArrowClick: () -> Unit,
    onMenuItemClick: (itemName: String) -> Unit,
    onSendButtonClick: () -> Unit,
    onAttachmentIconClick: () -> Unit,
    modifier: Modifier=Modifier,
) {

    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Compose")
        },
        navigationIcon = {
            CommonIconButton(
                imageVector = Icons.Default.ArrowBack,
                onClick = onBackArrowClick
            )
        },
        colors = topAppBarColors(
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
        ),
        actions = {
            CommonIconButton(
                resourceId = R.drawable.ic_attachment,
                onClick =onAttachmentIconClick
            )
            CommonIconButton(
                resourceId = R.drawable.ic_send,
                onClick =onSendButtonClick
            )
            Menu(
                onMenuItemClick = onMenuItemClick,
                menuItems = PopUpMenuItemName.listForInboxes,
            )
        }
    )
}

