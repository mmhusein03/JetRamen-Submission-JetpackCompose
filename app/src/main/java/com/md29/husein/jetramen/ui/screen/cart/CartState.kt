package com.md29.husein.jetramen.ui.screen.cart

import com.md29.husein.jetramen.data.model.OrderMenu

data class CartState(
    val orderMenu: List<OrderMenu>,
    val totalPrice: Int
)