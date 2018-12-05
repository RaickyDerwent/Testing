package com.derwentinc.wallpaperapp.service.repository.unsplash

import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.model.unsplash.UnsplashPhotoResult
import com.derwentinc.wallpaperapp.view.callback.ResultCallback
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors

class UnsplashService {

    companion object {
        var resultCallback: ResultCallback? = null

        private val unsplashRestClient by lazy {
            UnsplashRestClient.create()
        }

        fun registerCallback(callback: ResultCallback) {
            resultCallback = callback
        }

        fun unregisterCallback(callback: ResultCallback) {
            if (resultCallback === callback) {
                resultCallback = null
            }
        }

        fun getPhotos(searchTerm: String) {
            lateinit var disposable: Disposable
            unsplashRestClient.getPhotos(searchTerm = searchTerm)
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
                        val collect = t.stream().map { it ->
                            Photo(
                                it.description,
                                it.urls.regular
                            )
                        }
                            .collect(Collectors.toList())
                        resultCallback?.onResult(collect)
                    }

                    override fun onError(e: Throwable) {
                    }

                })
        }
    }
}