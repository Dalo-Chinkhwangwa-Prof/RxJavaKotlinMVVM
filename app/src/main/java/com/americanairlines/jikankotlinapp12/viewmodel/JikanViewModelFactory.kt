package com.americanairlines.jikankotlinapp12.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

object JikanViewModelFactory : ViewModelProvider.Factory {
    var viewModel: JikanViewModel? = JikanViewModel()
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        viewModel?.let {
            return viewModel as T
        } ?: {
            viewModel = JikanViewModel()
        }()

        return viewModel as T
    }
}