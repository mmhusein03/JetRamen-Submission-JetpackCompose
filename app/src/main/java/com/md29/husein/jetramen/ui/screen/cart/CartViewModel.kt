package com.md29.husein.jetramen.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.md29.husein.jetramen.data.repo.MenuRepository
import com.md29.husein.jetramen.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: MenuRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>>
        get() = _uiState

    fun getAddedOrderMenus() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getAddedOrderMenus()
                .collect { orderReward ->
                    val totalRequiredPoint =
                        orderReward.sumOf { it.menu.price * it.count }
                    _uiState.value = UiState.Success(CartState(orderReward, totalRequiredPoint))
                }
        }
    }

    fun updateOrderReward(menuId: Long, count: Int) {
        viewModelScope.launch {
            repository.updateOrderMenus(menuId, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderMenus()
                    }
                }
        }
    }
}