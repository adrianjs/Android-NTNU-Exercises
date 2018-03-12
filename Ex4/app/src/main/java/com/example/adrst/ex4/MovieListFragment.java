package com.example.adrst.ex4;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MovieListFragment extends ListFragment {
    private String[] movieTitles, movieDescriptions;
    private OnFragmentMovieInteractionListener movieListener;

    public MovieListFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Resources resources = getResources();
        movieTitles = resources.getStringArray(R.array.movie_titles);
        movieDescriptions = resources.getStringArray(R.array.movie_descriptions);
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, movieTitles));
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            movieListener = (OnFragmentMovieInteractionListener) activity;
        } catch (ClassCastException cce) {
            throw new ClassCastException(activity.toString() + " must implement the listener interface.");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        movieListener.onFragmentMovieInteractionListener(position, movieDescriptions[position]);
    }

    public interface OnFragmentMovieInteractionListener {
        void onFragmentMovieInteractionListener(int resourceId, String movieDescription);
    }
}
