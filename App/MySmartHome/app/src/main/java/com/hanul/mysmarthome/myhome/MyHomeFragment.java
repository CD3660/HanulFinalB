package com.hanul.mysmarthome.myhome;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.FragmentMyHomeBinding;

public class MyHomeFragment extends Fragment {
    FragmentMyHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyHomeBinding.inflate(inflater, container, false);












        return binding.getRoot();
    }
}