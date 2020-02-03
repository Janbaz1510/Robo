package com.cyl18.moviebuff.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cyl18.moviebuff.R;
import com.cyl18.moviebuff.adapters.MovieAdapter;
import com.cyl18.moviebuff.modals.MovieModal;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String API_URL = "http://movieabcd.000webhostapp.com/api/select.php";

    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    ImageView imageView;
    private Toolbar toolbar;
    RecyclerView movieRecycler;
    List<MovieModal> listmovie;
    ShimmerFrameLayout shimmer;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation_view);
        imageView = findViewById(R.id.navigation_menu_icon);
        movieRecycler = findViewById(R.id.main_recycler);
        layout = findViewById(R.id.movielayout);
        shimmer = findViewById(R.id.shimmer_home);
        movieRecycler.setHasFixedSize(true);
        shimmer.startShimmer();


        getData();

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        invalidateOptionsMenu();
        imageView.setOnClickListener(v -> {
            if (drawer.isDrawerOpen(navigationView)) {
                drawer.closeDrawer(navigationView);
            } else {
                drawer.openDrawer(navigationView);
            }
        });


    }


    private void getData(){
        StringRequest request = new StringRequest(Request.Method.GET, MainActivity.API_URL, this::parseData,
                error -> Toast.makeText(MainActivity.this, "Network Error"+error.getMessage(), Toast.LENGTH_SHORT).show());

        Volley.newRequestQueue(MainActivity.this).add(request);

    }

    private void parseData(String response){
        listmovie = new ArrayList<>();
        MovieAdapter movieAdapter = new MovieAdapter(listmovie, MainActivity.this);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            movieRecycler.setLayoutManager(new GridLayoutManager(this,2));
        }else{
            movieRecycler.setLayoutManager(new GridLayoutManager(this,4));
        }

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("server_response");
            int count =0;
            while (count<jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);

                String movietitle = JO.getString("movietitle");
                String movieposter = JO.getString("movieposter");
                String moviereleaseddate = JO.getString("moviereleaseddate");
                String movierating = JO.getString("movierating");
                String moviereview = JO.getString("moviereview");
                String movieoverview = JO.getString("movieoverview");
                MovieModal moviemodal = new MovieModal(JO.getString("id"),movietitle,movieposter,moviereleaseddate,movierating,moviereview,movieoverview);
                listmovie.add(moviemodal);
                count++;
            }
            shimmer.stopShimmer();
            shimmer.setVisibility(View.GONE);
            movieRecycler.setVisibility(View.VISIBLE);
            movieRecycler.setAdapter(movieAdapter);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
//        actionBarDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        switch (id) {
//            case R.id.logout:
//                SessionManagement sm = new SessionManagement(this);
//                sm.logoutUser();
//                break;
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

}
