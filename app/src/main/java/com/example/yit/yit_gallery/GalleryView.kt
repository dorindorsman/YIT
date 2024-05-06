package com.example.yit.yit_gallery

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import com.example.utils.observe
import com.example.yit_images.GridImageView

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun GalleryView(
    navigateToProduct: (id: Int) -> Unit,
    viewModel: GalleryViewModel
) {

    LocalLifecycleOwner.current.lifecycle.observe {
        viewModel.handle(it)
    }

    Scaffold (
        topBar = { SearchImageBar(Modifier.fillMaxWidth(), viewModel) }
    ){
        Spacer(modifier = Modifier.height(30.dp))
        GalleryGrid(Modifier.padding(it), viewModel)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchImageBar(modifier: Modifier = Modifier, viewModel: GalleryViewModel){
    SearchBar(
        modifier = modifier.fillMaxWidth(),
        query = viewModel.currentText,
        onQueryChange = {
            viewModel.handle(GalleryEvent.SetSearchText(it))
            //text = it
        },
        onSearch = {
            viewModel.handle(GalleryEvent.SetSearchActive(false))
            viewModel.handle(GalleryEvent.AddHistorySearch(it))

            //viewModel.handle(GalleryEvent.SetSearchText(""))
            // active = false
//                items.add(0,text)
            //Fixme
            //text = ""
        },
        active = viewModel.currentActive,
        onActiveChange = {
            viewModel.handle(GalleryEvent.SetSearchActive(it))
//                active = it
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
        },
        trailingIcon = {
            if (viewModel.currentActive) {
                IconButton(onClick = {
                    if (viewModel.currentText.isNotEmpty()) {
                        viewModel.handle(GalleryEvent.SetSearchText(""))
                    } else {
                        viewModel.handle(GalleryEvent.SetSearchActive(false))
                    }
                }) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close Icon")
                }
            }
//FIXME
//                Row {
//
//                    Spacer(modifier = Modifier.width(Icons.Default.Close.defaultWidth.times(0.5f)))
//                    IconButton(onClick = { /*TODO*/ }) {
//                        Text(text = "GO")
//                    }
//                }
        }
    ) {
        viewModel.history.forEach {
            Row(modifier = Modifier.padding(all = 14.dp)) {
                Icon(modifier = Modifier.padding(end = 10.dp), imageVector = Icons.Default.History, contentDescription = "History Icon")
                Text(text = it)
            }
        }
    }
}

@Composable
fun GalleryGrid(modifier: Modifier = Modifier, viewModel: GalleryViewModel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(minSize = 100.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalItemSpacing = 8.dp
    ) {
        items(viewModel.images){
            GridImageView(it)
        }
    }
}
