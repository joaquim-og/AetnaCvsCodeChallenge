package com.confradestech.aetna_cvs_code_challenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.confradestech.aetna_cvs_code_challenge.R
import com.confradestech.aetna_cvs_code_challenge.commom.theme.AetnaCvsCodeChallengeTheme
import com.confradestech.aetna_cvs_code_challenge.commom.ui.MediumTitleComponent
import com.confradestech.aetna_cvs_code_challenge.ui.screen.DetailScreen
import com.confradestech.aetna_cvs_code_challenge.ui.screen.HomeScreen
import com.confradestech.aetna_cvs_code_challenge.ui.viewModel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel by viewModels<HomeViewModel>()

    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AetnaCvsCodeChallengeTheme {

                val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

                NavigableListDetailPaneScaffold(
                    navigator = navigator,
                    listPane = {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding)
                            ) {
                                HomeScreen(
                                    homeUiState = homeViewModel.homeContentState,
                                    onSearch = { searchTag ->
                                        homeViewModel.getFlickrPhotos(searchTag)
                                    },
                                    onItemTapped = {
                                        homeViewModel.updateSelectedDetailsItem(it)
                                        navigator.navigateTo(
                                            pane = ListDetailPaneScaffoldRole.Detail
                                        )
                                    }
                                )
                            }
                        }
                    },
                    detailPane = {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(innerPadding),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                homeViewModel.detailsItem?.let {
                                    DetailScreen(flickrItem = it)
                                }
                                    ?: MediumTitleComponent(text = stringResource(R.string.no_details_selected))
                            }
                        }
                    }
                )
            }
        }
    }
}
