package ru.mvrlrd.cats;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {

    public Observable<Cat[]> requestServer() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        //http://pixabay.com/api/?key=9250926-552b631cddef606bad3e807d2
        IApiService api = new Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/images/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);

        return api.getCat("deea7252-b15f-4f3f-b383-d3368c1d50b3").subscribeOn(Schedulers.io());
    }
}