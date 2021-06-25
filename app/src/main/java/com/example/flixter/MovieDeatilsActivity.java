package com.example.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flixter.models.Movie;

import org.parceler.Parcels;

public class MovieDeatilsActivity extends AppCompatActivity {
    Movie movie;

    TextView tvTitle;
    TextView tvOverview;
    RatingBar rbVoteAverage;
    String posterPath;
    ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_deatils);

        tvTitle = findViewById(R.id.tvTitle);
        tvOverview = findViewById(R.id.tvOverview);
        rbVoteAverage = findViewById(R.id.rbVoteAverage);
        ivPoster = findViewById(R.id.ivPoster);
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        if(this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
           posterPath = movie.getBackdropPath();
        } else {
            posterPath = movie.getPosterPath();
        }

        Log.d("MovieDetailsActivity", String.format("Showing details for %s", movie.getTitle()));

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        float voteAverage = movie.getVoteAverage().floatValue();
        rbVoteAverage.setRating(voteAverage/2.0f);
        Log.d("Posterpath: ", posterPath);
        Glide.with(this).load(posterPath).into(ivPoster);
    }
}