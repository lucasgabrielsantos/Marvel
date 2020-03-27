package br.com.digitalhouse.projetomarvel.network

import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelAPI {
    @GET("comics")
    fun ComicsResponse(
            @Query("format") format: String?,
            @Query("formatType") formatType: String?,
            @Query("noVariants") noVariants: Boolean,
            @Query("ts") ts: String?,
            @Query("hash") hash: String?,
            @Query("offset") offset: String?,
            @Query("apikey") apikey: String?): Observable<ComicsResponse?>?

}

