package br.com.digitalhouse.projetomarvel.repository

import br.com.digitalhouse.projetomarvel.constants.constantsAPI.PUBLIC_KEY
import br.com.digitalhouse.projetomarvel.extensions.getTimeStamp
import br.com.digitalhouse.projetomarvel.network.RetrofitService.Companion.getApiService
import br.com.digitalhouse.projetomarvel.network.RetrofitService.Companion.getHash
import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse
import io.reactivex.Observable

class ComicsRepository {
    fun comicsResponse(): Observable<ComicsResponse?>? {
        return getApiService().getAllCharacters("magazine", "comic", true, getTimeStamp(), getHash(), PUBLIC_KEY, 0)
    }
}