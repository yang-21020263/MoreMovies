package sg.edu.rp.c346.id21020263.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import java.sql.Array;
import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    Button btnPG13;
    ListView lv;
    ArrayList<Movie> al;
    CustomAdapter ca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.lv);
        btnPG13 = findViewById(R.id.buttonPG13);

        DBHelper dbh = new DBHelper(ListActivity.this);
        al = new ArrayList<Movie>();
        al.addAll(dbh.getAllMovies());

        ca = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(ca);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long identity) {
                Movie movie = al.get(position);
                Intent i = new Intent(ListActivity.this, EditActivity.class);
                i.putExtra("movie", movie);
                startActivity(i);
            }
        });
    }
}