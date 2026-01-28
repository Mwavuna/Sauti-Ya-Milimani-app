package com.example.sautiyamilimani_test1.presentation.pages.members

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.automirrored.outlined.Login
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sautiyamilimani_test1.presentation.navigation.Screen
import com.example.sautiyamilimani_test1.presentation.pages.auth.Page
import com.example.sautiyamilimani_test1.presentation.viewmodels.AuthViewModel
import kotlinx.coroutines.launch


data class NavDrawerItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembersPage(modifier: Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val currentUser by authViewModel.currentUser.collectAsState()
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    var selectedDrawerItemIndex by rememberSaveable { mutableIntStateOf(0) }
    var isAnonymous by rememberSaveable {
        mutableStateOf(currentUser?.isAnonymous ?: true)
    }

    val navDrawerItems = listOf<NavDrawerItem>(
        NavDrawerItem(
            title = "logout",
            selectedIcon = Icons.Filled.Logout,
            unSelectedIcon = Icons.Outlined.Logout,
        ),
        NavDrawerItem(
            title = "Switch Account",
            selectedIcon = Icons.Filled.Login,
            unSelectedIcon = Icons.Outlined.Login
        ),
        NavDrawerItem(
            title = "Delete Account",
            selectedIcon = Icons.Filled.Delete,
            unSelectedIcon = Icons.Outlined.Delete
        ),
        NavDrawerItem(
            title = "Create Account",
            selectedIcon = Icons.Filled.PersonAdd,
            unSelectedIcon = Icons.Outlined.PersonAdd
        ),

        )




    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    if (currentUser !=null && isAnonymous ) "Guest" else if (currentUser==null) "No account" else "${currentUser?.email}",
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                )
                Spacer(Modifier.height(12.dp))

                navDrawerItems.forEachIndexed { index, item ->
                    if (isAnonymous && (item.title == "Create Account") ) {
                        NavigationDrawerItem(
                            label = { Text(item.title) },
                            selected = selectedDrawerItemIndex == index,
                            onClick = {
                                selectedDrawerItemIndex = index
                                navController.navigate(Screen.Login)
                            },
                            icon = {
                                Icon(
                                    if (selectedDrawerItemIndex == index) item.selectedIcon else item.unSelectedIcon,
                                    item.title
                                )
                            }

                        )

                    } else if (!isAnonymous && (item.title != "Create Account") )  {
                        when(item.title){
                            "logout"->{
                                NavigationDrawerItem(
                                    label = { Text(item.title) },
                                    selected = selectedDrawerItemIndex == index,
                                    onClick = {
                                        selectedDrawerItemIndex = index
                                        scope.launch {
                                            authViewModel.logout()
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            if (selectedDrawerItemIndex == index) item.selectedIcon else item.unSelectedIcon,
                                            item.title
                                        )
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                            "Delete Account"->{
                                NavigationDrawerItem(
                                    label = { Text(item.title) },
                                    selected = selectedDrawerItemIndex == index,
                                    onClick = {
                                        selectedDrawerItemIndex = index
                                    },
                                    icon = {
                                        Icon(
                                            if (selectedDrawerItemIndex == index) item.selectedIcon else item.unSelectedIcon,
                                            item.title
                                        )
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                            "Switch Account"->
                            {
                                NavigationDrawerItem(
                                    label = { Text(item.title) },
                                    selected = selectedDrawerItemIndex == index,
                                    onClick = {
                                        selectedDrawerItemIndex = index
                                        navController.navigate(Screen.Login)
                                    },
                                    icon = {
                                        Icon(
                                            if (selectedDrawerItemIndex == index) item.selectedIcon else item.unSelectedIcon,
                                            item.title
                                        )
                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                                )
                            }
                        }

                    }


                }

            }

        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                CenterAlignedTopAppBar(
                    title = { Text("Sauti Ya Milimani") },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }
                        ) {
                            Icon(Icons.Outlined.Menu, "Menu")
                        }
                    }

                )

            }

        ) { paddingValues ->
            Surface(modifier = Modifier.padding(paddingValues)) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        "Members Screen",
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displayLarge
                    )
                }
            }
        }
    }
}