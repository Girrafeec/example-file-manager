/* Created by Girrafeec */

package com.girrafeecstud.core_ui.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

abstract class BaseFragment<UiState>: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setListeners()
        registerObservers()
    }

    protected open fun registerObservers() {}

    protected open fun setListeners() {}

    protected open fun setState(state: UiState) {}
}