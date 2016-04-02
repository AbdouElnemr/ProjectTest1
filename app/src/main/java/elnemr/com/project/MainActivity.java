package elnemr.com.project;

import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import elnemr.com.project.Adapters.MovieAdapter;
import elnemr.com.project.Adapters.Pojo;

public class MainActivity extends AppCompatActivity {

    static GridView gridView;
     ImageView imageView;

    Pojo pojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        gridView = (GridView) findViewById(R.id.gridview);
        imageView = (ImageView) findViewById(R.id.imageView);

     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {

           new JSONClass().execute("http://api.themoviedb.org/3/movie/popular?api_key=013fdbe9d59157bfc099e169c0ab7a83");
            return true;
        }else if (id== R.id.top_rated)
        {
            new JSONClass().execute("http://api.themoviedb.org/3/movie/top_rated?api_key=013fdbe9d59157bfc099e169c0ab7a83");
        }

        return super.onOptionsItemSelected(item);
    }

    public class JSONClass extends AsyncTask<String, Void, List<Pojo>> {
        List<Pojo> pojoList;

        private static final String TAG_ARRAY = "results";
        private static final String TAG_POSTER_PATH = "poster_path";
        private static final String TAG_OVERVIEW = "overview";
        private static final String TAG_ORIGINAL_TITLE = "original_title";
        private static final String TAG_VOTE_AVERAGE = "vote_average";
        private static final String TAG_RELAEASE_DATE = "release_date";

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected List<Pojo> doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader bufferedReader = null;
            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream inputStream = connection.getInputStream();
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                StringBuffer buffer = new StringBuffer();

                String Line = "";
                while ((Line = bufferedReader.readLine()) != null) {
                    buffer.append(Line);
                }
                String finalJSOn = buffer.toString();

                JSONObject parentObject = new JSONObject(finalJSOn);
                JSONArray parentArray = parentObject.getJSONArray(TAG_ARRAY);

                pojoList = new ArrayList<>();
                Hashtable<Integer,List<Pojo>> hashtable = new Hashtable<>();
                for (int i = 0; i < parentArray.length(); i++) {
                    JSONObject finalObject = parentArray.getJSONObject(i);

                    pojo = new Pojo();


                    pojo.setImageurl(finalObject.getString(TAG_POSTER_PATH));
                    pojo.setOverview(finalObject.getString(TAG_OVERVIEW));
                    pojo.setTitle(finalObject.getString(TAG_ORIGINAL_TITLE));
                    pojo.setVote_average(finalObject.getString(TAG_VOTE_AVERAGE));
                    pojo.setYear(finalObject.getString(TAG_RELAEASE_DATE));

                    pojoList.add(pojo);
                    hashtable.put(i, pojoList);
                 }
                return pojoList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {

                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Pojo> aVoid) {
            MovieAdapter movieAdapter = new MovieAdapter(getApplicationContext(), aVoid);
            gridView.setAdapter(movieAdapter);
            movieAdapter.notifyDataSetChanged();

                  /*  Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                    intent.putExtra("image","http://image.tmdb.org/t/p/w500/"+pojo.getImageurl());
                    intent.putExtra("title",pojo.getTitle());
                    startActivity(intent);*/


        }

    }

}
