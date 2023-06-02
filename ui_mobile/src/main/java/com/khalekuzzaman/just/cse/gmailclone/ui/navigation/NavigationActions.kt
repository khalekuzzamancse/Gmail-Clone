package com.khalekuzzaman.just.cse.gmailclone.ui.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.common.ModalDrawerItem

class NavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: String) {
        navController.navigate(destination) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }


}
