package com.imagefinder


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.imagefinder.nontest.dto.ImageModel
import com.imagefinder.nontest.dto.SearchPhotoResponse
import com.imagefinder.nontest.network.NetworkResult
import com.imagefinder.nontest.network.retrofit.NetworkService
import com.imagefinder.repository.ImageRepository
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
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ImageRepositoryTest {

    @Rule
    @JvmField
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var mockNetworkService: NetworkService
    private lateinit var imageRepository: ImageRepository

    val searchString = "Beach"


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        mockNetworkService = mock()

        imageRepository = ImageRepository(
            mockNetworkService,
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `Get images success`() = runTest {
        // Given
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
        val mockResponse = Response.success(SearchPhotoResponse(1, 20, images))

        whenever(mockNetworkService.getImagesRequest("d")).thenReturn(
            mockResponse
        )

        // When
        val response = imageRepository.getImages("d")

        // Then
        assertEquals(NetworkResult.Success(images), response)
    }

    @Test
    fun `Get details success`() = runTest {
        // Given
        val image =
            ImageModel(
                alt = "pic1",
                id = "id1",
                url = "",
                photographer = "",
                src = null
            )

        val mockResponse = Response.success(image)

        whenever(mockNetworkService.getPhotoRequest("id")).thenReturn(
            mockResponse
        )

        // When
        val response = imageRepository.getPhotoByID("id")

        // Then
        assertEquals(NetworkResult.Success(image), response)
    }


    @Test
    fun `Test error getting images`() = runTest {
        // Given
        whenever(mockNetworkService.getImagesRequest("f")).thenReturn(
            null
        )
        // When
        val response = imageRepository.getImages("f")
        val errorMessage = if (response is NetworkResult.Error) response.exception.message else ""
        // Then
        assertEquals("Network failed", errorMessage)
    }

    @Test
    fun `Test error getting image details`() = runTest {
        // Given
        whenever(mockNetworkService.getPhotoRequest("fd")).thenReturn(
            null
        )
        // When
        val response = imageRepository.getPhotoByID("fd")
        val errorMessage = if (response is NetworkResult.Error) response.exception.message else ""
        // Then
        assertEquals("Network failed", errorMessage)
    }
}
