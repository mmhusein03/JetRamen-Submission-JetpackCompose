package com.md29.husein.jetramen.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.md29.husein.jetramen.data.model.OrderMenu
import com.md29.husein.jetramen.data.repo.MenuRepository
import com.md29.husein.jetramen.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MenuRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMenu>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderMenu>>>
        get() = _uiState

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllMenus()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    private val _query = mutableStateOf("")
    val query: State<String> get() = _query

    fun search(newQuery: String) {
        _query.value = newQuery
        viewModelScope.launch {
            repository.searchMenus(_query.value)
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { orderMenus ->
                    _uiState.value = UiState.Success(orderMenus)
                }
        }
    }
}