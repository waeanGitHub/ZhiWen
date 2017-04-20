package com.example.waean.zhiwen.fragment;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import com.example.waean.zhiwen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.about_page);
    }
}
