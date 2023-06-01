package com.khalekuzzaman.just.cse.gmailclone.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ModalDrawerItem

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
