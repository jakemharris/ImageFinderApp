package com.imagefinder.viewModelTests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.imagefinder.nontest.dto.ImageModel
import com.imagefinder.nontest.network.NetworkResult
import com.imagefinder.repository.ImageRepository
import com.imagefinder.vm.DetailViewModel
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

@OptIn(ExperimentalCoroutinesApi::class)
class DetailViewModelTest {

    @Rule
    @JvmField
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()


    private lateinit var viewModel: DetailViewModel
    private lateinit var mockImageRepository: ImageRepository

    val searchString = "Beach"


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockImageRepository = mock()

        viewModel = DetailViewModel(
            mockImageRepository,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `Should return image details `() = runTest {
        // Given
        val image = ImageModel(
            alt = "pic1",
            id = "id1",
            url = "",
            photographer = "",
            src = null
        )

        whenever(mockImageRepository.getPhotoByID("")).thenReturn(
            NetworkResult.Success(image)
        )

        // When
        viewModel.fetchImageDetails("")

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assertEquals(image, state.imageData)
        }
    }

    @Test
    fun `Test error state`() = runTest {
        // Given
        whenever(mockImageRepository.getPhotoByID("")).thenReturn(
            NetworkResult.Error(Exception("no image found"))
        )

        // When
        viewModel.fetchImageDetails("")

        // Then
        viewModel.observableState.test {
            val state = awaitItem()
            assertEquals("no image found", state.errorMessage)
        }
    }
//
//    @Test
//    fun `Search string should be in state`() = runTest {
//        // When
//        viewModel.onTextChange(searchString)
//
//        // Then
//        viewModel.observableState.test {
//            val state = awaitItem()
//            assert(state.searchString == searchString)
//        }
//    }
//
//    @Test
//    fun `Test clear search field`() = runTest {
//        // When
//        viewModel.onTextChange(searchString)
//        viewModel.clearSearchString()
//
//        // Then
//        viewModel.observableState.test {
//            val state = awaitItem()
//            assert(state.searchString == "")
//        }
//    }
}
