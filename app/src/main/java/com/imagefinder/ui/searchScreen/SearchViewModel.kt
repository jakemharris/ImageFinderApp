package com.imagefinder.ui.searchScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imagefinder.dto.ImageModel
import com.imagefinder.network.NetworkResult
import com.imagefinder.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
) : ViewModel() {

    private val viewState = MutableStateFlow(SearchScreenViewState())
    val observableState: StateFlow<SearchScreenViewState> = viewState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewState.value
        )

    @VisibleForTesting
    fun fetchImages(searchString: String) {
        if (searchString.isBlank()) return
        viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            imageRepository.getImages(searchString)
            when (val result = imageRepository.getImages(searchString)) {
                is NetworkResult.Success -> {
                    viewState.update {
                        it.copy(
                            isLoading = false,
                            hasErrorMessage = null,
                            listOfImages = result.data
                        )
                    }
                }

                is NetworkResult.Error -> {
                    viewState.update {
                        it.copy(
                            isLoading = false,
                            hasErrorMessage = result.exception.message
                        )
                    }
                }
            }
        }
    }

    fun onClickSearch() {
        fetchImages(viewState.value.searchString)
    }

    fun onImageClick(imageId: String) {
        imageRepository.selectedImageId = imageId
    }

    fun onTextChange(searchText: String) {
        viewState.update { it.copy(searchString = searchText) }
    }

    fun clearSearchString() {
        viewState.update { it.copy(searchString = "") }
    }
}


data class SearchScreenViewState(
    val isLoading: Boolean = false,
    val hasErrorMessage: String? = null,
    val listOfImages: List<ImageModel> = listOf(),
    val searchString: String = ""
)
