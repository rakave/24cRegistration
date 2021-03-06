package com.example.iyashwant.spiderprojectprototype;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.iyashwant.spiderprojectprototype.auditions.AuditionsTab;
import com.example.iyashwant.spiderprojectprototype.encounters.SwipeFragment;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    AppBarLayout appBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

      /*  SharedPreferences sharedPref = this.getPreferences(MODE_PRIVATE);
        String jwttoken = sharedPref.getString(getString(R.string.jwtTokenKey), null);
        if(jwttoken==null){
            Intent intent = new Intent(this, Login2.class);
            startActivity(intent);
        }

        */

        //to alter the display size on scroll
        appBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout_craft);
        final FrameLayout contentLayout = (FrameLayout) findViewById(R.id.content_frame_crafts);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                ViewGroup.MarginLayoutParams layoutParams = (ViewGroup.MarginLayoutParams) contentLayout.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, appBarLayout.getMeasuredHeight() + verticalOffset);
                contentLayout.requestLayout();
            }

        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState==null){
            navigationView.getMenu().performIdentifierAction(R.id.inbox,0);
        }

        ImageView purchase_coins = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.purchase_coins);
        purchase_coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Main2Activity.this, PurchaseCoins.class);
                startActivity(i);
            }
        });

        RelativeLayout nav_header_crafts = (RelativeLayout) navigationView.getHeaderView(0).findViewById(R.id.nav_header_crafts);
        nav_header_crafts.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(Main2Activity.this, ProfileView.class)
                        .putExtra("thisistogetback", "do nothing")
                        .putExtra("fromwhom", "do nothing");
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            new AlertDialog.Builder(Main2Activity.this)
                    .setMessage("Do you really want to exit?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.finishAffinity(Main2Activity.this);
                            finish();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        }


    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

       if (id == R.id.inbox) {
           Fragment fragment = new InboxTab();
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, fragment).commit();
           appBarLayout.setTargetElevation(0);

       } else if (id == R.id.auditions) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new AuditionsTab()).commit();
           appBarLayout.setTargetElevation(0);

        } else if (id == R.id.newsfeed) {
            fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new NewsfeedFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.nearby) {
           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new PeopleNearbyFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.encounters) {
           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new SwipeFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

       } else if (id == R.id.directory) {
           fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new DirectoryFragment()).commit();
           if (android.os.Build.VERSION.SDK_INT >= 21) appBarLayout.setElevation(8);

        } else if (id == R.id.promote) {
           Intent i = new Intent(getApplicationContext(),PromoteProfilePopUp.class);
           startActivity(i);
           //fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new SecondFragment()).commit();

        } else if (id == R.id.subscribe) {
           Intent i = new Intent(getApplicationContext(),SubscribePopUp.class);
           startActivity(i);
           // fragmentManager.beginTransaction().replace(R.id.content_frame_crafts, new SecondFragment()).commit();

        } else if (id == R.id.settings) {
           Intent i = new Intent(this,Settings.class).putExtra("type","crafts");
           startActivity(i);

       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
