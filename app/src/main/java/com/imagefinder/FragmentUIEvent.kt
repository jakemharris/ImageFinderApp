package com.imagefinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavOptions

abstract class FragmentUIEvent<T : Fragment> {

    private var hasExecuted: Boolean = false

    fun execute(fragment: T, navController: NavController) {
        if (hasExecuted) {
            return
        }

        hasExecuted = true
        doExecute(fragment, navController)
    }


    protected abstract fun doExecute(fragment: T, navController: NavController)
}

fun NavController.safeNavigate(
    resId: Int,
    args: Bundle? = null,
    navOptions: NavOptions? = null,
) {
    val action = currentDestination?.getAction(resId) ?: graph.getAction(resId)
    if (action != null && currentDestination?.id != action.destinationId) {
        navigate(
            resId = resId,
            args = args,
            navOptions = navOptions,
        )
    }
}
