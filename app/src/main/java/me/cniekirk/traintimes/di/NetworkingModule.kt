package me.cniekirk.traintimes.di

import android.content.Context
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.cniekirk.traintimes.data.remote.service.NationalRailService
import me.cniekirk.traintimes.data.repository.NationalRailRepositoryImpl
import me.cniekirk.traintimes.domain.repository.NationalRailRepository
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class NetworkingModule {

    @Provides
    fun provideCache(@ApplicationContext context: Context): Cache {
        return Cache(context.cacheDir, 10 * 1024 * 1024)
    }

    @Provides
    fun provideOkHttp(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
            .build()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://cniekirk.me/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideNationalRailService(retrofit: Retrofit): NationalRailService {
        return retrofit.create(NationalRailService::class.java)
    }

    @Provides
    fun provideNationalRailRepository(nationalRailRepositoryImpl: NationalRailRepositoryImpl): NationalRailRepository =
        nationalRailRepositoryImpl
}