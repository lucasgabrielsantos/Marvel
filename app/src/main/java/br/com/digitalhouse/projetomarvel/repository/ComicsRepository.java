package br.com.digitalhouse.projetomarvel.repository;

import br.com.digitalhouse.projetomarvel.network.RetrofitService;
import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse;
import io.reactivex.Observable;

import static br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel.PUBLIC_KEY;

public class ComicsRepository {

    public Observable<ComicsResponse> comicsResponse() {
        return RetrofitService.Companion.getApiService().ComicsResponse("magazine", "comic", true, RetrofitService.Companion.getTimeStamp(), RetrofitService.Companion.getHash(), PUBLIC_KEY);
    }
}
