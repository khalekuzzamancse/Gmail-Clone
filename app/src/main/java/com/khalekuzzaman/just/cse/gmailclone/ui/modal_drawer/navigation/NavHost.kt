package com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.Destinations
import com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.DrawerItemsProvider

@Composable
fun ModalDrawerNavHost(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destinations.ALL_INBOXES
    ) {

    }

}

