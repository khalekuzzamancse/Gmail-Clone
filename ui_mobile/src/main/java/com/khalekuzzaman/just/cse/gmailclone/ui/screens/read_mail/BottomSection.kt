package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R

@Composable
fun FooterSection(
    onReplyButtonClick: () -> Unit,
    onReplyAllButtonClick: () -> Unit,
    onForwardButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_reply, label = "Reply", onClick = onReplyButtonClick)
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_reply_all, label = "Reply all", onClick = onReplyAllButtonClick)
        BottomSectionButton(
            modifier = Modifier.weight(1f),
            leftIconId = R.drawable.ic_forward, label = "Forward", onClick = onForwardButtonClick)
    }
}

@Composable
fun BottomSectionButton(
    modifier: Modifier = Modifier,
    leftIconId: Int,
    label: String,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(3.dp),
        onClick = onClick,
        contentPadding = PaddingValues(0.dp),
        //using content padding allows to do not shrink the button
        //when it has small size.it shrink all the button when width is
        //less
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = MaterialTheme.colorScheme.onSurface
        ),
        elevation = ButtonDefaults.buttonElevation(5.dp)
    ) {
        Icon(painter = painterResource(id = leftIconId), contentDescription = null)
        Text(
            maxLines = 1,
            text = label,
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomSectionButtonPreview() {
    BottomSectionButton(leftIconId = R.drawable.ic_reply, label = "Reply") {
    }
}
