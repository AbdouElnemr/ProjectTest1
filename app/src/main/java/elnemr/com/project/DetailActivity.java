package elnemr.com.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import elnemr.com.project.Adapters.Pojo;

public class DetailActivity extends AppCompatActivity {
    ImageView detailImage;
    TextView year;
    TextView  vote_average;
    TextView  overview;
    TextView  title;
    Pojo pojo;

    public static Intent getActivityIntent(Context context, Pojo movieObj){
        return new Intent(context, DetailActivity.class).putExtra("Object Key", movieObj).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setHomeButtonEnabled(true);
        pojo = (Pojo) getIntent().getSerializableExtra("Object Key");
        detailImage = (ImageView) findViewById(R.id.detail_movie_image);
        year = (TextView) findViewById(R.id.deatil_movie_year);
        vote_average = (TextView) findViewById(R.id.detail_movie_vote_Average);
        overview = (TextView) findViewById(R.id.detail_movie_overview);
        title = (TextView) findViewById(R.id.detail_movie_title);
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500/" + pojo.getImageurl())
                .into(detailImage);
        title.setText(pojo.getTitle());
        year.setText(pojo.getYear());
        vote_average.setText(pojo.getVote_average());
        overview.setText(pojo.getOverview());



    }

}
