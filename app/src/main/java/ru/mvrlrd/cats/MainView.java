package ru.mvrlrd.cats;

import android.view.View;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MainView extends MvpView {

    @StateStrategyType(value = SkipStrategy.class)
    void updateImageView(String s);
}