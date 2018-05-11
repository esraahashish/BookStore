package com.example.mahmayar.virtualshelfbrowser;


import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void testValidJsonForOneBook(){
        String json = "{\n" +
                " \"items\": [\n" +
                "  {\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"authors\": [\n" +
                "     \"Author\"\n" +
                "    ],\n" +
                "    \"publishedDate\": \"2018-12-18\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"identifier\": \"isbn\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "     \"Cat\"\n" +
                "    ],\n" +
                "    \"imageLinks\": {\n" +
                "     \"thumbnail\": \"url\"\n" +
                "    }" +
                "},\n" +
                "   \"saleInfo\": {\n" +
                "    \"isEbook\": false\n" +
                "   }\n" +
                "  }\n" +
                " ]\n" +
                "}";

        JSONReader jsonReader = new JSONReader();
        ArrayList<Book> outputBooks = jsonReader.getBooks(json);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("Author");
        book.setCategory("Cat");
        book.setPrice(-1.0f);
        book.setCurrency("");
        book.setDescription("description");
        book.setReleaseDate("2018-12-18");
        book.setImageUrl("url");
        book.setISBN("isbn");
        book.setTitle("Title");

        expectedBooks.add(book);

        assertTrue(areEqualLists(outputBooks, expectedBooks));
    }


    @Test
    public void testValidJsonForMoreThanOneBook(){
        String json = "{\n" +
                " \"items\": [\n" +
                "  {\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"authors\": [\n" +
                "     \"Author\"\n" +
                "    ],\n" +
                "    \"publishedDate\": \"2018-12-18\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"identifier\": \"isbn\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "     \"Cat\"\n" +
                "    ],\n" +
                "    \"imageLinks\": {\n" +
                "     \"thumbnail\": \"url\"\n" +
                "    }" +
                "},\n" +
                "   \"saleInfo\": {\n" +
                "    \"isEbook\": false\n" +
                "   }\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"authors\": [\n" +
                "     \"Author\"\n" +
                "    ],\n" +
                "    \"publishedDate\": \"2018-12-18\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"identifier\": \"isbn\"\n" +
                "     }\n" +
                "    ],\n" +
                "    \"categories\": [\n" +
                "     \"Cat\"\n" +
                "    ],\n" +
                "    \"imageLinks\": {\n" +
                "     \"thumbnail\": \"url\"\n" +
                "    }" +
                "},\n" +
                "   \"saleInfo\": {\n" +
                "    \"isEbook\": false\n" +
                "   }\n" +
                "  }\n" +
                " ]\n" +
                "}";

        JSONReader jsonReader = new JSONReader();
        ArrayList<Book> outputBooks = jsonReader.getBooks(json);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Book book1 = new Book();
        book1.setAuthor("Author");
        book1.setCategory("Cat");
        book1.setPrice(-1.0f);
        book1.setCurrency("");
        book1.setDescription("description");
        book1.setReleaseDate("2018-12-18");
        book1.setImageUrl("url");
        book1.setISBN("isbn");
        book1.setTitle("Title");

        Book book2 = new Book();
        book2.setAuthor("Author");
        book2.setCategory("Cat");
        book2.setPrice(-1.0f);
        book2.setCurrency("");
        book2.setDescription("description");
        book2.setReleaseDate("2018-12-18");
        book2.setImageUrl("url");
        book2.setISBN("isbn");
        book2.setTitle("Title");

        expectedBooks.add(book1);
        expectedBooks.add(book2);

        assertTrue(areEqualLists(outputBooks, expectedBooks));
    }

    @Test
    public void testInvalidJson(){
        String json = "jfdlj";
        JSONReader jsonReader = new JSONReader();
        ArrayList<Book> outputBooks = jsonReader.getBooks(json);

        assertTrue(outputBooks.isEmpty());
    }

    @Test
    public void testEmptyCategory(){
        String json = "{\n" +
                " \"items\": [\n" +
                "  {\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"authors\": [\n" +
                "     \"Author\"\n" +
                "    ],\n" +
                "    \"publishedDate\": \"2018-12-18\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"identifier\": \"isbn\"\n" +
                "     }\n" +
                "    ],\n"+
                "    \"imageLinks\": {\n" +
                "     \"thumbnail\": \"url\"\n" +
                "    }" +
                "},\n" +
                "   \"saleInfo\": {\n" +
                "    \"isEbook\": false\n" +
                "   }\n" +
                "  }\n" +
                " ]\n" +
                "}";
        JSONReader jsonReader = new JSONReader();
        ArrayList<Book> outputBooks = jsonReader.getBooks(json);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("Author");
        book.setCategory("Unspecified");
        book.setPrice(-1.0f);
        book.setCurrency("");
        book.setDescription("description");
        book.setReleaseDate("2018-12-18");
        book.setImageUrl("url");
        book.setISBN("isbn");
        book.setTitle("Title");

        expectedBooks.add(book);

        assertTrue(areEqualLists(outputBooks, expectedBooks));
    }

    @Test
    public void testPriceExist(){
        String json = "{\n" +
                " \"items\": [\n" +
                "  {\n" +
                "   \"volumeInfo\": {\n" +
                "    \"title\": \"Title\",\n" +
                "    \"authors\": [\n" +
                "     \"Author\"\n" +
                "    ],\n" +
                "    \"publishedDate\": \"2018-12-18\",\n" +
                "    \"description\": \"description\",\n" +
                "    \"industryIdentifiers\": [\n" +
                "     {\n" +
                "      \"identifier\": \"isbn\"\n" +
                "     }\n" +
                "    ],\n"+
                "    \"imageLinks\": {\n" +
                "     \"thumbnail\": \"url\"\n" +
                "    }" +
                "},\n" +
                "   \"saleInfo\": {\n" +
                "    \"isEbook\": true,\n" +
                "    \"listPrice\": 50.0,\n" +
                "    \"currencyCode\": \"EGP\""+
                "   }\n" +
                "  }\n" +
                " ]\n" +
                "}";
        JSONReader jsonReader = new JSONReader();
        ArrayList<Book> outputBooks = jsonReader.getBooks(json);
        ArrayList<Book> expectedBooks = new ArrayList<>();
        Book book = new Book();
        book.setAuthor("Author");
        book.setCategory("Unspecified");
        book.setPrice(50.0f);
        book.setCurrency("EGP");
        book.setDescription("description");
        book.setReleaseDate("2018-12-18");
        book.setImageUrl("url");
        book.setISBN("isbn");
        book.setTitle("Title");

        expectedBooks.add(book);

        assertTrue(areEqualLists(outputBooks, expectedBooks));
    }

    private boolean areEqualLists(List<Book> actual, List<Book> expected) {
        for(int i = 0; i < actual.size(); ++i) {
            if(actual.get(i).compareTo(expected.get(i)) != 0)
                return false;
        }
        return true;
    }
}
