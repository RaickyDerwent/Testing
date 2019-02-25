package com.derwentinc.wallpaperapp.service.repository.unsplash

import android.content.Context
import com.derwentinc.wallpaperapp.service.model.unsplash.UnsplashPhotos
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface UnsplashRestClient {

    companion object {
        fun create(context: Context): UnsplashRestClient {
            val cache = Cache(context.cacheDir, 10 * 1024 * 1024)
            val okHttpClient = OkHttpClient.Builder()
                .cache(cache)
                //.addNetworkInterceptor(getCacheInterceptor())
                .build()

            val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.unsplash.com/")
                .client(okHttpClient)
                .build()
            return builder.create(UnsplashRestClient::class.java)
        }
    }

    @GET("/search/photos/")
    fun getPhotos(
        @Header("Authorization") authHeader: String = "Client-ID 48d2f500c13f99e57baefe017552e1b41f9b548efd225b4e5e58759465961e63",
        @Query("query") searchTerm: String,
        @Query("page") pageNumber: Int = 1
    ): Observable<UnsplashPhotos>
}