package br.com.heiderlopes.metrospapp.api;

import java.util.List;

import br.com.heiderlopes.metrospapp.model.Estacao;
import br.com.heiderlopes.metrospapp.model.Linha;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface MetroAPI {

    @GET("/linhas")
    Observable<List<Linha>> getLinhas();

    @GET("/linhas/{linha}/estacoes")
    Observable<List<Estacao>> getEstacoes(@Path("linha") String linha);

}
