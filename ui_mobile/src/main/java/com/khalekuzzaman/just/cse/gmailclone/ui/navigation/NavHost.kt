package com.khalekuzzaman.just.cse.gmailclone.ui.navigation

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerDestinations
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import com.khalekuzzaman.just.cse.gmailclone.ui.common.SearchBar
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.CommonEmailListScreen
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.ComposeEmail
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.OpenEmail
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalDrawerNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val openDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.open()
        }
    }
    val closeDrawer: () -> Unit = {
        coroutineScope.launch {
            drawerState.close()
        }
    }
    val navigationActions = NavigationActions(navController)
    val navigateTo: (String) -> Unit = { route ->
        navigationActions.navigateTo(route)
    }
    val navigateToOpenEmail: (EmailModel) -> Unit = {
        navController.navigate(NonTopDestinations.OPEN_EMAIL)
    }
    val navigateToComposeEmail: () -> Unit = {
        navController.navigate(NonTopDestinations.COMPOSE_EMAIL)
    }


    NavHost(
        navController = navController,
        startDestination = DrawerDestinations.ALL_INBOXES
    ) {

        composable(DrawerDestinations.ALL_INBOXES) {
                CommonEmailListScreen(
                    onNavigate = navigateTo,
                    closeDrawer = closeDrawer,
                    openDrawer = openDrawer,
                    onFabClick = navigateToComposeEmail,
                    onEmailItemClick = navigateToOpenEmail,
                    drawerState = drawerState,
                    emails = FakeEmailList().getFakeEmails(),
                )


        }
        composable(DrawerDestinations.PRIMARY) {
            CommonEmailListScreen(
                onNavigate = navigateTo,
                closeDrawer = closeDrawer,
                openDrawer = openDrawer,
                onFabClick = navigateToComposeEmail,
                onEmailItemClick = navigateToOpenEmail,
                drawerState = drawerState,
                emails = FakeEmailList().getFakeEmails()
            )
        }
        composable(DrawerDestinations.PROMOTIONS) {
            CommonEmailListScreen(
                onNavigate = navigateTo,
                closeDrawer = closeDrawer,
                openDrawer = openDrawer,
                onFabClick = navigateToComposeEmail,
                onEmailItemClick = navigateToOpenEmail,
                drawerState = drawerState,
                emails = FakeEmailList().getFakeEmails()
            )
        }
        composable(DrawerDestinations.SOCIAL) {
            CommonEmailListScreen(
                onNavigate = navigateTo,
                closeDrawer = closeDrawer,
                openDrawer = openDrawer,
                onFabClick = navigateToComposeEmail,
                onEmailItemClick = navigateToOpenEmail,
                drawerState = drawerState,
                emails = FakeEmailList().getFakeEmails()
            )
        }
        composable(DrawerDestinations.UPDATES) {
            CommonEmailListScreen(
                onNavigate = navigateTo,
                closeDrawer = closeDrawer,
                openDrawer = openDrawer,
                onFabClick = navigateToComposeEmail,
                onEmailItemClick = navigateToOpenEmail,
                drawerState = drawerState,
                emails = FakeEmailList().getFakeEmails()
            )
        }
        composable(DrawerDestinations.FORUMS) {
            CommonEmailListScreen(
                onNavigate = navigateTo,
                closeDrawer = closeDrawer,
                openDrawer = openDrawer,
                onFabClick = navigateToComposeEmail,
                onEmailItemClick = navigateToOpenEmail,
                drawerState = drawerState,
                emails = FakeEmailList().getFakeEmails()
            )
        }

        composable(NonTopDestinations.COMPOSE_EMAIL) {
            ComposeEmail()
        }
        composable(NonTopDestinations.OPEN_EMAIL) {
            OpenEmail()
        }
    }

}

object NonTopDestinations {
    const val OPEN_EMAIL = "open email"
    const val COMPOSE_EMAIL = "compose email"

}
