package com.derwentinc.wallpaperapp.service.repository.unsplash

import androidx.lifecycle.MutableLiveData
import com.derwentinc.wallpaperapp.model.Photo
import com.derwentinc.wallpaperapp.service.model.unsplash.UnsplashPhotoResult
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.stream.Collectors

class UnsplashService {

    companion object {

        private val unsplashRestClient by lazy {
            UnsplashRestClient.create()
        }

        fun getPhotos(searchTerm: String): MutableLiveData<MutableList<Photo>> {
            val photoList = MutableLiveData<MutableList<Photo>>()
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
                        photoList.value = t.stream()
                            .map { it ->
                                Photo(
                                    it.description,
                                    it.urls.regular
                                )
                            }
                            .collect(Collectors.toList())
                    }

                    override fun onError(e: Throwable) {
                    }

                })
            return photoList
        }
    }
}