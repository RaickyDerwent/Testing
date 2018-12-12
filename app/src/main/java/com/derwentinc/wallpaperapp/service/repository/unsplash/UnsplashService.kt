package com.derwentinc.wallpaperapp.service.repository.unsplash

import android.content.Context
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.model.unsplash.UnsplashPhotoResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors

class UnsplashService {

    companion object {
        private lateinit var unsplashRestClient: UnsplashRestClient
        private var callback: ResultCallback? = null

        fun registerCallback(resultCallback: ResultCallback) {
            callback = resultCallback
        }

        fun create(context: Context) {
            unsplashRestClient = UnsplashRestClient.create(context)
        }

        fun getPhotos(searchTerm: String, pageNumber: Int = 1) {
            lateinit var disposable: Disposable
            unsplashRestClient.getPhotos(searchTerm = searchTerm, pageNumber = pageNumber)
                .subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.computation())
                .map { it -> it.results }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<List<UnsplashPhotoResult>> {
                    override fun onComplete() {
                        disposable.dispose()
                    }

                    override fun onSubscribe(d: Disposable) {
                        disposable = d
                    }

                    override fun onNext(t: List<UnsplashPhotoResult>) {
                        callback?.onResult(t.stream()
                            .map { it ->
                                Photo(
                                    it.description,
                                    it.urls.regular
                                )
                            }
                            .collect(Collectors.toList()))
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        }
    }
}

interface ResultCallback {
    fun onResult(photoList: List<Photo>)
}