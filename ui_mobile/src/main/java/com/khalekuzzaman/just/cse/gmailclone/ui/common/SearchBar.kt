package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.khalekuzzaman.just.cse.gmailclone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var text by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(true)
    }


    SearchBar(
        modifier = modifier,
        query = text,
        onQueryChange = {
            text = it
        },
        onSearch = {
            active = false
        },
        active = active,
        onActiveChange = {
            active = it
        },
        placeholder = {
            Text(text = "Search in emails")
        },
        leadingIcon = {
            CommonIconButton(
                imageVector = Icons.Default.ArrowBack,
                onClick = {
                    text = ""
                    active = false
                    onBackClick()
                }
            )

        }, trailingIcon = {
            if (text.isNotBlank()) {
                CommonIconButton(
                    resourceId = R.drawable.ic_close,
                    onClick = {
                        text = ""
                    }
                )
            } else {
                CommonIconButton(
                    resourceId = R.drawable.ic_mic,
                    onClick = {

                    }
                )
            }


        }
    ) {

    }
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun SearchBarPreview() {
    SearchBar {}
}