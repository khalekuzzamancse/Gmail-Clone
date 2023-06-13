package com.khalekuzzaman.just.cse.gmailclone.ui.navigation

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmail
import com.khalekuzzaman.just.cse.gmailclone.data.FakeEmailList
import com.khalekuzzaman.just.cse.gmailclone.ui.common.labelscreencomponent.CommonLabelListScreen
import com.khalekuzzaman.just.cse.gmailclone.ui.common.CommonListScreen
import com.khalekuzzaman.just.cse.gmailclone.ui.common.DrawerDestinations
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel

import com.khalekuzzaman.just.cse.gmailclone.ui.screens.ComposeEmail
import com.khalekuzzaman.just.cse.gmailclone.ui.screens.ReadEmailScreen
import kotlinx.coroutines.launch

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

    var readEmail: EmailModel = FakeEmail.email;
    val navigationActions = NavigationActions(navController)
    val navigateTo: (String) -> Unit = { route ->
        navigationActions.navigateTo(route)
    }
    val navigateToOpenEmail: (EmailModel) -> Unit = {
        readEmail = it
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
            CommonListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakeEmails()
            )

        }
        composable(DrawerDestinations.PRIMARY) {
            CommonListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakePrimaryEmails()
            )
        }
        composable(DrawerDestinations.PROMOTIONS) {
            CommonListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakePromotionEmails()
            )
        }
        composable(DrawerDestinations.SOCIAL) {
            CommonListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakeSocialEmails()
            )
        }
        composable(DrawerDestinations.UPDATES) {
            CommonListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakeUpdateEmails()
            )
        }
        composable(DrawerDestinations.FORUMS) {

        }
        composable(DrawerDestinations.IMPORTANT) {
            CommonLabelListScreen(
                onDrawerItemClick = navigateTo,
                onBottomNavigationIconClick = navigateTo,
                closeDrawer = closeDrawer,
                drawerState = drawerState,
                openDrawer = openDrawer,
                profileImageResourceId = R.drawable.ic_profile_2,
                onFabIconClick = navigateToComposeEmail,
                onEmailClick = navigateToOpenEmail,
                emails = FakeEmailList.getFakeUpdateEmails()
            )
        }

        composable(NonTopDestinations.COMPOSE_EMAIL) {
            ComposeEmail()
        }
        composable(NonTopDestinations.OPEN_EMAIL) {
            ReadEmailScreen(email = readEmail)
        }
    }

}

object NonTopDestinations {
    const val OPEN_EMAIL = "open email"
    const val COMPOSE_EMAIL = "compose email"

}
