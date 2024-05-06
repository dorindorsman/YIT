package com.example.yit

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.yit.MainPage.GALLERY
import com.example.yit.yit_gallery.GalleryView
import com.example.yit.yit_gallery.GalleryViewModelFactory

object MainPage {
    const val GALLERY = "GALLERY"
    const val IMAGE = "IMAGE"
}

@Composable
fun MainNavigation(
    navController: NavHostController,
    modifier: Modifier
) {
    val appContext = LocalContext.current.applicationContext
    NavHost(navController, startDestination = GALLERY, modifier = modifier) {
        composable(route = GALLERY) {
            GalleryView(
                navigateToProduct = { id ->
                    navController.navigate(route = "Product/$id")
                },
                viewModel(
                    factory = GalleryViewModelFactory(appContext)
                )
            )
        }
    }
}