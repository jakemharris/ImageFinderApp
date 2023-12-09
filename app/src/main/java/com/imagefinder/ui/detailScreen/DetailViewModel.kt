package com.imagefinder.ui.detailScreen

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imagefinder.FragmentUIEvent
import com.imagefinder.dto.ImageModel
import com.imagefinder.network.NetworkResult
import com.imagefinder.repository.ImageRepository
import com.imagefinder.ui.detailScreen.DetailFragment.Companion.IMAGE_ID_TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
    private val events: DetailFragmentEvents
) : ViewModel() {

    private val viewState = MutableStateFlow(DetailScreenViewState())
    val observableState: StateFlow<DetailScreenViewState> = viewState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewState.value
        )
    private val eventPublisher = MutableLiveData<FragmentUIEvent<DetailFragment>>()
    val eventObservable: LiveData<FragmentUIEvent<DetailFragment>> = eventPublisher

    fun onCreate(bundle: Bundle) {
        val imageId = bundle.getString(IMAGE_ID_TAG, "")
        fetchImageDetails(imageId)
    }

    fun onClickBack() {
        eventPublisher.value = events.navigateBackToList()
    }

    private fun fetchImageDetails(imageId: String) {
        viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (val result = imageRepository.getPhotoByID(imageId)) {
                is NetworkResult.Success -> {
                    viewState.update {
                        it.copy(
                            isLoading = false,
                            imageData = result.data
                        )
                    }
                }

                is NetworkResult.Error -> {
                    viewState.update {
                        it.copy(
                            isLoading = false,
                            errorMessage = result.exception.message.orEmpty()
                        )
                    }
                }
            }
        }
    }
}

data class DetailScreenViewState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val imageData: ImageModel? = null
)
