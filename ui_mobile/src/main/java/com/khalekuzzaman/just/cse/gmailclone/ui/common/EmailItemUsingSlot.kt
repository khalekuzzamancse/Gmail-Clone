package com.khalekuzzaman.just.cse.gmailclone.ui.common

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R

@Preview(showBackground = true)
@Composable
private fun EmailItemSlotsPreview() {
    EmailItemSlot(
        profileImage = {
            ProfileImage(
                drawableResource = R.drawable.profile_image
            )
        },
        userName = {
            Text(
                text = "Mr Bean",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        timeStamp = {
            Text(
                text = "05 June 2023",
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        subject = {
            Text(
                text = "Subject:Slot API testing for gmail clone application so that we can use later",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
        },
        message = {
            Text(
                text = "Congratulates,Mr Bean this the first gmail clone project and in this project we are going to use",
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        bookmark = {
            BookmarkIcon()
        },
    )

}

@Composable
fun EmailItemDemo() {
    Column {
        EmailItem(
            userName = "Mr Bean",
            subject = "This is the SLOT API testing purpose for Mr Bean,for Gmail Clone App.",
            message = "Congratulations,Mr Bean.You are selected as  a team member for testing the gmail clone app.",
            isSelected = false,
            onClick = {
                Log.i("onClickExecuted:", "SingleClick")
            },
            onLongClick = {
                Log.i("onClickExecuted:", "LongClick")
            },
            onProfileImageClick = {
                Log.i("onClickExecuted:", "ProfileImageClick")
            },
            onBookmarkIconClick = {
                Log.i("onClickExecuted:", "onBookmarkClick")
            },
            isBookmarked = false,
            timeOrDate = "5 June 2023",
        )
        EmailItem(
            userName = "Mr Bean",
            subject = "This is the SLOT API testing purpose for Mr Bean,for Gmail Clone App.",
            message = "Congratulations,Mr Bean.You are selected as  a team member for testing the gmail clone app.",
            isSelected = true,
            onClick = {
                Log.i("onClickExecuted:", "SingleClick")
            },
            onLongClick = {
                Log.i("onClickExecuted:", "LongClick")
            },
            onProfileImageClick = {
                Log.i("onClickExecuted:", "ProfileImageClick")
            },
            onBookmarkIconClick = {
                Log.i("onClickExecuted:", "onBookmarkClick")
            },
            isBookmarked = true,
            timeOrDate = "5 June 2023",
        )
        EmailItemForSearchList(
            userName = "Mr Bean",
            subject = "This is the SLOT API testing purpose for Mr Bean,for Gmail Clone App.",
            message = "Congratulations,Mr Bean.You are selected as  a team member for testing the gmail clone app.",
            onClick = {
                Log.i("onClickExecuted:", "SingleClick")
            },
            searchedText = "mr",
            onBookmarkIconClick = {
                Log.i("onClickExecuted:", "onBookmarkClick")
            },
            isBookmarked = true,
            timeOrDate = "5 June 2023",
        )
        EmailItemForSearchList(
            userName = "Mr Bean",
            subject = "This is the SLOT API testing purpose for Mr Bean,for Gmail Clone App.",
            message = "Congratulations,Mr Bean.You are selected as  a team member for testing the gmail clone app.",
            onClick = {
                Log.i("onClickExecuted:", "SingleClick")
            },
            searchedText = "a",
            onBookmarkIconClick = {
                Log.i("onClickExecuted:", "onBookmarkClick")
            },
            isBookmarked = false,
            timeOrDate = "5 June 2023",
        )
    }
}

@Composable
@Preview
private fun EmailItemPreview() {
    EmailItemDemo()
}

/*
EmailItemForSearchList() composable
1:It will just provide the content with the on click lambda
2:It does not know how to and where content will be placed

Note:One may think instead of passing,userName,subject,message and date
why we not pass the whole EmailModel as a parameter,
if we pass the EmailModel as a parameter then this function is tightly coupled
with the EmailModel class,as a result without the existence of EmailModel class
we can not use this function,to avoid such a tight coupling we passed the
separate parameter,this give us more flexibility such as changing,re using and testing.
If we used the EmailModel as parameter,if for some reason we change the internal
structure of the EmailModel then we need to change also this function unnecessary.
 */
@Composable
fun EmailItemForSearchList(
    modifier: Modifier = Modifier,
    userName: String,
    subject: String,
    message: String,
    searchedText: String,
    onClick: () -> Unit,
    onBookmarkIconClick: () -> Unit,
    isBookmarked: Boolean,
    timeOrDate: String,
) {
    EmailItemSlot(
        modifier = modifier
            .clickable { onClick() },
        profileImage = {
            ProfileImage(
                drawableResource = R.drawable.profile_image
            )
        },
        userName = {
            Text(
                text = getHighLightedString(userName, searchedText),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        timeStamp = {
            Text(
                text = timeOrDate,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        subject = {
            Text(
                text = getHighLightedString(subject, searchedText),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
        },
        message = {
            Text(
                text = getHighLightedString(message, searchedText),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        bookmark = {
            if (isBookmarked)
                CustomIconButton(
                    onClick = onBookmarkIconClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmarked),
                        contentDescription = null
                    )
                }
            else {
                CustomIconButton(
                    onClick = onBookmarkIconClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = null
                    )
                }
            }
        },
    )

}

/*
EmailItem() composable
1:It will just provide the content with the on click lambda
2:It does not know how to and where content will be placed
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmailItem(
    modifier: Modifier = Modifier,
    userName: String,
    subject: String,
    message: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    onProfileImageClick: () -> Unit,
    onBookmarkIconClick: () -> Unit,
    isBookmarked: Boolean,
    timeOrDate: String,
) {
    EmailItemSlot(
        modifier = modifier
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )
            .background(
                if (isSelected) MaterialTheme.colorScheme.primaryContainer
                else MaterialTheme.colorScheme.surface,
            ),
        profileImage = {
            Box(Modifier.clickable { onProfileImageClick() }) {
                if (isSelected) {
                    SelectedProfileImage()
                } else {
                    ProfileImage(
                        drawableResource = R.drawable.profile_image
                    )
                }
            }

        },
        userName = {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        timeStamp = {
            Text(
                text = timeOrDate,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        subject = {
            Text(
                text = subject,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
        },
        message = {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        bookmark = {
            if (isBookmarked)
                CustomIconButton(
                    onClick = onBookmarkIconClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmarked),
                        contentDescription = null
                    )
                }
            else {
                CustomIconButton(
                    onClick = onBookmarkIconClick
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark),
                        contentDescription = null
                    )
                }
            }
        },
    )

}

/*
EmailItemSlot() composable
1:It will take  the content with place them to the appropriate position
2:It does not know how to handle click on the content
3:It does not know from where and which type of content is coming
 */
@Composable
fun EmailItemSlot(
    modifier: Modifier = Modifier,
    profileImage: @Composable () -> Unit,
    userName: @Composable () -> Unit,
    timeStamp: @Composable () -> Unit,
    subject: @Composable () -> Unit,
    message: @Composable () -> Unit,
    bookmark: @Composable () -> Unit,
) {
    Row(modifier = modifier) {
        Box(modifier = modifier.padding(end = 5.dp))
        {
            profileImage()
        }
        Column {
            UserNameAndTimeSlot(
                userName = userName,
                timeStamp = timeStamp
            )
            MessageAndSubjectSlotAndBookmark(
                subject = subject,
                message = message,
                bookmark = bookmark,
            )
        }
    }

}

@Composable
fun UserNameAndTimeSlot(
    userName: @Composable () -> Unit,
    timeStamp: @Composable () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier.weight(1f),
        ) {
            userName()
        }
        timeStamp()
    }

}

@Composable
fun MessageAndSubjectSlotAndBookmark(
    subject: @Composable () -> Unit,
    message: @Composable () -> Unit,
    bookmark: @Composable () -> Unit,
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
        ) {
            message()
            subject()
        }
        Box {
            bookmark()
        }

    }


}

@Composable
private fun SelectedProfileImage(modifier: Modifier = Modifier) {
    Box(
        modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Icon(
            Icons.Default.Check,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}