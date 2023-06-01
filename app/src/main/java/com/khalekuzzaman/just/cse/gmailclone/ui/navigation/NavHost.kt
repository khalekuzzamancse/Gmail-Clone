package com.khalekuzzaman.just.cse.gmailclone.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.khalekuzzaman.just.cse.gmailclone.ui.common.Destinations

@Composable
fun ModalDrawerNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.ALL_INBOXES
    ) {

    }

}

