package com.imagefinder.ui.searchScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imagefinder.FragmentUIEvent
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
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val imageRepository: ImageRepository,
//    private val movieRepo: MovieRepo,
    private val events: SearchFragmentEvents
) : ViewModel() {

    private val viewState = MutableStateFlow(SearchScreenViewState())
    val observableState: StateFlow<SearchScreenViewState> = viewState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = viewState.value
        )
    private val eventPublisher = MutableLiveData<FragmentUIEvent<SearchFragment>>()
    val eventObservable: LiveData<FragmentUIEvent<SearchFragment>> = eventPublisher


    private fun fetchImages(searchString: String) {
        if (searchString.isBlank()) return
        Log.w("jake", "fetch images =" + searchString)
        viewState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            imageRepository.getImages(searchString)
            when (val result = imageRepository.getImages(searchString)) {
                is NetworkResult.Success -> {
                    Log.w("jake", "got images in VM =" + result.data.toString())
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
        eventPublisher.value = events.dismissSoftKeyboard()
        fetchImages(viewState.value.searchString)
    }

    fun onImageClick(imageId: String) {
        eventPublisher.value = events.navigateToDetails(imageId)
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