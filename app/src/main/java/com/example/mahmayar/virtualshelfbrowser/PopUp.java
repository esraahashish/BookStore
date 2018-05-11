package com.example.mahmayar.virtualshelfbrowser;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.ArrayList;

public class PopUp extends Activity{

    private String searchKey = "";
    static public int SYNC_RETURB_CODE = 2;
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.pop_up);
        DisplayMetrics dm = new DisplayMetrics ();
        getWindowManager ().getDefaultDisplay ().getMetrics ( dm );
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow ().setLayout ( (int) (width *.8), (int)(height*.6));

        Intent intent = getIntent();

        key = intent.getStringExtra ( "key");
        switch (key)
        {
            case "search":
                findViewById(R.id.isbn_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.title_radioButton).setVisibility(View.VISIBLE);
                break;

            case "customize":
                findViewById(R.id.release_date_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.category_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.price_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.author_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.library_radioButton).setVisibility(View.VISIBLE);
                break;

            case "add":
                findViewById(R.id.isbn_radioButton).setVisibility(View.VISIBLE);
                findViewById(R.id.all_book_radioButton).setVisibility(View.VISIBLE);
                break;

            case "delete":
                findViewById(R.id.isbnText).setVisibility(View.VISIBLE);
                findViewById(R.id.isbnView).setVisibility(View.VISIBLE);
                findViewById(R.id.searchField).setVisibility(View.INVISIBLE);
                break;

            case "edit":
                findViewById(R.id.isbnText).setVisibility(View.VISIBLE);
                findViewById(R.id.isbnView).setVisibility(View.VISIBLE);
                findViewById(R.id.priceText).setVisibility(View.VISIBLE);
                findViewById(R.id.priceView).setVisibility(View.VISIBLE);
                findViewById(R.id.searchField).setVisibility(View.INVISIBLE);
                break;

        }



        Button searchSubmit = (Button) findViewById(R.id.search);
        searchSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent ();
                if(key.equals ( "edit" )) {
                    EditText isbnText = (EditText) findViewById ( R.id.isbnText );
                    String isbnValue = isbnText.getText ().toString ();
                    EditText priceText = (EditText) findViewById ( R.id.priceText );
                    String priceValue = priceText.getText ().toString ();
                    intent.putExtra ( "isbn", isbnValue );
                    intent.putExtra ( "price", priceValue );
                }else if(key.equals ( "delete" )){
                    EditText isbnText = (EditText) findViewById ( R.id.isbnText );
                    String isbnValue = isbnText.getText ().toString ();
                    intent.putExtra ( "isbn", isbnValue );
                }else{
                    EditText searchText = (EditText) findViewById(R.id.searchField);
                    String searchValue = searchText.getText().toString();
                    intent.putExtra("searchKey",searchKey);
                    intent.putExtra ( "searchValue", searchValue);
                }
                setResult ( RESULT_OK,intent );
                finish ();
            }
        });
    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
          /*  case( R.id.all_book_radioButton && key.equals ( "add" )):
                findViewById(R.id.searchField).setVisibility(View.VISIBLE);
                break;*/
            case R.id.isbn_radioButton:
                if (checked)
                    searchKey = "isbn";
                findViewById(R.id.searchField).setVisibility(View.VISIBLE);
                break;
            case R.id.title_radioButton:
                if (checked)
                    searchKey = "title";
                break;
            case R.id.author_radioButton:
                if (checked)
                    searchKey = "author";
                break;
            case R.id.price_radioButton:
                if (checked)
                    searchKey = "price";
                break;
            case R.id.library_radioButton:
                if (checked)
                    searchKey = "library";
                break;
            case R.id.release_date_radioButton:
                if (checked)
                    searchKey = "release_date";
                break;
            case R.id.category_radioButton:
                if (checked)
                    searchKey = "category";
                break;
            case R.id.all_book_radioButton:
                if (checked)
                    searchKey = "allBook";
                    findViewById(R.id.searchField).setVisibility(View.INVISIBLE);
                break;

        }
    }



}
