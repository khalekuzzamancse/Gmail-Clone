package com.khalekuzzaman.just.cse.gmailclone

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import com.khalekuzzaman.just.cse.gmailclone.utils.TextFinder

fun main() {
    val urls = TextFinder().findText(
        text = "abcdaB",
        "Ab" +
                ""
    )
    urls.forEach { pair ->
        print("$pair")
    }

}