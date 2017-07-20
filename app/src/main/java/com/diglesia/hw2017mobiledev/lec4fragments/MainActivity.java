package com.diglesia.hw2017mobiledev.lec4fragments;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements
        TextInputFragment.Callback, SliderInputFragment.Callback {

    private Button mButton;
    private TextView mTextView;

    // Model values received from fragments.
    private int mIntValue;
    private String mStringValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set initial fragment
        showTextInputFragment();

        mTextView = (TextView) findViewById(R.id.value_textview);

        mButton = (Button) findViewById(R.id.next_frag_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            int fragmentIndex;
            @Override
            public void onClick(View v) {
                // Step between zero and one.
                fragmentIndex = ++fragmentIndex % 2;
                if (fragmentIndex == 0) {
                    showTextInputFragment();
                } else {
                    showSliderInputFragment();
                }
            }
        });

    }

    private void showTextInputFragment() {
        // Wrong way...
        // Fragment fragment = new TextInputFragment();
        // fragment.setInitialText(mStringValue);

        // Better way - send an initial value for TextInputFragment to pack into its arguments
        Fragment fragment = TextInputFragment.newInstance(mStringValue);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void showSliderInputFragment() {
        // Better way - send an initial value for SliderInputFragment to pack into its arguments
        Fragment fragment = SliderInputFragment.newInstance(mIntValue);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    // Receive values from fragments via overriding their Callback methods.
    @Override
    public void onStringValueChanged(String stringValue) {
        mStringValue = stringValue;
        mTextView.setText("I received value: "+stringValue);
    }

    @Override
    public void onIntValueChanged(int intValue) {
        mIntValue = intValue;
        mTextView.setText("I received value: "+intValue);
    }
}
