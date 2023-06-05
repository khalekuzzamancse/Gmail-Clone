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
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    var queryText by remember {
        mutableStateOf("")
    }
    var active by remember {
        mutableStateOf(true)
    }
    var filteredEmails by remember {
        mutableStateOf(emptyList<EmailModel>())
    }


    SearchBar(
        modifier = modifier,
        query = queryText,
        onQueryChange = {
            queryText = it
            filteredEmails = getSearchResult(queryText)

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
                    queryText = ""
                    active = false
                    onBackClick()
                }
            )

        }, trailingIcon = {
            if (queryText.isNotBlank()) {
                CommonIconButton(
                    resourceId = R.drawable.ic_close,
                    onClick = {
                        queryText = ""
                        filteredEmails = emptyList()
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
        EmailListForSearchResult(
            emails = filteredEmails,
            onBookIconClick = {

            },
            onEmailClick = {

            },
            highlightedText = queryText
        )

    }
}

private fun getSearchResult(queryText: String): List<EmailModel> {
    return (
            FakeEmailList().getFakeEmails().filter { email ->
                email.userName.contains(queryText, ignoreCase = true) ||
                        email.subject.contains(queryText, ignoreCase = true) ||
                        email.message.contains(queryText, ignoreCase = true)
            }
            )
}


@Composable
@Preview(
    showBackground = true,
    showSystemUi = true
)
private fun SearchBarPreview() {
    SearchBar {}
}