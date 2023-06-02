package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R

@Composable
fun ShowFAB(
    modifier: Modifier = Modifier,
    shouldShowExpandedFAB: Boolean = true,
    onClick: () -> Unit,
) {
    if (shouldShowExpandedFAB) {
        ExtendedFloatingActionButton(
            modifier = modifier,
            onClick = onClick,
        ) {
            ComposeIcon()
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = "Compose"
            )
        }
    } else {
        FloatingActionButton(
            modifier = modifier,
            onClick = onClick
        ) {
            ComposeIcon()
        }
    }
}

@Composable
private fun ComposeIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_compose),
        contentDescription = null
    )
}
