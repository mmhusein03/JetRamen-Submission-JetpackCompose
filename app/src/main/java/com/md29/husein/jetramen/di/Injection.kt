package com.md29.husein.jetramen.di

import com.md29.husein.jetramen.data.repo.MenuRepository

object Injection {
    fun provideRepository(): MenuRepository {
        return MenuRepository.getInstance()
    }
}