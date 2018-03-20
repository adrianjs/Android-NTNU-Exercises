package com.example.adrst.ex7;

import android.content.Intent;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updateBackground();
        try {
            db = new DatabaseManager(this);
            db.clean();
            long id = db.insert("Bud Kurniawan","Android Application Development: A Beginners Tutorioal");
            id = db.insert("Mildrid Ljosland", "Programmering i C++");
            id = db.insert("Else Lervik", "Programmering i C++");
            id = db.insert("Mildrid Ljosland", "Algoritmer og datastrukturer");
            id = db.insert("Helge Hafting", "Algoritmer og datastrukturer");

            // Code for reading json file and inserting into db
            InputStream bookStream = getResources().openRawResource(R.raw.books_as_json);
            Map books = new ObjectMapper().readValue(bookStream, Map.class);
            bookStream.close();
            List bookList = (List) books.get("books");
            Log.i(TAG, "onCreate: books gotten " + bookList.size());
            for (Object o : bookList) {
                Map book = (Map) o;
                String author = (String) book.get("author");
                String title = (String) book.get("title");
                Log.i(TAG, "onCreate: loaded book " + author + ", " + title);
                db.insert(author, title);
            }

            //   ArrayList<String> res = db.getAllAuthors();
            ArrayList<String> res = db.getAllBooks();
            //   ArrayList<String> res = db.getBooksByAuthor("Mildrid Ljosland");
            //   ArrayList<String> res = db.getAuthorsByBook("Programmering i C++");
            //   ArrayList<String> res = db.getAllBooksAndAuthors();
            showResults(res);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            String tekst = e.getMessage();
            TextView t = (TextView)findViewById(R.id.tw1);
            t.setText(tekst);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        updateBackground();
    }

    private void updateBackground() {
        Log.i(TAG, "updateBackground: update background color");
        TextView t = findViewById(R.id.tw1);
        String backgroundColor = PreferenceManager.getDefaultSharedPreferences(this)
                .getString("backgroundColor", "#ffffff");
        Log.i(TAG, "updateBackground: // Update bgc to " + backgroundColor);
        t.setBackgroundColor(Color.parseColor(backgroundColor));
    }

    public void showResults(ArrayList<String> list) {
        StringBuffer res = new StringBuffer("");
        for (String s : list)  {
            res.append(s+"\n");
        }
        TextView t = findViewById(R.id.tw1);
        t.setText(res);
    }

    public void onClickShowAuthors(View view) {
        showResults(db.getAllAuthors());
    }

    public void onClickShowTitles(View view) {
        showResults(db.getAllBooks());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(".SettingsActivity");
            startActivityForResult(intent, 1);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
