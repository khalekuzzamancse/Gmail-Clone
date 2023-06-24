package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.ui.common.FormLayout

@Composable
 fun EmailRecipientInfo(
    from: String,
    to: String,
    date: String,
) {

    Surface(
        tonalElevation = 5.dp
    ) {
        FormLayout(eachRow1stChildMaxWidth = 100.dp) {
            Text(text = "From")
            Text(text = from)
            Text(text = "to")
            Text(text = to)
            Text(text = "Date")
            Text(text = date)
        }
    }


}

@Composable
@Preview(showBackground = true)
private fun EmailRecipientInfoPreview() {
    EmailRecipientInfo(
        from = "khalekuzzaman91@gmail.com",
        to = "khalekuzzaman91@gmail.com",
        date = "26 Mar 2023, 8:26 pm",
    )
}

