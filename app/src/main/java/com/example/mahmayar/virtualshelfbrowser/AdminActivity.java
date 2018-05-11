package com.example.mahmayar.virtualshelfbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminActivity extends AppCompatActivity {
    private BooksFragment fragment;
    private DbConnection dbConnection;
    private AdminModel adminModel;
    private DbQuery dbQuery;
    private String searchKey = "";
    boolean user;
    Intent intent ;
    static public int LOGIN_RETURN_CODE = 1;




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater ().inflate ( R.menu.drop_down_menu, menu );
        if(user) {
            menu.findItem(R.id.remove_books).setVisible ( false );
            menu.findItem(R.id.edit_books).setVisible ( false );
            menu.findItem(R.id.add_books).setVisible ( false );
        }
        return true;
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        String searchKey = data.getStringExtra ("searchKey");
        String searchValue = data.getStringExtra ("searchValue");
            switch(requestCode)
            {
                // search
                case 1:
                    ArrayList<Book> bookList = dbQuery.search(searchKey, searchValue);
                    for(Book b : bookList) {
                        System.out.println(b.getISBN());
                    }
                    fragment.getBooksGridUpdater().update(bookList);
                    break;

                //add
                case 2:
                    System.out.println("in add books");
                    new FetchBooks(adminModel, fragment.getBooksGridUpdater()).execute(searchValue);
                    break;

                //edit
                case 3:
                    Map<String, String> attributes = new HashMap<String, String>();
                    String isbnValue = data.getStringExtra ("isbn");
                    String priceValue = data.getStringExtra ("price");
                    attributes.put( "isbn", isbnValue);
                    attributes.put( "price", priceValue);
                    adminModel.editBook(attributes);
                    fragment.getBooksGridUpdater().update(dbQuery.fetchAllBooks());
                    break;
                //delete
                case 4:
                    String isbnVal = data.getStringExtra ("isbn");
                    adminModel.deleteBook(isbnVal);
                    fragment.getBooksGridUpdater().update(dbQuery.fetchAllBooks());
                    break;
            }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId ();
        Intent intent;
        switch (id) {

            case R.id.search_item :
               intent = new Intent ( AdminActivity.this,  PopUp.class );
                intent.putExtra("key", "search");
                startActivityForResult ( intent,1);
                break;
            case R.id.customize_item :
                intent = new Intent ( AdminActivity.this,  PopUp.class );
                intent.putExtra("key", "customize");
                startActivityForResult ( intent,1);
                break;

            case R.id.add_books :
                intent = new Intent ( AdminActivity.this,  PopUp.class );
                intent.putExtra("key", "add");
                startActivityForResult ( intent,2);
                break;
            case R.id.remove_books :
                intent = new Intent ( AdminActivity.this,  PopUp.class );
                intent.putExtra("key", "delete");
                startActivityForResult ( intent,4);
                break;
            case R.id.edit_books :
                intent = new Intent ( AdminActivity.this,  PopUp.class );
                intent.putExtra("key", "edit");
                startActivityForResult ( intent,3);
                break;

        }

        return true;
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

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

        dbConnection = DbConnection.getInstace(AdminActivity.this);
        adminModel = new AdminModel(dbConnection);
        dbQuery = new DbQuery(dbConnection);
        intent = getIntent();
        fragment = new BooksFragment();

        user = intent.getBooleanExtra("user", false);
        getFragmentManager().beginTransaction().add(R.id.bookFragment, fragment, "tag").commit();
    }
}