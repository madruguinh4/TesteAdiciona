package projetoteste.testeadiciona;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by alan0 on 02/10/2017.
 */

public interface CrudRest {



    @POST("Aula/webresources/aula.pessoas/inserir")
    Call<Void> inserePessoa(@Body Pessoas pessoas);

    @GET("Aula/webresources/aula.pessoas")
    Call<List<Pessoas>> getLivros();

    @GET("Aula/webresources/aula.pessoas/buscar/{id}")
    Call<Pessoas> getPessoaPorId(@Path("id") String id);

    @PUT("Aula/webresources/aula.pessoas/editar/{id}")
    Call<Void> alterarPessoa(@Path("id") String id, @Body Pessoas pessoas);

    @DELETE("Aula/webresources/aula.pessoas/remover/{id}")
    Call<Void> removePessoa(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
