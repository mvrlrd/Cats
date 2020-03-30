package ru.mvrlrd.cats;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IApiService {
    @GET("search/")
    Observable <Cat[]> getCat(@Query("key") String key);
}
