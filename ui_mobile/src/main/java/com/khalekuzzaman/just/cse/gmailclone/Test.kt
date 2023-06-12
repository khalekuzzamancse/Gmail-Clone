package com.khalekuzzaman.just.cse.gmailclone

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.khalekuzzaman.just.cse.gmailclone.utils.TextFinder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

suspend fun main() {
    coroutineScope {
        for (i in 1..4) {
            withContext(Dispatchers.Main) {
                println("$i")
            }
            delay(500)
        }
    }
}