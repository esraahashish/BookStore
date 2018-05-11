package com.example.mahmayar.virtualshelfbrowser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JSONReader {

    public ArrayList<Book> getBooks(String booksJsonStr) {
        System.out.println(booksJsonStr);
        ArrayList<Book> books = new ArrayList<Book>();
        try {
            // Root Element of JSON
            JSONObject root = new JSONObject(booksJsonStr);

            JSONArray results = root.getJSONArray("items");

            for (int i = 0; i < results.length(); i++) {
                System.out.println("inside for");
                Book book = new Book();

                JSONObject bookInfo = results.getJSONObject(i);
                String isbn = bookInfo.getJSONObject("volumeInfo").getJSONArray("industryIdentifiers").getJSONObject(0).getString("identifier");
                book.setISBN(isbn);


                String title = bookInfo.getJSONObject("volumeInfo").getString("title");
                book.setTitle(replaceQuotes(title));

                String author = bookInfo.getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
                book.setAuthor(author);

                String releaseDate = bookInfo.getJSONObject("volumeInfo").getString("publishedDate");
                book.setReleaseDate(releaseDate);

                String description = bookInfo.getJSONObject("volumeInfo").getString("description");
                book.setDescription(replaceQuotes(description));

                boolean isForSale = bookInfo.getJSONObject("saleInfo").getBoolean("isEbook");

                float price = -1.0f;

                if (isForSale) {
                    price = (float) bookInfo.getJSONObject("saleInfo").getJSONObject("listPrice").getDouble("amount");
                    book.setCurrency(bookInfo.getJSONObject("saleInfo").getJSONObject("listPrice").getString("currencyCode"));
                }
                book.setPrice(price);

                String category = "";
                try {
                    category = bookInfo.getJSONObject("volumeInfo").getJSONArray("categories").getString(0);
                } catch (JSONException e) {
                    category = "Unspecified";
                }

                book.setCategory(category);

                // add a book only if  it has an image preview
                try {
                    String imageURL = bookInfo.getJSONObject("volumeInfo").getJSONObject("imageLinks").getString("thumbnail");
                    book.setImageUrl(imageURL);
                    books.add(book);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                book.setReviewURL("https://books.google.com.eg/books?id=" + bookInfo.getString("id") + "&sitesec=reviews");
                System.out.println( bookInfo.getString("id") + " >>> " + book.getReviewURL());
            }

        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println("inside catch");
        }

        return books;
    }

    private String replaceQuotes(String str) {
        return str.replaceAll("\"", "\'");
    }

}
