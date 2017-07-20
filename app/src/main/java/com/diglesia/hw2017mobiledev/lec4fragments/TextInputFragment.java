package com.diglesia.hw2017mobiledev.lec4fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


public class TextInputFragment extends Fragment {
    private static final String STRING_KEY = "string_key";

    public interface Callback {
        void onStringValueChanged(String stringValue);
    }

    private Callback mCallback;

    public static TextInputFragment newInstance(String initialValue) {
        Bundle args = new Bundle();
        if (initialValue != null) {
            args.putString(STRING_KEY, initialValue);
        }
        TextInputFragment frag = new TextInputFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_text, container, false);

        EditText editText = (EditText) v.findViewById(R.id.edit_text);

        String initialStringValue = getArguments().getString(STRING_KEY);
        if (initialStringValue != null) {
            editText.setText(initialStringValue);
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                mCallback.onStringValueChanged(s.toString());
            }
        });

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mCallback = (Callback) context;
    }
}
