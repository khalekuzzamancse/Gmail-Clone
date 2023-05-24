package com.khalekuzzaman.just.cse.gmailclone

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.Px
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
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
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

//val toolbarHeight = 48.dp
// val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
// var toolbarOffsetHeightPx by
//remember { mutableStateOf(0f) }
//    val nestedScrollConnection = remember {
//         object : NestedScrollConnection {
//            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
//                val delta = available.y
//                val newOffset = toolbarOffsetHeightPx + delta
//                Log.i("Scrooling:TopAppbarM3_01()", newOffset.toString())
//                toolbarOffsetHeightPx = newOffset.coerceIn(-toolbarHeightPx, 0f)
//                return Offset.Zero
//            }
//        }
//  }

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
                moveAppbarVerticallyBy,
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
fun CollapseAbleTopAppbar(
    moveAppbarVerticallyBy: Int = 0,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
) {


    TopAppBar(
        modifier = Modifier
            .offset { IntOffset(x = 0, y = moveAppbarVerticallyBy) },
        title = {
            Text("Home Page")
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
            }
        }, scrollBehavior = scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun CollapsableTopAppbarPreview() {
    //CollapseAbleTopAppbar()
    TopAppbarM3_01()
}