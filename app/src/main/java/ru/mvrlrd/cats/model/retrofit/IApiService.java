package ru.mvrlrd.cats.model.retrofit;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.mvrlrd.cats.model.Cat;

public interface IApiService {
    @GET("search/")
    Observable <Cat[]> getCat(@Query("key") String key);
}
