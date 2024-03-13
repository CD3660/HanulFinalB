package com.hanul.mysmarthome.mymenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.hanul.mysmarthome.MainActivity;
import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.FragmentMyBinding;
import com.hanul.mysmarthome.member.MemberVO;


public class MyFragment extends Fragment {
    FragmentMyBinding binding;
    MainActivity mainActivity;

    public MyFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyBinding.inflate(inflater, container, false);

        Glide.with(this)
                .load(mainActivity.getMemberVO().getProfile())
                .fallback(R.drawable.d_profile_img)
                .into(binding.profile);




        return binding.getRoot();
    }
}