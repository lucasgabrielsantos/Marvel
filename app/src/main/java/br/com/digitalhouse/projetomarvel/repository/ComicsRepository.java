package br.com.digitalhouse.projetomarvel.repository;

import br.com.digitalhouse.projetomarvel.network.RetrofitService;
import br.com.digitalhouse.projetomarvel.pojo.ComicsResponse;
import br.com.digitalhouse.projetomarvel.pojo.Result;
import io.reactivex.Observable;

import static br.com.digitalhouse.projetomarvel.network.RetrofitService.getApiService;
import static br.com.digitalhouse.projetomarvel.viewmodel.ComicsViewModel.PUBLIC_KEY;

public class ComicsRepository {

    public Observable<ComicsResponse> comicsResponse() {
        return getApiService().ComicsResponse("magazine", "comic", true, RetrofitService.getTimeStamp(), RetrofitService.getHash(), PUBLIC_KEY);
    }
}
