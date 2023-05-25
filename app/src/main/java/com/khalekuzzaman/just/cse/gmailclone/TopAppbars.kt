package com.khalekuzzaman.just.cse.gmailclone

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbarM3_01() {

    var moveAppbarVerticallyBy by remember {
        mutableStateOf(0)
    }
    val collapsableToolbarState =
        CollapsableToolbarState(density = LocalDensity.current) { y ->
            moveAppbarVerticallyBy = y
        }

    Scaffold(
        modifier = Modifier.nestedScroll(collapsableToolbarState.nestedScrollConnection),
        topBar = {
            CollapseAbleTopAppbar(
                moveAppbarVerticallyBy = moveAppbarVerticallyBy,
            )
        }) {
        LazyColumn {
            items(100) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )

            }
        }

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopAppbar(moveAppbarVerticallyBy: Int = 0) {
    CollapseAbleTopAppbar {
        SearchBar(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )
        ProfileImage(drawableResource = R.drawable.profile_image)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapseAbleTopAppbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    moveAppbarVerticallyBy: Int = 0,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onNavigationIconClick: () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
) {

    TopAppBar(
        modifier = modifier
            .offset { IntOffset(x = 0, y = moveAppbarVerticallyBy) },
        title = {
            title?.let { text ->
                Text(text)
            }
        },
        actions = actions,
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick() }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        },
        scrollBehavior = scrollBehavior
    )
}


///
class CollapsableToolbarState(
    density: Density,
    private val onToolbarOffsetChanged: (Int) -> Unit,
) {
    private val toolbarHeight: Dp = 48.dp
    private var toolbarHeightPx: Float
    var toolbarOffsetHeightPx = 0f
        private set(value) {
            field = value
            onToolbarOffsetChanged(value.roundToInt()) // Call the callback whenever the value changes
        }

    init {
        toolbarHeightPx = with(density) {
            toolbarHeight.roundToPx().toFloat()
        }
    }

    val nestedScrollConnection: NestedScrollConnection
        get() = object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetHeightPx + delta
                toolbarOffsetHeightPx = newOffset.coerceIn(-toolbarHeightPx, 0f)
                return Offset.Zero
            }
        }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContextualTopAppbar(
    onBackArrowClick: () -> Unit = {},
    selectedEmails: Int = 0,
    onArchiveButtonClick: () -> Unit = {},
    onDeleteButtonClick: () -> Unit = {},
    onMarkAsUnReadButtonClick: () -> Unit = {},
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            CommonIconButton(
                imageVector = Icons.Default.ArrowBack,
                onClick = onBackArrowClick
            )
        },
        actions = {
            if (selectedEmails > 0) {
                Text(text = "$selectedEmails")
            }

            Spacer(modifier = Modifier.width(40.dp))
            CommonIconButton(
                resourceId = R.drawable.ic_archive,
                onClick = onArchiveButtonClick
            )
            CommonIconButton(
                resourceId = R.drawable.ic_delete,
                onClick = onDeleteButtonClick
            )
            CommonIconButton(
                resourceId = R.drawable.ic_mark_as_unread,
                onClick = onMarkAsUnReadButtonClick
            )
            CommonIconButton(
                imageVector = Icons.Default.MoreVert,
                onClick = { }
            )
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun ContextualTopAppbarPreview() {
    ContextualTopAppbar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = false)
private fun ContextualTopAppbarPreview2() {
    Scaffold(
        topBar = {
            ContextualTopAppbar()
        }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(3) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CollapsableTopAppbarPreview() {
    //CollapseAbleTopAppbar()
    TopAppbarM3_01()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CommonTopAppbarPreview() {
    CommonTopAppbar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CommonTopAppbarPreview2() {
    Scaffold(
        topBar = {
            CommonTopAppbar()
        }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            items(10) { index ->
                Text(
                    "I'm item $index", modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }

}
