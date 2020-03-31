package ru.mvrlrd.cats;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class MainPresenter extends MvpPresenter <MainView> {
    private static final String TAG = "MainPresenter";
    private ApiHelper apiHelper;
    private Cat[] cats;



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
                  Log.d(TAG, photos[0].url + "  new cats picture");
                },
                throwable -> {
                  Log.e(TAG, "onError " + throwable+"   "+cats.length);
                });
    }

    public Cat[] getCats() {
        return cats;
    }
}
