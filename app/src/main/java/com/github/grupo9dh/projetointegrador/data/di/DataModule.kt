package com.github.grupo9dh.projetointegrador.data.di

import android.util.Log
import com.github.grupo9dh.projetointegrador.data.api.TmdbApi
import com.github.grupo9dh.projetointegrador.data.repositories.MovieRepository
import com.github.grupo9dh.projetointegrador.data.repositories.MovieRepositoryInterface
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun load() {
        loadKoinModules(networkModules() + repositoriesModule())
    }

    private fun networkModules(): Module {
        return module {
            single {
                val interceptor = HttpLoggingInterceptor {
                    Log.e("OkHttp", it)
                }.apply { level = HttpLoggingInterceptor.Level.BODY }

                OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .build()
            }

            single {
                GsonConverterFactory.create(GsonBuilder().create())
            }

            single { createService<TmdbApi>(get(), get()) }
        }
    }

    private fun repositoriesModule(): Module {
        return module {
            single<MovieRepositoryInterface> { MovieRepository(get()) }
        }
    }

    private inline fun <reified T> createService(
        client: OkHttpClient,
        factory: GsonConverterFactory
    ): T {
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .client(client)
            .addConverterFactory(factory)
            .build()
            .create(T::class.java)
    }
}