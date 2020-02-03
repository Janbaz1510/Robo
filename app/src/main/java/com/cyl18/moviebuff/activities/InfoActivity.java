package com.cyl18.moviebuff.activities;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.cyl18.moviebuff.R;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class InfoActivity extends AppCompatActivity {

    TextView movietitle, movierating, moviereleasedate, movieoverview, moviereview;
    ImageView movieposter, movietitleimage;
     CollapsingToolbarLayout collaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolbar.setNavigationOnClickListener(view -> finish());

        initCollapsingToolbar();

        movietitle = findViewById(R.id.movie_title);
        movierating = findViewById(R.id.movie_rating);
        moviereleasedate = findViewById(R.id.movie_released_date);
        movieoverview = findViewById(R.id.movie_overview);
        moviereview = findViewById(R.id.movie_review);
        movieposter = findViewById(R.id.movie_poster);
        movietitleimage = findViewById(R.id.toolbar_layout);
        collaps = findViewById(R.id.collaps);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        Intent intentThatStartedThisActivity = getIntent();
        if (intentThatStartedThisActivity.hasExtra("movietitle")){
            String mposter = Objects.requireNonNull(getIntent().getExtras()).getString("movieposter");
            String mtitle = getIntent().getExtras().getString("movietitle");
            String mrating = getIntent().getExtras().getString("movierating");
            String mreleaseddate = getIntent().getExtras().getString("moviereleaseddate");
            String moverview = getIntent().getExtras().getString("movieoverview");
            String mreview = getIntent().getExtras().getString("moviereview");
            String mtoollayout = getIntent().getExtras().getString("movieposter");

            Glide.with(this)
                    .load(mposter)
                    .into(movieposter);

            Glide.with(this)
                    .load(mtoollayout)
                    .into(movietitleimage);

            movietitle.setText(mtitle);
            movierating.setText(mrating);
            moviereleasedate.setText(mreleaseddate);
            movieoverview.setText(moverview);
            moviereview.setText(mreview);
        }else{
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    public  void initCollapsingToolbar(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collaps);
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + i == 0){
                    collapsingToolbarLayout.setTitle(getString(R.string.review));
                    isShow = true;
                }else  if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });
    }
}
