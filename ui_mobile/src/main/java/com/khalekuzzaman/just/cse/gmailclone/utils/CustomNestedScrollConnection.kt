package com.khalekuzzaman.just.cse.gmailclone.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import kotlin.math.roundToInt

class CustomNestedScrollConnection(private val onScrollCallback: (Int, ScrollDirection) -> Unit) : NestedScrollConnection {
    private var previousScrollPosition: Float = 0f

    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.y.roundToInt()
        val currentScrollPosition = previousScrollPosition + delta

        val scrollDirection = when {
            currentScrollPosition > previousScrollPosition -> ScrollDirection.DOWN
            currentScrollPosition < previousScrollPosition -> ScrollDirection.UP
            else -> ScrollDirection.IDLE
        }

        // Invoke the callback with the delta value and scroll direction
        onScrollCallback.invoke(delta, scrollDirection)

        previousScrollPosition = currentScrollPosition

        return Offset.Zero
    }
}

enum class ScrollDirection {
    UP,
    DOWN,
    IDLE
}
