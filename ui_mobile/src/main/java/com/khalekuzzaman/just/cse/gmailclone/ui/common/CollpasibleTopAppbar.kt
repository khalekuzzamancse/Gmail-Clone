package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import kotlin.math.roundToInt


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreenTopAppbarDemo() {

    var moveAppbarVerticallyBy by remember {
        mutableIntStateOf(0)
    }
    val collapsableToolbarState =
        CollapsableToolbarState(
            density = LocalDensity.current,
        ) { y ->
            moveAppbarVerticallyBy = y
        }

    Scaffold(
        modifier = Modifier
            .nestedScroll(collapsableToolbarState.nestedScrollConnection),
        topBar = {

            CommonTopAppbarForListScreen(
                profileImageResourceId = R.drawable.ic_profile_2,
                moveAppbarVerticallyBy = moveAppbarVerticallyBy,
                onNavigationIconClick = {},
                onProfileIconClick = {},
                onSearchTextClick = {}
            )
        }) {
        LazyColumn(modifier = Modifier) {
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


@Composable
@Preview
private fun Previews() {
    Column(
        modifier = Modifier
    ) {
        ListScreenTopAppbarDemo()
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Dialogue(
    modifier: Modifier = Modifier,
) {
    val openDialog = remember { mutableStateOf(true) }

    val googleText = buildAnnotatedString {
        append(AnnotatedString("G", spanStyle = SpanStyle(Color.Blue)))
        append(AnnotatedString("o", spanStyle = SpanStyle(Color.Red)))
        append(AnnotatedString("o", spanStyle = SpanStyle(Color.Yellow)))
        append(AnnotatedString("g", spanStyle = SpanStyle(Color.Blue)))
        append(AnnotatedString("l", spanStyle = SpanStyle(Color.Green)))
        append(AnnotatedString("e", spanStyle = SpanStyle(Color.Red)))
    }

    if (openDialog.value) {
        AlertDialog(
            modifier = modifier,
            onDismissRequest = {
                openDialog.value = false
            }
        ) {
            Card(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        modifier =
                        Modifier,
                        onClick = { /*TODO*/ }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_cross),
                            contentDescription = null
                        )
                    }
                    Text(
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center,
                        text = googleText,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Surface(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize(),
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = AlertDialogDefaults.TonalElevation
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {

                        Spacer(modifier = Modifier.height(24.dp))

                        Text(
                            text = "Put the content here",
                        )

                    }
                }
            }


        }
    }
}


/*
The topAppbar that is provided the material library has some bugs such as:
1.if the "action" parameter content size of enough then it overlap the navigation icon
2.We Simply can not align the start of the "Search" in email in the end of the navigation icon
Decision:
Because of the limitation of the top app bar we are going to create our own top app bar,using
the material 3 top appbar size,color,..etc,you can look the material 3 documentation of the measurement
and themes that we are using in this customTop app bar'

This top bar do:
It take the profile Image and  show it appropriate position.
It has:
Navigation Icon
Search Text
Profile Image

Main Responsibility of this function to just notify it caller function that
navigation icon,search text or profile icon is clicked then against
the click the caller function can take any action that it want.
 */

@Composable
fun CommonTopAppbarForListScreen(
    modifier: Modifier = Modifier,
    onNavigationIconClick: () -> Unit,
    onSearchTextClick: () -> Unit,
    onProfileIconClick: () -> Unit,
    profileImageResourceId: Int,
    moveAppbarVerticallyBy: Int = 0,
) {
    Surface(
        modifier = modifier
            .height(64.dp)
            .offset { IntOffset(x = 0, y = moveAppbarVerticallyBy) }
    ) {
        val margin = 8.dp
        Card(
            modifier = Modifier
                .padding(margin)
                .height(48.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                IconButton(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 8.dp)
                        .size(48.dp),
                    onClick = onNavigationIconClick
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = ""
                    )
                }
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .clickable {
                            onSearchTextClick()
                        },
                    text = "Search in email",
                )
                IconButton(
                    modifier = Modifier
                        .padding(start = 24.dp, end = 16.dp)
                        .size(48.dp),
                    onClick = onProfileIconClick
                ) {
                    Icon(
                        painter = painterResource(id = profileImageResourceId),
                        contentDescription = ""
                    )
                }
            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CollapseAbleTopAppbar(
    modifier: Modifier = Modifier,
    title: String? = null,
    moveAppbarVerticallyBy: Int = 0,
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(),
    onNavigationIconClick: () -> Unit,
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
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        )

    )
}


///
class CollapsableToolbarState(
    density: Density,
    private val onToolbarOffsetChanged: (Int) -> Unit,

    ) {
    private val toolbarHeight: Dp = 64.dp
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
