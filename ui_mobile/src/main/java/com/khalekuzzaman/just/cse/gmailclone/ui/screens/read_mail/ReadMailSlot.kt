package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ReadEmailLayoutSlot(
    modifier: Modifier = Modifier,
    subjectSection: @Composable () -> Unit,
    moreInfoSection: @Composable () -> Unit,
    messageSection: @Composable () -> Unit,
    footerSection: @Composable () -> Unit,
) {

    Column(
        modifier = modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        //
        subjectSection()
        moreInfoSection()
        messageSection()
        footerSection()

    }


}
