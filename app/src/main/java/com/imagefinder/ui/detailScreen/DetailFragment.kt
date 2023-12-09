package com.imagefinder.ui.detailScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.imagefinder.ui.theme.FlickrDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment  : Fragment() {
    private val viewModel: DetailViewModel by viewModels()

    companion object{
        const val IMAGE_ID_TAG="IMAGE_ID_TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle -> viewModel.onCreate(bundle) }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                FlickrDemoTheme {
                    DetailScreen(
                        viewModel = viewModel
                    )
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.eventObservable.observe(this) { it.execute(this, findNavController()) }
    }

    override fun onPause() {
        viewModel.eventObservable.removeObservers(this)
        super.onPause()
    }
}
