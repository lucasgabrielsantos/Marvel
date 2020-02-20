package br.com.digitalhouse.projetomarvel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse
import br.com.digitalhouse.projetomarvel.pojo.Result
import br.com.digitalhouse.projetomarvel.repository.ComicsRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ComicsViewModel(application: Application) : AndroidViewModel(application) {
    private val listaresult = MutableLiveData<List<Result>>()
    private val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    private val repository = ComicsRepository()
    val listaComics: LiveData<List<Result>> get() = listaresult

    fun loading(): MutableLiveData<Boolean> {
        return loading
    }

    val comics: Unit
        get() {
            disposable.add(
                    repository.comicsResponse()
                    !!.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSubscribe { loading.setValue(true) }
                            .doOnTerminate { loading.setValue(false) }
                            .flatMap { comicsResponse: ComicsResponse -> Observable.just(comicsResponse.data.results) }
                            .subscribe({ resultlist: List<Result> -> listaresult.setValue(resultlist) }
                            ) { throwable: Throwable -> Log.i("LOG", "erro" + throwable.message) }
            )
        }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        const val PUBLIC_KEY = "6eb7e8896ec5850c52515a8a23ee97f0"
        const val PRIVATE_KEY = "0dd0c16fedb8a02985977eafca66b49f5e6a526f"
    }
}