package com.valter.openweather.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import com.valter.openweather.BuildConfig
import com.valter.openweather.data.database.OpenWeatherDatabase
import com.valter.openweather.data.repository.OpenWeatherRepository
import com.valter.openweather.data.repository.OpenWeatherRepositoryImpl
import com.valter.openweather.dispatchers.AppDispatchersContainer
import com.valter.openweather.dispatchers.DispatchersContainer
import com.valter.openweather.network.*
import com.valter.openweather.ui.main.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataModule {
    val module = module {
        single { provideMoshi() }
        single { provideLoggingInterceptor() }
        single<RequestInterceptor> { provideRequestInterceptor() }
        single<ConnectivityInterceptor> { provideConnectivityInterceptor(get()) }
        single { provideDefaultOkHttpClient(get(), get(), get()) }
        single { provideRetrofit(get(), get()) }
        single { provideDatabase(androidApplication()) }
        single { provideCurrentWeatherDao(get()) }
        single { provideForecastWeatherDao(get()) }
        single { provideApiService(get()) }
        single<DispatchersContainer> { AppDispatchersContainer() }
        single<OpenWeatherRepository> { OpenWeatherRepositoryImpl(get(), get(), get(), get()) }
        viewModel { MainViewModel(get(), get()) }
    }
}

fun provideMoshi(): Moshi = Moshi.Builder().build()

fun provideLoggingInterceptor() = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

fun provideConnectivityInterceptor(context: Context) = ConnectivityInterceptorImpl(context)

fun provideRequestInterceptor() = RequestInterceptorImpl()

fun provideDefaultOkHttpClient(loggingInterceptor: HttpLoggingInterceptor,
                               requestInterceptor: RequestInterceptor,
                               connectivityInterceptor: ConnectivityInterceptor
) = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(requestInterceptor)
        .addInterceptor(connectivityInterceptor)
        .build()

fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

fun provideDatabase(context: Context) = Room.databaseBuilder(
        context,
        OpenWeatherDatabase::class.java,
        "open_weather.db"
).fallbackToDestructiveMigration()
        .build()

fun provideCurrentWeatherDao(database: OpenWeatherDatabase) = database.currentWeatherDao()

fun provideForecastWeatherDao(database: OpenWeatherDatabase) = database.forecastWeatherDao()

fun provideApiService(retrofit: Retrofit): OpenWeatherService = retrofit.create(OpenWeatherService::class.java)