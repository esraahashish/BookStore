package com.example.mahmayar.virtualshelfbrowser;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class Book implements Parcelable, Comparable<Book> {
    private String isbn;
    private String title;
    private float price;
    private String releaseDate;
    private String description;
    private String category;
    private String author;
    private String imageUrl;
    private String currency = "";
    private String reviewURL;

    public Book() {}

    protected Book(Parcel in) {
        imageUrl = in.readString();
        title = in.readString();
        price = (float) in.readDouble();
        currency = in.readString();
        isbn = in.readString();
        releaseDate = in.readString();
        category = in.readString();
        author = in.readString();
        reviewURL = in.readString();
        description = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String image_url) {
        this.imageUrl = image_url;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return isbn;
    }

    public void setISBN(String ISBN) {
        this.isbn = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        currency = currency;
    }

    public String getCurrency() {
        return currency;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReviewURL() {
        return reviewURL;
    }

    public void setReviewURL(String url) {
        this.reviewURL = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeString(title);
        dest.writeDouble(price);
        dest.writeString(currency);
        dest.writeString(isbn);
        dest.writeString(releaseDate);
        dest.writeString(category);
        dest.writeString(author);
        dest.writeString(reviewURL);
        dest.writeString(description);
    }

    @Override
    public int compareTo(@NonNull Book book) {
        if(!title.equals(book.title)) return -1;
        if(!category.equals(book.category)) return -1;
        if(!currency.equals(book.currency)) return -1;
        if(price!= book.price) return -1;
        if(!description.equals(book.description)) return -1;
        if(!releaseDate.equals(book.releaseDate)) return -1;
        if(!category.equals(book.category)) return -1;
        if(!author.equals(book.author)) return -1;
        if(!isbn.equals(book.isbn)) return -1;
        return 0;
    }
}