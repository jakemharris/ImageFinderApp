package com.imagefinder.ui.detailScreen


import androidx.navigation.NavController
import com.imagefinder.FragmentUIEvent
import javax.inject.Inject

class DetailFragmentEvents @Inject constructor() {
    fun navigateBackToList() = NavigateBackToList()
}

class NavigateBackToList : FragmentUIEvent<DetailFragment>() {
    override fun doExecute(fragment: DetailFragment, navController: NavController) {
        navController.popBackStack()
    }
}
