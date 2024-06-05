package com.confradestech.aetna_cvs_code_challenge.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.confradestech.aetna_cvs_code_challenge.commom.utils.Constants
import com.confradestech.aetna_cvs_code_challenge.domain.model.FlickrItem
import com.confradestech.aetna_cvs_code_challenge.domain.model.uiStates.HomeUiState
import com.confradestech.aetna_cvs_code_challenge.domain.useCase.GetFlickrPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFlickrPhotoUseCase: GetFlickrPhotoUseCase,
) : ViewModel() {

    private val lock = Any()

    var homeContentState by mutableStateOf(HomeUiState())
        private set

    var detailsItem by mutableStateOf<FlickrItem?>(null)
        private set

    fun getFlickrPhotos(tags: String) {
        viewModelScope.launch(Constants.requestDispatchers) {
            updateHomeUILoadingState(true)
            getFlickrPhotoUseCase
                .getFlickrPhotos(tags)
                .distinctUntilChanged().catch {
                    updateHomeUILoadingState(false)
                    updateHomeUIErrorState(it)
                }.collectLatest { photos ->
                    updateHomeUILoadingState(false)
                    updateHomeUIContentState(photos?.flickrItems)
                }
        }
    }

    override fun onCleared() {
        super.onCleared()
        resetHomeUIState()
    }

    fun updateSelectedDetailsItem(flickrItem: FlickrItem) {
        synchronized(lock) {
            detailsItem = flickrItem
        }
    }

    private fun updateHomeUIContentState(flickrItems: List<FlickrItem>?) {
        synchronized(lock) {
            homeContentState = homeContentState.copy(
                flickrItems = flickrItems,
            )
        }
    }

    private fun updateHomeUILoadingState(isLoading: Boolean) {
        synchronized(lock) {
            homeContentState = homeContentState.copy(
                isLoading = isLoading,
                error = null
            )
        }
    }

    private fun updateHomeUIErrorState(error: Throwable) {
        synchronized(lock) {
            homeContentState = homeContentState.copy(
                isLoading = false,
                error = error
            )
        }
    }

    private fun resetHomeUIState() {
        synchronized(lock) {
            homeContentState = HomeUiState()
        }
    }

}
