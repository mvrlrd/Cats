package ru.mvrlrd.cats.presenter;

import android.util.Log;
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

    public MainPresenter() {
        Log.d(TAG, "MainPresenter: ");
        apiHelper = new ApiHelper();
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
                  getViewState().updateImageView(photos[0].url);

                  Log.d(TAG, "  new cats picture" + "   " + photos[0].url);
                },
                throwable -> {
                  Log.e(TAG, "onError " + throwable + "   ");
                });
    }
}
