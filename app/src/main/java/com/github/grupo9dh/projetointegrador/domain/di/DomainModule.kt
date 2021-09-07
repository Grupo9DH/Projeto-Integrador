package com.github.grupo9dh.projetointegrador.domain.di

import com.github.grupo9dh.projetointegrador.domain.ListMoviesUseCase
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModule {
    fun load() {
        loadKoinModules(domainModules())
    }

    private fun domainModules() : Module {
        return module {
            factory {
                ListMoviesUseCase(get())
            }
        }
    }
}