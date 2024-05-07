package com.example.yit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.yit.MainPage.GALLERY
import com.example.yit.MainPage.IMAGE
import com.example.yit.local.repository.PreferencesManager
import com.example.yit.yit_gallery.GalleryView
import com.example.yit.yit_gallery.GalleryViewModelFactory
import com.example.yit_image.ImageDisplayView
import com.example.yit_image.ImageDisplayViewModelFactory

object MainPage {
    const val GALLERY = "GALLERY"
    const val IMAGE = "IMAGE"
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier,
    preferencesManager: PreferencesManager
) {
    val appContext = LocalContext.current.applicationContext
    NavHost(navController, startDestination = GALLERY, modifier = modifier) {
        composable(route = GALLERY) {
            GalleryView(
                navigateToImage = { id ->
                    navController.navigate(route = "$IMAGE/$id")
                },
                viewModel(
                    factory = GalleryViewModelFactory(preferencesManager)
                )
            )
        }

        composable(
            route = "$IMAGE/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )) { navBackStackEntry ->
            val imageId = navBackStackEntry.arguments?.getInt("id") ?: 0

            ImageDisplayView(
                viewModel (
                    factory = ImageDisplayViewModelFactory(imageId)
                )
            )
        }
    }
}