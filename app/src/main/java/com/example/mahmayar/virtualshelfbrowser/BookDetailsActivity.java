package com.example.mahmayar.virtualshelfbrowser;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class BookDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.book_details_activity, new BookDetailsFragment(), "tag").commit();
        }
    }

}
