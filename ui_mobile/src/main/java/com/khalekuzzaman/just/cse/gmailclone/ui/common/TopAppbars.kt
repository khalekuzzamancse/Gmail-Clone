package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppbarM3_01() {

    var moveAppbarVerticallyBy by remember {
        mutableIntStateOf(0)
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
                onNavigationIconClick = {}
            ) {}
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
fun ContextualTopAppbar(
    onBackArrowClick: () -> Unit,
    selectedEmailCount: Int,
    onArchiveButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onMarkAsUnReadButtonClick: () -> Unit,
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            CommonIconButton(
                imageVector = Icons.Default.ArrowBack,
                onClick = onBackArrowClick
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        ),
        actions = {
            if (selectedEmailCount > 0) {
                Text(text = "$selectedEmailCount")
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
    ContextualTopAppbar(
        selectedEmailCount = 0,
        onArchiveButtonClick = {},
        onBackArrowClick = {},
        onDeleteButtonClick = {},
        onMarkAsUnReadButtonClick = {}
    )
}


@Composable
@Preview(showBackground = false)
private fun ContextualTopAppbarPreview2() {
    Scaffold(
        topBar = {
            ContextualTopAppbar(
                selectedEmailCount = 0,
                onArchiveButtonClick = {},
                onBackArrowClick = {},
                onDeleteButtonClick = {},
                onMarkAsUnReadButtonClick = {}
            )
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




