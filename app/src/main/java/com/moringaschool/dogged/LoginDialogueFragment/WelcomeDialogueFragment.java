package com.moringaschool.dogged.LoginDialogueFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.moringaschool.dogged.R;

public class WelcomeDialogueFragment extends DialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.welcomedialogue, container, false);
        getDialog().setTitle("Confirmation Message");
        return rootView;
    }
}
