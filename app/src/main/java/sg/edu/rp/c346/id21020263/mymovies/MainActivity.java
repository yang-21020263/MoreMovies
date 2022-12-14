package sg.edu.rp.c346.id21020263.mymovies;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText etTitle, etGenre, etYear;
    Button btnInsert, btnList;
    Spinner ratings;
    ArrayList<Movie> movieList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTitle = findViewById(R.id.editTextTitle);
        etGenre = findViewById(R.id.editTextGenre);
        etYear = findViewById(R.id.editTextYear);
        btnInsert = findViewById(R.id.buttonInsert);
        btnList = findViewById(R.id.buttonList);
        ratings = findViewById(R.id.spinnerRating);


        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = etTitle.getText().toString();
                String genre = etGenre.getText().toString();
                String year = etYear.getText().toString();
                String rating = ratings.getSelectedItem().toString();

                DBHelper dbh = new DBHelper(MainActivity.this);
                long inserted_id = dbh.insertAll(title, genre, year, rating);
                if (inserted_id != -1) {
                    Toast.makeText(MainActivity.this, "Insert Successful", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListActivity.class);
                startActivity(i);
            }
        });
    }
}