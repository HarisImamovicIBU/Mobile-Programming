package com.example.brainscript.ui.theme.navigation
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.brainscript.model.User
import com.example.brainscript.ui.theme.navigation.types.CustomNavType
import com.example.brainscript.ui.theme.HomeScreen
import com.example.brainscript.ui.theme.LoginScreen
import com.example.brainscript.ui.theme.RegistrationScreen
import com.example.brainscript.ui.theme.CategoryScreen
import com.example.brainscript.ui.theme.ProfileScreen
import com.example.brainscript.ui.theme.navigation.BottomNavigationBar
import com.example.brainscript.vmodels.UserViewModel
import com.example.brainscript.vmodels.CategoryViewModel
import com.example.brainscript.vmodels.HomeVModel
import kotlinx.serialization.Serializable
import kotlin.math.log
import kotlin.reflect.typeOf

//Nested graph serializable
@Serializable object Auth

//Routes for nested Auth graph
@Serializable object Login
@Serializable object Register
@Serializable object ForgotPassword

//Nested graph Main
@Serializable object Main

@Serializable data class Home(val user: User)
@Serializable data class Category(val user: User)
@Serializable data class Profile(val user: User)

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val showBottomBar = (currentRoute?.split('/')?.get(0)) in listOf(
        Home::class.qualifiedName,
        Category::class.qualifiedName,
        Profile::class.qualifiedName
    )

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val user = remember { navBackStackEntry!!.toRoute<Home>().user }
                BottomNavigationBar(
                    user,
                    onCategoryNav = { navController.navigate(Category(user)) },
                    onHomeNav = { navController.navigate(Home(user)) {
                        popUpTo(Main) { saveState = true }
                        launchSingleTop = true
                    } },
                    onProfileNav = {
                        navController.navigate(Profile(it))
                    }
                )
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Auth, modifier = Modifier.padding(padding)
        ) {
            navigation<Auth>(startDestination = Login) {
                composable<Login> { backStackEntry ->
                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                    LoginScreen(authViewModel, onRegisterNav = {
                        navController.navigate(route = Register)
                    }, onSuccessLogin = {user: User ->
                        navController.navigate(route = Home(user = user)) {
                            popUpTo(route = Auth) { inclusive = true }
                        }
                    })
                }
                composable<Register> { backStackEntry ->
                    val authViewModel: UserViewModel = hiltViewModel(backStackEntry)
                    RegistrationScreen(authViewModel, onLoginNav = {
                        navController.navigate(route = Login)
                    })
                }
            }

            navigation<Main>(startDestination = Home(user = User(1, "", "", "", "", "", 0))) {
                composable<Home>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val homeViewModel: HomeVModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<Home>().user
                    HomeScreen(loggedUser = loggedUser, homeViewModel)
                }

                composable<Category>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val categoryViewModel: CategoryViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<Category>().user
                    CategoryScreen(loggedUser, categoryViewModel)
                }

                composable<Profile>(
                    typeMap = mapOf(
                        typeOf<User>() to CustomNavType.UserType
                    )
                ) { backStackEntry ->
                    val userViewModel: UserViewModel = hiltViewModel(backStackEntry)
                    val loggedUser = backStackEntry.toRoute<Profile>().user
                    ProfileScreen(loggedUser = loggedUser, userViewModel = userViewModel)
                }


            }
        }
    }
}