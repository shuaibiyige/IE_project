package com.example.ie_project;

import android.app.FragmentManager;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity// implements NavigationView.OnNavigationItemSelectedListener
{
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, new Dashboard()).commit();


        //ActionBar actionBar = getActionBar();
        //actionBar.setTitle("Leto's Aid");
        //getSupportActionBar();

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        toolbar.setTitle("Leto's Aid");

//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        NavigationView navigationView = findViewById(R.id.nav_view);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView.setNavigationItemSelectedListener(this);
//        Intent intent = getIntent();
//        boolean goToSchedule = intent.getBooleanExtra("schedule", false);
//        if (goToSchedule)
//        {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main, new Schedule()).commit();
//        }
//        else {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main, new Dashboard()).commit();
//        }
    }


//    @Override
//    public boolean onNavigationItemSelected(MenuItem item)
//    {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        Fragment nextFragment = null;
//
//        if (id == R.id.nav_home)
//        {
//            nextFragment = new Dashboard();
//        }
//        else if (id == R.id.nav_schedule)
//        {
//            nextFragment = new Schedule();
//        }
//
//        if (nextFragment != null)
//        {
//            FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_main, nextFragment).commit();
//        }
//
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
