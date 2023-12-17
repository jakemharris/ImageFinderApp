package com.imagefinder.viewModelTests


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.imagefinder.nontest.dto.ImageModel
import com.imagefinder.nontest.network.NetworkResult
import com.imagefinder.repository.ImageRepository
import com.imagefinder.vm.SearchViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()


    private lateinit var viewModel: SearchViewModel
    private lateinit var mockImageRepository: ImageRepository

    val searchString = "Beach"


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockImageRepository = mock()

        viewModel = SearchViewModel(
            mockImageRepository,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private suspend fun mockReposResponses() {
        val images = mutableListOf<ImageModel>()
        images.add(
            ImageModel(
                alt = "pic1",
                id = "id1",
                url = "",
                photographer = "",
                src = null
            )
        )
        images.add(
            ImageModel(
                alt = "pic2",
                id = "id2",
                url = "",
                photographer = "",
                src = null
            )
        )
        whenever(mockImageRepository.getImages(searchString)).thenReturn(
            NetworkResult.Success(images)
        )
    }

    @Test
    fun `Should return no images with empty search string`() = runTest {
        // Given
        mockReposResponses()

        // When
        viewModel.fetchImages("")

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assert(state.listOfImages.isEmpty())
        }
    }

    @Test
    fun `Should return images with valid search string`() = runTest {
        // Given
        mockReposResponses()

        // When
        viewModel.onTextChange(searchString)
        viewModel.fetchImages(searchString)

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assert(state.listOfImages.size == 2)
        }
    }

    @Test
    fun `Search string should be in state`() = runTest {
        // When
        viewModel.onTextChange(searchString)

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assert(state.searchString == searchString)
        }
    }

    @Test
    fun `Test clear search field`() = runTest {
        // When
        viewModel.onTextChange(searchString)
        viewModel.clearSearchString()

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assert(state.searchString == "")
        }
    }
    @Test
    fun `Error state`() = runTest {
        // When
        whenever(mockImageRepository.getImages(searchString)).thenReturn(
            NetworkResult.Error(Exception("No images found"))
        )
        viewModel.fetchImages(searchString)
        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assertEquals("No images found",state.errorMessage)
        }
    }

}
