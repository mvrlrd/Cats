package ru.mvrlrd.cats.presenter;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;
import ru.mvrlrd.cats.model.Cat;
import ru.mvrlrd.cats.model.retrofit.ApiHelper;
import ru.mvrlrd.cats.views.MainView;

@InjectViewState
public class MainPresenter extends MvpPresenter <MainView> {
    private static final String TAG = "MainPresenter";
    private ApiHelper apiHelper;
    private List <Cat[]> list;

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        apiHelper = new ApiHelper();
        list = new ArrayList<>();
    }

    @Override
    protected void onFirstViewAttach() {
        getAllPhoto();
    }

    public void getAllPhoto() {
        Observable<Cat[]> single = apiHelper.requestServer();
    Disposable disposable =
        single
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                photos -> {
                  list.add(photos);

                    getViewState()
                            .updateImageView(photos[0].url);

                    Log.d(TAG, list + "  new cats picture" + "   "+photos[0].url);
                },
                throwable -> {
                  Log.e(TAG, "onError " + throwable + "   ");
                });
    }
}
