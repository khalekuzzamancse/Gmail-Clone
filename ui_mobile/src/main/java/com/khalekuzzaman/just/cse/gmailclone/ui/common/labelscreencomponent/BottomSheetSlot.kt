package com.khalekuzzaman.just.cse.gmailclone.ui.common.labelscreencomponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LabelBottomSheetSlot(
    dismissButton: @Composable () -> Unit,
    title: @Composable () -> Unit,
    searchContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier) { dismissButton() }
            Spacer(modifier = Modifier.width(8.dp))
            Box(modifier = Modifier) { title() }

        }
        Divider()
        Column(modifier = Modifier.fillMaxWidth()) {
            searchContent?.let {
                searchContent()
                Divider()
            }
        }
        Box(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
        ) { content() }
    }


}