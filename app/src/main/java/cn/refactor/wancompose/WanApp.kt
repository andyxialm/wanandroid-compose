package cn.refactor.wancompose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import cn.refactor.wancompose.arch.graphs.NavGraphs
import cn.refactor.wancompose.arch.graphs.mainBottomNavigationItems
import cn.refactor.wancompose.ui.blog.BlogScreen
import cn.refactor.wancompose.ui.home.HomeScreen
import cn.refactor.wancompose.ui.profile.ProfileScreen
import cn.refactor.wancompose.ui.project.ProjectScreen
import cn.refactor.wancompose.ui.square.SquareScreen

/**
 * Created on 2022/10/30.
 *
 * @author andy
 */
@Composable
fun WanApp() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = { BottomNavigationBar(navController = navController) }
        ) { innerPadding ->
            NavHost(navController, startDestination = NavGraphs.HOME.route, Modifier.padding(innerPadding)) {
                composable(NavGraphs.HOME.route) {
                    HomeScreen(navController)
                }
                composable(NavGraphs.SQUARE.route) {
                    SquareScreen(navController)
                }
                composable(NavGraphs.PROJECT.route) {
                    ProjectScreen(navController)
                }
                composable(NavGraphs.BLOG.route) {
                    BlogScreen(navController)
                }
                composable(NavGraphs.PROFILE.route) {
                    ProfileScreen(navController)
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        mainBottomNavigationItems().forEach { item ->
            BottomNavigationItem(
                selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                icon = {
                    Column {
                        Icon(
                            painter = painterResource(item.iconResourceId),
                            contentDescription = stringResource(id = item.textResourceId),
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                },
                label = { Text(stringResource(item.textResourceId)) },
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}