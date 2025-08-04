package com.app.contactapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.app.contactapp.R;

public class ContactDetailsFragment extends Fragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_NUMBER = "number";
    private static final String ARG_IMAGE = "image";

    public static ContactDetailsFragment newInstance(String name, String number, int imageResId) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        args.putString(ARG_NUMBER, number);
        args.putInt(ARG_IMAGE, imageResId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);

        TextView nameView = view.findViewById(R.id.textViewName);
        TextView numberView = view.findViewById(R.id.textViewNumber);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.person_icon);

        Bundle args = getArguments();
        if (args != null) {
            nameView.setText(args.getString(ARG_NAME));
            numberView.setText(args.getString(ARG_NUMBER));
            imageView.setImageResource(args.getInt(ARG_IMAGE));
        }

        return view;
    }
}

