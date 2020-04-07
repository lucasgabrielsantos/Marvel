package br.com.digitalhouse.projetomarvel.network

import br.com.digitalhouse.projetomarvel.api.ComicsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("comics")
    fun getAllCharacters(
            @Query("format") format: String?,
            @Query("formatType") formatType: String?,
            @Query("noVariants") noVariants: Boolean,
            @Query("ts") ts: String?,
            @Query("hash") hash: String?,
            @Query("apikey") apikey: String?,
            @Query("offset") offset: Int? = 0): Observable<ComicsResponse?>?


}

