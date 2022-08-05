package sg.edu.rp.c346.id21020263.mymovies;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

    public class DBHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "simplenotes.db";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_MOVIES = "movies";
        private static final String COLUMN_ID = "_id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_GENRE = "genre";
        private static final String COLUMN_YEAR = "year";
        private static final String COLUMN_RATING = "rating";


        public DBHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String createNoteTableSql = "CREATE TABLE " + TABLE_MOVIES + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT, " + COLUMN_GENRE + " TEXT,"
                    + COLUMN_YEAR + " TEXT, " + COLUMN_RATING + " TEXT )" ;
            db.execSQL(createNoteTableSql);
            Log.i("info", "created tables");
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTE);
            //db.execSQL("ALTER TABLE " + TABLE_NOTE + " ADD COLUMN  module_name TEXT ");
            // onCreate(db);

        }

        public long insertAll(String title, String genre, String year, String rating) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, title);
            values.put(COLUMN_GENRE, genre);
            values.put(COLUMN_YEAR, year);
            values.put(COLUMN_RATING, rating);
            long result = db.insert(TABLE_MOVIES, null, values);
            db.close();
            Log.d("SQL Insert","ID:"+ result); //id returned, shouldn’t be -1
            return result;
        }

        public ArrayList<Movie> getAllMovies() {
            ArrayList<Movie> movies = new ArrayList<Movie>();

            SQLiteDatabase db = this.getReadableDatabase();

            String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
            Cursor cursor = db.query(TABLE_MOVIES, columns, null, null,
                    null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String genre = cursor.getString(2);
                    String year = cursor.getString(3);
                    String rating = cursor.getString(4);
                    Movie movie = new Movie(id, title, genre, year, rating);
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return movies;
        }

        public int updateMovie(Movie movie){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_GENRE, movie.getGenre());
            values.put(COLUMN_YEAR, movie.getYear());
            values.put(COLUMN_RATING, movie.getRating());
            String condition = COLUMN_ID + "= ?";
            String[] args = {String.valueOf(movie.getId())};
            int result = db.update(TABLE_MOVIES, values, condition, args);
            db.close();
            return result;
        }


        public int deleteMovie(int id){
            SQLiteDatabase db = this.getWritableDatabase();
            String condition = COLUMN_ID + "= ?";
            String[] args = {String.valueOf(id)};
            int result = db.delete(TABLE_MOVIES, condition, args);
            db.close();
            return result;
        }


        public ArrayList<Movie> getAllMovies(String keyword) {
            ArrayList<Movie> movies = new ArrayList<Movie>();

            SQLiteDatabase db = this.getReadableDatabase();
            String[] columns= {COLUMN_ID, COLUMN_TITLE, COLUMN_GENRE, COLUMN_YEAR, COLUMN_RATING};
            String condition = COLUMN_RATING + " Like ?";
            String[] args = { "%" +  keyword + "%"};
            Cursor cursor = db.query(TABLE_MOVIES, columns, condition, args,
                    null, null, null, null);

            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(0);
                    String title = cursor.getString(1);
                    String genre = cursor.getString(2);
                    String year = cursor.getString(3);
                    String rating = cursor.getString(4);
                    Movie movie = new Movie(id, title, genre, year, rating);
                    movies.add(movie);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();
            return movies;
        }







    }
    
