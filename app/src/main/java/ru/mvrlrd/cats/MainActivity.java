package ru.mvrlrd.cats;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements MainView{
    @BindView(R.id.imageButton)
    ImageButton imageButton;
    @BindView(R.id.imageView)
    ImageView imageView;

    MainPresenter mainPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mainPresenter = new MainPresenter();

    }

  @OnClick(R.id.imageButton)
  public void onClick() {
    mainPresenter.getAllPhoto();
//    if (mainPresenter.getCats() != null) {
//      updateImageView(mainPresenter.getCats()[0].url);
//    }
      }

    @Override
    public void updateImageView(String s) {
        Picasso
                .get()
                .load(s)
                .into(imageButton);
    }
}
