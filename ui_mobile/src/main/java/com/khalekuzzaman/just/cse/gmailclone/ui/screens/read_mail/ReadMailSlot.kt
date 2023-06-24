package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReadEmailLayoutSlot(
    modifier: Modifier = Modifier,
    subjectSection: @Composable () -> Unit,
    bookMarkSection: @Composable () -> Unit,
    moreInfoSection: @Composable () -> Unit,
    messageSection: @Composable () -> Unit,
    footerSection: @Composable () -> Unit,
) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) { subjectSection() }
            bookMarkSection()
        }
        moreInfoSection()
        Box(Modifier.weight(1f)) { messageSection() }
        footerSection()

    }


}
