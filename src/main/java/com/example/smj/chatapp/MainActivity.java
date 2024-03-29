package com.example.smj.chatapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Toolbar mToolbar;

    private ViewPager mViewPager;

    private SectionsPageAdapter mSectionsPageAdapter;

    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mToolbar=(Toolbar)findViewById(R.id.main_page_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Lapit Chat");

        //tabs

        mViewPager=(ViewPager)findViewById(R.id.main_tabPager);
        mSectionsPageAdapter=new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager.setAdapter(mSectionsPageAdapter);

        mTabLayout =(TabLayout)findViewById(R.id.main_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        }

 public void onStart(){
        super.onStart();
     FirebaseUser currentUser=mAuth.getCurrentUser();

     if(currentUser==null){
         sendToStart();
     }

 }

    private void sendToStart() {

        Intent startIntent=new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
         getMenuInflater().inflate(R.menu.menu_main,menu);



         return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if(item.getItemId()==R.id.main_logout_btn){
            FirebaseAuth.getInstance().signOut();
            sendToStart();

        }
        if(item.getItemId()==R.id.main_settings_btn){

            Intent settingsIntent=new Intent(MainActivity.this,SettingsActivity.class);
            startActivity(settingsIntent);

        }
        if (item.getItemId()==R.id.main_all_btn){
            Intent settingsIntent=new Intent(MainActivity.this,UsersActivity.class);
            startActivity(settingsIntent);


        }


        return true;
    }
}
