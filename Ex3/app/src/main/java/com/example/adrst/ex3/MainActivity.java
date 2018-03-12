package com.example.adrst.ex3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrst.ex3.model.Friend;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Friend> friends = new ArrayList<Friend>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        friends.add(new Friend("Adrian Steffenakk", "10. september 1993"));
        friends.add(new Friend("Ann-Julie Nordgaard", "21. april 1993"));
        initializeSpinner();
    }

    void initializeSpinner() {
        ArrayAdapter<Friend> arrayAdapter = new ArrayAdapter<Friend>(this, android.R.layout.simple_spinner_item, friends);
        Spinner spinner = findViewById(R.id.spinner_friends);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView fetchedName = findViewById(R.id.view_fetchedName);
                fetchedName.setText(friends.get(position).getName());
                TextView fetchedBirthday = findViewById(R.id.view_fetchedBirthday);
                fetchedBirthday.setText(friends.get(position).getBirthday());

                EditText editName = findViewById(R.id.edit_name);
                editName.setText("");
                EditText editBirthday = findViewById(R.id.edit_birthday);
                editBirthday.setText("");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            String name = data.getStringExtra("newName");
            String birthday = data.getStringExtra("newBirthday");
            friends.add(new Friend(name, birthday));
        }
    }

    public void onClickNewFriend(View view) {
        Intent intent = new Intent("com.example.adrst.ex3.ShowFriends");
        startActivityForResult(intent, 1);
    }

    public void onClickChangeFriend(View view) {
        // make the grid visible
        GridLayout gridLayout = findViewById(R.id.grid_changeFriend);
        gridLayout.setVisibility(View.VISIBLE);
        Button changeButton = findViewById(R.id.btn_changeFriend);
        changeButton.setVisibility(View.INVISIBLE);
    }

    public void onClickSaveChanges(View view) {
        Spinner spinner = findViewById(R.id.spinner_friends);
        Friend selectedFriend = (Friend)spinner.getSelectedItem();
        EditText editName = findViewById(R.id.edit_name);
        EditText editBirthday = findViewById(R.id.edit_birthday);
        TextView viewName = findViewById(R.id.view_fetchedName);
        TextView viewBirthday = findViewById(R.id.view_fetchedBirthday);

        boolean somethingChanged = false;
        String newName = editName.getText().toString();
        if (!newName.equals("")) {
            selectedFriend.setName(newName);
            somethingChanged = true;
        }

        String newBirthday = editBirthday.getText().toString();
        if (!newBirthday.equals("")) {
            selectedFriend.setBirthday(newBirthday);
            somethingChanged = true;
        }

        GridLayout gridLayout = findViewById(R.id.grid_changeFriend);
        gridLayout.setVisibility(View.INVISIBLE);
        Button changeButton = findViewById(R.id.btn_changeFriend);
        changeButton.setVisibility(View.VISIBLE);

        String toastReply;
        if (somethingChanged) {
            viewName.setText(selectedFriend.getName());
            viewBirthday.setText(selectedFriend.getBirthday());
            toastReply = "Changes saved";
        } else {
            toastReply = "No changes detected";
        }

        Toast.makeText(this, toastReply, Toast.LENGTH_LONG).show();
    }

    public void onClickDiscard(View view) {
        // Change EditText to default
        EditText editName = findViewById(R.id.edit_name);
        EditText editBirthday = findViewById(R.id.edit_birthday);
        editName.setText("");
        editBirthday.setText("");

        // Make grid invisible
        GridLayout gridLayout = findViewById(R.id.grid_changeFriend);
        gridLayout.setVisibility(View.INVISIBLE);
    }
}
