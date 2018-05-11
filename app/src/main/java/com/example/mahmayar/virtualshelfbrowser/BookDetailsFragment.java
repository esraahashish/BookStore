package com.example.mahmayar.virtualshelfbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BookDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BookDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BookDetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BookDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BookDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BookDetailsFragment newInstance(String param1, String param2) {
        BookDetailsFragment fragment = new BookDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book_details, container, false);
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra("book")) {
            Bundle data = intent.getExtras();
            // get the selected book details
            Book book = data.getParcelable("book");
            URL url = null;
            try {
                url = new URL(book.getImageUrl());
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                ImageView imageView = (ImageView) rootView.findViewById(R.id.book_thumbnail);
                imageView.setImageBitmap(bmp);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ((TextView) rootView.findViewById(R.id.book_title)).setText(
                    mapNullToNA(book.getTitle()));

            ((TextView) rootView.findViewById(R.id.book_price)).setText(
                    formulatePrice(book.getPrice(), book.getCurrency()));

            ((TextView) rootView.findViewById(R.id.book_isbn)).setText(
                    mapNullToNA(book.getISBN()));

            ((TextView) rootView.findViewById(R.id.book_release_date)).setText(
                    mapNullToNA(book.getReleaseDate()));

            ((TextView) rootView.findViewById(R.id.book_category)).setText(
                    mapNullToNA(book.getCategory()));

            ((TextView) rootView.findViewById(R.id.book_author)).setText(
                    mapNullToNA(book.getAuthor()));

            ((TextView) rootView.findViewById(R.id.book_reviews)).setText(
                    Html.fromHtml(
                            "<a href=\"" + book.getReviewURL() + "\">Book reviews</a> "));
            ((TextView) rootView.findViewById(R.id.book_reviews)).setMovementMethod(
                    LinkMovementMethod.getInstance());
            ((TextView) rootView.findViewById(R.id.book_description)).setText(
                    mapNullToNA(book.getDescription()));

        }
        return rootView;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    private String formulatePrice(float price, String currency) {
        if(price == -1) {
            return "Not for sale";
        }
        return String.valueOf(price) + currency;
    }

    private String mapNullToNA(String string) {
        if(!string.equals("null"))
            return string;
        return "N/A";
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
