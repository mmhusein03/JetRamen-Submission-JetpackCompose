package com.md29.husein.jetramen.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.md29.husein.jetramen.data.repo.MenuRepository
import com.md29.husein.jetramen.ui.screen.cart.CartViewModel
import com.md29.husein.jetramen.ui.screen.detail.DetailViewModel
import com.md29.husein.jetramen.ui.screen.home.HomeViewModel

class ViewModelFactory(private val repository: MenuRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(repository) as T
        } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
            return CartViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}