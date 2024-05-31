package com.md29.husein.jetramen.data.repo

import com.md29.husein.jetramen.data.model.FakeMenuData
import com.md29.husein.jetramen.data.model.OrderMenu
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MenuRepository {

    private val orderMenus = mutableListOf<OrderMenu>()

    init {
        if (orderMenus.isEmpty()) {
            FakeMenuData.dummyMenu.forEach {
                orderMenus.add(OrderMenu(it, 0))
            }
        }
    }

    fun getAllMenus(): Flow<List<OrderMenu>> {
        return flowOf(orderMenus)
    }

    fun getOrderMenusById(menusId: Long): OrderMenu {
        return orderMenus.first {
            it.menu.id == menusId
        }
    }

    fun updateOrderMenus(menusId: Long, newCountValue: Int): Flow<Boolean> {
        val index = orderMenus.indexOfFirst { it.menu.id == menusId }
        val result = if (index >= 0) {
            val orderMenu = orderMenus[index]
            orderMenus[index] =
                orderMenu.copy(menu = orderMenu.menu, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderMenus(): Flow<List<OrderMenu>> {
        return getAllMenus()
            .map { orderMenus ->
                orderMenus.filter { orderMenu ->
                    orderMenu.count != 0
                }
            }
    }

    fun searchMenus(query: String): Flow<List<OrderMenu>> {
        return flowOf(orderMenus.filter {
            it.menu.title.contains(query, ignoreCase = true)
        })
    }

    companion object {
        @Volatile
        private var instance: MenuRepository? = null

        fun getInstance(): MenuRepository =
            instance ?: synchronized(this) {
                MenuRepository().apply {
                    instance = this
                }
            }
    }

}