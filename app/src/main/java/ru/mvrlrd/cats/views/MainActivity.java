package ru.mvrlrd.cats.views;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import ru.mvrlrd.cats.presenter.MainPresenter;
import ru.mvrlrd.cats.R;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;


public class MainActivity extends MvpAppCompatActivity implements MainView {
    private static final String TAG = "MainActivity";

    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.imageView)
    ImageView imageView;
    @InjectPresenter
    MainPresenter mainPresenter;

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //leak of memory here
         new DownloadTask().start();
        //leak of memory here^^^^^^^
}

    //leak of memory here
private class DownloadTask extends Thread {
    public DownloadTask() {
      Log.e(TAG,MainActivity.this+"        new activity DownloadTask created");
    }
    //leak of memory here^^^^^^^^^^^

    @Override
    public void run() {
        SystemClock.sleep(2000*10);

    }
}




  @OnClick(R.id.imageButton)
  public void onClick() {
    mainPresenter.getAllPhoto();
      }

    @Override
    public void updateImageView(String url) {
        Picasso
                .get()
                .load(url)
                .fit()
                .into(imageButton);
    }
}
