package ru.mvrlrd.cats.model.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.mvrlrd.cats.model.Cat;

public class ApiHelper {
    private static final String KEY = "deea7252-b15f-4f3f-b383-d3368c1d50b3";
    private static final String BASIC_URL = "https://api.thecatapi.com/v1/images/";

    public Observable<Cat[]> requestServer() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        IApiService api = new Retrofit.Builder()
                .baseUrl(BASIC_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(IApiService.class);

        return api.getCat(KEY).subscribeOn(Schedulers.io());
    }
      //получаем json как массив с 1 элементом (Cat)
        // при каждом переходе по ссылке выдается рандомный элемент
        // (т.е.ссылка одна - элементы разные)
        //????????каким образом я могу получить в Презентер сразу несколько элементов за один раз????????

//[
// {
// "breeds":[],
// "id":"lP3_R8rUt",
// "url":"https://cdn2.thecatapi.com/images/lP3_R8rUt.jpg",
// "width":810,
// "height":1080
// }
// ]

}