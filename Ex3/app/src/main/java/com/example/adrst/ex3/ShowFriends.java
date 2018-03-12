package com.example.adrst.ex3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ShowFriends extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_friends);
    }

    public void onClickNewFriend(View view) {
        EditText editName = findViewById(R.id.edit_newName);
        EditText editBirthday = findViewById(R.id.edit_newBirthday);
        String name = editName.getText().toString();
        String birthday = editBirthday.getText().toString();

        if (name.equals("") || birthday.equals("")) {
            Toast.makeText(this, "Please fill in both fields to create a new friend", Toast.LENGTH_LONG).show();
        } else {
            Intent intent = new Intent();
            intent.putExtra("newName", name);
            intent.putExtra("newBirthday", birthday);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void onClickCancel(View view) {
        setResult(RESULT_CANCELED);
        finish();
    }
}
