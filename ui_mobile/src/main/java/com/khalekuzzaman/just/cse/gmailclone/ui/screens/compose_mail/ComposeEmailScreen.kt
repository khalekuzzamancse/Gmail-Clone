package com.khalekuzzaman.just.cse.gmailclone.ui.screens.compose_mail


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton


@Composable
fun ComposeEmail(
    onBackArrowClick: () -> Unit,
    onMenuItemClick: (String) -> Unit,
    onAttachmentIconClick: () -> Unit,
    onSendButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
    from: String,
) {

    Scaffold(
        modifier = modifier,
        topBar = {
            ComposeEmailTopAppbar(
                onBackArrowClick = onBackArrowClick,
                onMenuItemClick = onMenuItemClick,
                onSendButtonClick = onSendButtonClick,
                onAttachmentIconClick = onAttachmentIconClick,
            )
        }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),

            ) {

            Column(modifier = Modifier) {
                /*
            we have to align start of
            "from" and "to" Row with the subject and "compose" row
            the internal start padding of TextField=16 dp,so we have to use
            it with the "from" and "to" row.
             */
                val internalStartPaddingOfTextField = 16.dp
                Row {
                    InputField(
                        label = "From",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(start = internalStartPaddingOfTextField),
                        onTextChange = {},
                        initialText = from
                    )
                    CommonIconButton(
                        resourceId = R.drawable.ic_down_arrow
                    )
                }
                Row {
                    InputField(
                        label = "to",
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(start = internalStartPaddingOfTextField),
                        onTextChange = {},
                        initialText = ""
                    )
                    CommonIconButton(
                        resourceId = R.drawable.ic_down_arrow
                    )
                }
                ComposableScreenTextField(
                    modifier = Modifier.fillMaxWidth(),
                    onTextChange = {},
                    hints = "Subject",
                    initialText = ""
                )
            }
            ComposableScreenTextField(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize(),
                onTextChange = {},
                maxLine = Int.MAX_VALUE,
                hints = "Compose Email",
                initialText = ""
            )
        }

    }


}

@Preview
@Composable
fun ComposeEmailPreview() {
    ComposeEmail(
        onBackArrowClick = { /*TODO*/ },
        onMenuItemClick = {},
        onAttachmentIconClick = { /*TODO*/ },
        onSendButtonClick = {},
        from = "khalekuzzaman91@gmail.com"
    )
}