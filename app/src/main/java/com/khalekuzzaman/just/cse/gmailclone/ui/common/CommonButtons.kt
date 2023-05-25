package com.khalekuzzaman.just.cse.gmailclone.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import com.khalekuzzaman.just.cse.gmailclone.R

@Composable
fun CommonIconButton(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick: () -> Unit = {},
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = imageVector,
            contentDescription = null
        )
    }
}

@Composable
fun CommonIconButton(
    modifier: Modifier = Modifier,
    resourceId: Int,
    onClick: () -> Unit = {},
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = resourceId),
            contentDescription = null
        )
    }
}
@Composable
fun BookmarkIcon(
    modifier: Modifier = Modifier,
    isBookMarked: Boolean = false,
    onBookmarkIconClick: () -> Unit = {},
) {
    CustomIconButton(
        onClick = onBookmarkIconClick,
        modifier = modifier
    ) {
        val icon = if (isBookMarked) R.drawable.ic_bookmarked
        else R.drawable.ic_bookmark
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )
    }

}
@Composable
fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit,
) {
    /*
    Note:
    The icon button that is provided by compose library has some minimum size so that is can
    match the target touch,but in this case we want the icon button size,should be the same as the
    icon size so that we can easily place it with the other content,
    that is why we we edited the IconButton() source code and create a customIcon button
    that will take the exact size of it content size

     */

    Box(
        modifier =
        modifier
            .wrapContentSize()
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(
                    bounded = false,
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        CompositionLocalProvider(LocalContentColor provides TabRowDefaults.contentColor, content = content)
    }
}