package com.example.yit.yit_gallery

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import com.example.utils.observe

@Composable
fun GalleryView(
    navigateToProduct: (id: Int) -> Unit,
    viewModel: GalleryViewModel
) {

    LocalLifecycleOwner.current.lifecycle.observe {
        viewModel.handle(it)
    }

}