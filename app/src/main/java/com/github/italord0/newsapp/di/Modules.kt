package com.github.italord0.newsapp.di

import com.github.italord0.newsapp.BuildConfig
import com.github.italord0.newsapp.data.NewsApiService
import com.github.italord0.newsapp.repository.ArticleRepository
import com.github.italord0.newsapp.repository.ArticleRepositoryImpl
import com.github.italord0.newsapp.ui.home.HomeViewModel
import com.github.italord0.newsapp.ui.home.HomeViewModelImpl
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCase
import com.github.italord0.newsapp.usecase.GetAllArticlesUseCaseImpl
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCase
import com.github.italord0.newsapp.usecase.GetTopHeadlinesUseCaseImpl
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val viewModelModule = module {
    viewModelOf(::HomeViewModelImpl) { bind<HomeViewModel>() }
}

val repositoryModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
}

val useCaseModule = module {
    single<GetAllArticlesUseCase> { GetAllArticlesUseCaseImpl(get()) }
    single<GetTopHeadlinesUseCase> { GetTopHeadlinesUseCaseImpl(get()) }
}

val networkModule = module {
    single {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(logging).build()

        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

        Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    single<NewsApiService> { get<Retrofit>().create(NewsApiService::class.java) }
}