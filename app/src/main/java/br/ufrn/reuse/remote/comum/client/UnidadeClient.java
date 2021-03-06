package br.ufrn.reuse.remote.comum.client;

import br.ufrn.reuse.remote.DTO.UnidadeDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

/**
 * Created by Daniel on 11/19/2017.
 */

public interface UnidadeClient {

    @GET("/unidade/v0.1/unidades/{idUnidade}")
    Call<UnidadeDTO> findUnidadeById(@Header("Authorization") String authentication, @Header("x-api-key") String apiKey, @Path("idUnidade") long idUnidade);
}
