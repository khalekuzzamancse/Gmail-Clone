package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CustomIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.Menu

@Composable
fun SenderInfoHeader(
    userName: String,
    time: String,
    profileImageId: Int,
    onExpandClick: () -> Unit,
    onMenuItemClick: (item: String) -> Unit,
    menuItems: List<String>,
) {

    SenderInfoSlotExpandedScreen(
        isExpandedScreen = true,
        profileImage = {
            CommonIconButton(
                resourceId =profileImageId
            )
        },
        userName = {
            Text(
                text = userName,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        timeStamp = {
            Text(
                text =time,
                style = MaterialTheme.typography.labelMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,

                )
        },
        replyArrow = {
            CommonIconButton(
                resourceId = R.drawable.ic_reply
            )
        },
        forwardArrow = {

            CommonIconButton(
                resourceId = R.drawable.ic_forward
            )
        },
        moreMenuIcon = {
            Menu(
                onMenuItemClick = onMenuItemClick,
                menuItems = menuItems,
                offset = DpOffset(0.dp, 0.dp)
            )

        },
        toMeText = {
            Text(
                text = "to me",
                style = MaterialTheme.typography.labelLarge,
            )
        },
        expandedIcon = {
            CustomIconButton(onClick = onExpandClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_down_arrow),
                    contentDescription = null
                )
            }
        }
    )
}




@Composable
@Preview(showBackground = true)
private fun SenderHeaderPreview() {
    SenderInfoHeader(
        userName = "Mr. Bean",
        time = "5 days ago",
        profileImageId = R.drawable.ic_profile_2,
        {},
        {},
        emptyList()
    )
}
