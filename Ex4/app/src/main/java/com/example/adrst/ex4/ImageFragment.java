package com.example.adrst.ex4;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageFragment extends Fragment {
    private TextView movieText;
    private ImageView movieCover;

    public ImageFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        movieText = view.findViewById(R.id.fragment_image_description);
        movieCover = view.findViewById(R.id.fragment_image_view);
        return view;
    }

    public void changeImage(int resourceId, String movieDescription) {
        movieCover.setImageResource(resourceId);
        movieText.setText(movieDescription);
    }
}