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
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;


public class MainActivity extends MvpAppCompatActivity implements MainView {
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
    }

  @OnClick(R.id.imageButton)
  public void onClick() {
    mainPresenter.getAllPhoto();
      }

    @Override
    public void updateImageView(String s) {
        Picasso
                .get()
                .load(s)
                .fit()
                .into(imageButton);
    }
}
