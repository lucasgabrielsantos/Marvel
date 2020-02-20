package br.com.digitalhouse.projetomarvel.repository

import br.com.digitalhouse.projetomarvel.network.RetrofitService.Companion.getApiService
import br.com.digitalhouse.projetomarvel.network.RetrofitService.Companion.getHash
import br.com.digitalhouse.projetomarvel.network.RetrofitService.Companion.getTimeStamp
import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse
import br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel.Companion.PUBLIC_KEY
import io.reactivex.Observable

class ComicsRepository {
    fun comicsResponse(): Observable<ComicsResponse?>? {
        return getApiService().ComicsResponse("magazine", "comic", true, getTimeStamp(), getHash(), PUBLIC_KEY)
    }
}