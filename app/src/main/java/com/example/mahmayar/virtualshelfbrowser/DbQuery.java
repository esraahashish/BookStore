package com.example.mahmayar.virtualshelfbrowser;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;

public class DbQuery {

    
private SQLiteDatabase db;

    public DbQuery(DbConnection dbConnection) {
        this.db = dbConnection.getConnection();
    }
    
     private final String BOOK_TABLE_NAME = "book";
     private final String LIBRARY_TABLE_NAME = "libraries";
     private final String LIBRARY_BOOK_TABLE_NAME = "book_libraries"; 

    public ArrayList<Book> search(String attribute, String value)
    {
        String sqlSearch = "";
        if(attribute.equals("price"))
        {
         /* sqlSearch = "SELECT * FROM " + BOOK_TABLE_NAME + " as b " +
          "INNER JOIN " + LIBRARY_BOOK_TABLE_NAME + " as bl ON b.isbn = bl.isbn " +
          "INNER JOIN " + LIBRARY_TABLE_NAME + " as l ON bl.id = l.id where l.name = \"" + value +"\";";*/
            String priceValue = Double.toString(Double.parseDouble(value)+1);
            sqlSearch = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE " + attribute.toLowerCase() + " between " + value + " AND " + priceValue  + ";";
        }
        else
        {
            sqlSearch = "SELECT * FROM " + BOOK_TABLE_NAME + " WHERE " +  " " + attribute.toLowerCase()+ " " + " = \"" + value + "\";";
        }

        return getBookList(db.rawQuery(sqlSearch, null));
    }


    public ArrayList<Book> fetchAllBooks() {
        String sqlFetchAll = "SELECT * FROM " + BOOK_TABLE_NAME;
        return getBookList(db.rawQuery(sqlFetchAll, null));
    }

    private ArrayList<Book> getBookList(Cursor cursor) {
        ArrayList<Book> books = new ArrayList<Book>();
        while(cursor.moveToNext()) {
            Book book = new Book();

            book.setImageUrl(cursor.getString(cursor.getColumnIndex("image_url")));
            book.setAuthor(cursor.getString(cursor.getColumnIndex("author")));
            book.setISBN(cursor.getString(cursor.getColumnIndex("isbn")));
            book.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            book.setPrice(cursor.getInt(cursor.getColumnIndex("price")));
            book.setReleaseDate(cursor.getString(cursor.getColumnIndex("release_date")));
            book.setDescription(cursor.getString(cursor.getColumnIndex("description")));
            book.setCategory(cursor.getString(cursor.getColumnIndex("category")));
            books.add(book);
        }

        return books;
    }
}