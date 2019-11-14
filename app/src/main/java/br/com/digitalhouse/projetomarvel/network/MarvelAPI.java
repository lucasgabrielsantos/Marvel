package br.com.digitalhouse.projetomarvel.network;

import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MarvelAPI {

    @GET("comics")
    Observable<ComicsResponse> ComicsResponse(
            @Query("format") String format,
            @Query("formatType") String formatType,
            @Query("noVariants") boolean noVariants,
            @Query("ts") String ts,
            @Query("hash") String hash,
            @Query("apikey") String apikey);
}
