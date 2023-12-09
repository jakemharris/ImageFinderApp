package com.imagefinder.ui.searchScreen

import android.os.Bundle
import androidx.navigation.NavController
import com.imagefinder.FragmentUIEvent
import com.imagefinder.MainActivity
import com.imagefinder.R
import com.imagefinder.ui.detailScreen.DetailFragment.Companion.IMAGE_ID_TAG
import javax.inject.Inject

class SearchFragmentEvents @Inject constructor() {
    fun navigateToDetails(imageId: String) = NavigateToDetailsEvent(imageId)
    fun dismissSoftKeyboard() = DismissSoftKeyboardEvent()

}

class NavigateToDetailsEvent(val imageId: String) : FragmentUIEvent<SearchFragment>() {
    override fun doExecute(fragment: SearchFragment, navController: NavController) {
        val bundle = Bundle()
        bundle.putString(IMAGE_ID_TAG, imageId)
        navController.navigate(R.id.action_search_to_details, bundle)
    }
}

class DismissSoftKeyboardEvent : FragmentUIEvent<SearchFragment>() {
    override fun doExecute(fragment: SearchFragment, navController: NavController) {
        val activity = fragment.activity as MainActivity
        fragment.view?.let { activity.hideKeyboard(it) }
    }
}
