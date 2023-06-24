package com.khalekuzzaman.just.cse.gmailclone.ui.screens.read_mail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CustomIconButton
import com.khalekuzzaman.just.cse.gmailclone.ui.common.Menu

@Composable
fun SenderInfoSlotExpandedScreen(
    profileImage: @Composable () -> Unit,
    userName: @Composable () -> Unit,
    timeStamp: @Composable () -> Unit,
    replyArrow: @Composable () -> Unit,
    forwardArrow: @Composable () -> Unit,
    moreMenuIcon: @Composable () -> Unit,
    expandedIcon: @Composable () -> Unit,
    toMeText: @Composable () -> Unit,
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Box(modifier = Modifier.align(Alignment.CenterVertically)) {
            profileImage()
        }
        Column(modifier = Modifier.weight(1f)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                        Box(Modifier.weight(1f, fill = false)) {
                            userName()
                        }
                        timeStamp()

                }

                Row(modifier = Modifier) {
                    replyArrow()
                    forwardArrow()
                    moreMenuIcon()
                }
            }
            Row(modifier = Modifier) {
                toMeText()
                expandedIcon()
            }

        }
    }


}

@Preview()
@Preview(
    device = Devices.TABLET
)
@Composable
fun SenderInfoExpandedScreenPreview() {

    Column {
        var string = ""
        for (i in 1..50)
            string += "a"
        SenderInfoSlotExpandedScreen(
            profileImage = {
                CommonIconButton(
                    resourceId = R.drawable.ic_profile_2
                )
            },
            userName = {
                Text(
                    text = string,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    modifier = Modifier.weight(1f),
                    overflow = TextOverflow.Ellipsis,
                )
            },
            timeStamp = {
                Text(
                    text = "5 days ago",
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
                    onMenuItemClick = {},
                    menuItems = emptyList(),
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
                CustomIconButton(onClick = { }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_down_arrow),
                        contentDescription = null
                    )
                }
            }
        )


    }

}
