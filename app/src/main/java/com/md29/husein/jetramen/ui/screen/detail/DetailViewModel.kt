package com.md29.husein.jetramen.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.md29.husein.jetramen.data.model.Menu
import com.md29.husein.jetramen.data.model.OrderMenu
import com.md29.husein.jetramen.data.repo.MenuRepository
import com.md29.husein.jetramen.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MenuRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMenu>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMenu>>
        get() = _uiState

    fun getRewardById(rewardId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getOrderMenusById(rewardId))
        }
    }

    fun addToCart(menu: Menu, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMenus(menu.id, count)
        }
    }
}