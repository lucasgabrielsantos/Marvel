package br.com.digitalhouse.projetomarvel.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.digitalhouse.projetomarvel.api.ComicsResponse
import br.com.digitalhouse.projetomarvel.api.Result
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
    var listaComics: LiveData<List<Result>> = listaresult

    fun loading(): MutableLiveData<Boolean> {
        return loading
    }

    fun getComicsViewModel() {
        disposable.add(
                repository.comicsResponse()
                !!.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { loading.setValue(true) }
                        .doOnTerminate { loading.setValue(false) }
                        .flatMap { comicsResponse: ComicsResponse ->
                            Observable.just(comicsResponse.data.results)
                        }
                        .subscribe(listaresult::setValue
                        ) { throwable: Throwable ->
                            Log.i("LOG", "erro" + throwable.message)
                        }
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}