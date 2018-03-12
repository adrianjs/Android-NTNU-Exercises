package com.example.adrst.ex4;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MovieListFragment.OnFragmentMovieInteractionListener {
    @StyleableRes private int selectedImage = 0;
    private TypedArray movieCovers;
    private String[] movieTitles, movieDescriptions;
    private ImageFragment imageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources resources = getResources();
        movieCovers = resources.obtainTypedArray(R.array.movie_covers);
        movieTitles = resources.getStringArray(R.array.movie_titles);
        movieDescriptions = resources.getStringArray(R.array.movie_descriptions);
        imageFragment = (ImageFragment) getFragmentManager().findFragmentById(R.id.image_fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    public void previousImage() {
        if (selectedImage <= 0) {
            Toast.makeText(this, "This is the first movie in this list", Toast.LENGTH_SHORT).show();
        } else {
            selectedImage--;
            TextView movieTitle = findViewById(R.id.movieTitle);
            movieTitle.setText(movieTitles[selectedImage]);
            onFragmentMovieInteractionListener(selectedImage, movieDescriptions[selectedImage]);
        }
    }

    public void nextImage() {
        if (selectedImage >= movieTitles.length - 1) {
            Toast.makeText(this, "This is the last movie in the list", Toast.LENGTH_SHORT).show();
        } else {
            selectedImage++;
            TextView movieTitle = findViewById(R.id.movieTitle);
            movieTitle.setText(movieTitles[selectedImage]);
            onFragmentMovieInteractionListener(selectedImage, movieDescriptions[selectedImage]);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id) {
            case R.id.menu_prev:
                previousImage();
                return true;
            case R.id.menu_next:
                nextImage();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentMovieInteractionListener(int resourceId, String movieDescription) {
        selectedImage = resourceId;
        int movieID = movieCovers.peekValue(resourceId).resourceId;
        imageFragment.changeImage(movieID, movieDescription);
    }
}
