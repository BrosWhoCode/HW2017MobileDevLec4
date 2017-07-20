package com.diglesia.hw2017mobiledev.lec4fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

public class SliderInputFragment extends Fragment {
    private static final String INT_KEY = "int_key";

    public interface Callback {
        void onIntValueChanged(int intValue);
    }

    private Callback mCallback;
    private SeekBar mSeekBar;

    public static SliderInputFragment newInstance(int initialValue) {
        Bundle args = new Bundle();
        args.putInt(INT_KEY, initialValue);
        SliderInputFragment frag = new SliderInputFragment();
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_slider, container, false);
        mSeekBar = (SeekBar) v.findViewById(R.id.seek_bar);

        int initialValue = getArguments().getInt(INT_KEY);
        mSeekBar.setProgress(initialValue);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mCallback.onIntValueChanged(seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
