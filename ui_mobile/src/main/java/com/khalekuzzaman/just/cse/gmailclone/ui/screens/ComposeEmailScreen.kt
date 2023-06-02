package com.khalekuzzaman.just.cse.gmailclone.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row



import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeEmail(

) {


    Column(
        modifier = Modifier
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
       InputField(
           modifier = Modifier.fillMaxWidth()
               .padding(start = internalStartPaddingOfTextField),
           label = "From", onTextChange = {})
       InputField(
           modifier = Modifier.fillMaxWidth()
               .padding(start=internalStartPaddingOfTextField),
           label = "To", onTextChange = {})
       ComposableScreenTextField(
           modifier = Modifier.fillMaxWidth(),
           onTextChange = {},
           hints = "Subject"
       )
   }
        ComposableScreenTextField(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            onTextChange = {},
            maxLine = Int.MAX_VALUE,
            hints = "Compose Email"
        )
    }


}

@Composable
private fun InputField(
    label: String,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    maxLine: Int = 1,
    hints: String = "",
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
        )

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ComposableScreenTextField(
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit,
    maxLine: Int = 1,
    hints: String,
) {
    var text by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }
    TextField(
        modifier = modifier,
        value = text,
        onValueChange = {
            text = it
            onTextChange(text.text)
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = MaterialTheme.colorScheme.surface,
        ),
        maxLines = maxLine,
        shape = TextFieldDefaults.outlinedShape,
        placeholder = {
            Text(
                text = hints
            )
        }
    )
}

@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun ComposeEmailPreview() {
    ComposeEmail()
}
