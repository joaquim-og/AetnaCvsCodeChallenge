package com.confradestech.aetna_cvs_code_challenge.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.confradestech.aetna_cvs_code_challenge.R
import com.confradestech.aetna_cvs_code_challenge.commom.theme.AetnaCvsCodeChallengeTheme
import com.confradestech.aetna_cvs_code_challenge.commom.ui.FlickrImageCardComponent
import com.confradestech.aetna_cvs_code_challenge.commom.ui.MediumTitleComponent
import com.confradestech.aetna_cvs_code_challenge.commom.ui.SearchBarComponent
import com.confradestech.aetna_cvs_code_challenge.commom.utils.PreviewProfileScreens
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem
import com.confradestech.aetna_cvs_code_challenge.domain.model.uiStates.HomeUiState

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    onSearch: (String) -> Unit,
    onItemTapped: (FlickrItem) -> Unit
) {

    var searchedTag by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BuildTopSearchBar(
            onSearch = {
                searchedTag = it
                onSearch(it)
            }
        )
        Spacer(modifier = Modifier.height(12.dp))
        AnimatedVisibility(
            visible = homeUiState.error != null,
            enter = expandVertically(animationSpec = tween(durationMillis = 1000)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000)) + fadeOut(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
            MediumTitleComponent(
                text = stringResource(R.string.home_error_on_call_api)
            )
                }
        }
        AnimatedVisibility(
            visible = homeUiState.isLoading,
            enter = expandVertically(animationSpec = tween(durationMillis = 1000)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000)) + fadeOut(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            CircularProgressIndicator(modifier = Modifier.size(52.dp))
        }
        AnimatedVisibility(
            visible = !homeUiState.flickrItems.isNullOrEmpty(),
            enter = expandVertically(animationSpec = tween(durationMillis = 1000)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000)) + fadeOut(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            BuildImagesList(
                flickrItems = homeUiState.flickrItems,
                searchedTag = searchedTag,
                onItemTapped = onItemTapped
            )
        }
        AnimatedVisibility(
            visible = homeUiState.flickrItems?.isEmpty() == true,
            enter = expandVertically(animationSpec = tween(durationMillis = 1000)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000)) + fadeOut(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MediumTitleComponent(
                    text = stringResource(R.string.home_no_images_to_list)
                )
            }
        }
        AnimatedVisibility(
            visible = homeUiState.flickrItems == null && homeUiState.error == null,
            enter = expandVertically(animationSpec = tween(durationMillis = 1000)),
            exit = shrinkVertically(animationSpec = tween(durationMillis = 1000)) + fadeOut(
                animationSpec = tween(
                    durationMillis = 800
                )
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                MediumTitleComponent(
                    text = stringResource(R.string.home_no_search_images)
                )

            }
        }

    }
}

@Composable
private fun BuildTopSearchBar(
    onSearch: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        SearchBarComponent(onSearch = onSearch)
    }
}

@Composable
private fun BuildImagesList(
    flickrItems: List<FlickrItem>?,
    searchedTag: String,
    onItemTapped: (FlickrItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 12.dp,
                end = 12.dp,
                bottom = 12.dp
            )
    ) {
        if (searchedTag.isNotEmpty()) {
            MediumTitleComponent(
                text = stringResource(R.string.home_searched_tag).replace(
                    "#value",
                    searchedTag
                )
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        flickrItems?.let { items ->
            LazyVerticalGrid(
                modifier = Modifier,
                columns = GridCells.Adaptive(minSize = 152.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(10.dp)
            ) {
                itemsIndexed(items) { _, flickrItem ->
                    FlickrImageCardComponent(
                        flickrImage = flickrItem,
                        onItemTapped = onItemTapped
                    )
                }
            }
        }

    }
}

@PreviewProfileScreens
@Composable
fun HomePreview() {
    AetnaCvsCodeChallengeTheme {
        HomeScreen(
            homeUiState = HomeUiState(),
            onSearch = { /*No-op for previews */ },
            onItemTapped = { /*No-op for previews */ }
        )
    }
}