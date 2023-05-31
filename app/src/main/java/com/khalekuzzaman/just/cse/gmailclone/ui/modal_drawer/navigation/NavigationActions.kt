package com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer.ModalDrawerItem

class NavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: ModalDrawerItem) {
        val route=destination.label
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}
