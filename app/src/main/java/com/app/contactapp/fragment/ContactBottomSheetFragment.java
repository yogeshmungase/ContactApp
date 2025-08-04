package com.app.contactapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.contactapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class ContactBottomSheetFragment extends BottomSheetDialogFragment {

    private static final String ARG_NAME = "name";
    private static final String ARG_NUMBER = "number";
    private static final String ARG_IMAGE = "image";

    public static ContactBottomSheetFragment newInstance(String name, String number, int imageResId) {
        ContactBottomSheetFragment fragment = new ContactBottomSheetFragment();
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
        View view = inflater.inflate(R.layout.fragment_contact_bottom_sheet, container, false);

        TextView nameText = view.findViewById(R.id.textViewName);
        TextView numberText = view.findViewById(R.id.textViewNumber);
        ImageView imageView = view.findViewById(R.id.imageView);

        if (getArguments() != null) {
            nameText.setText(getArguments().getString(ARG_NAME));
            numberText.setText(getArguments().getString(ARG_NUMBER));
            imageView.setImageResource(getArguments().getInt(ARG_IMAGE));
        }

        return view;
    }
}
