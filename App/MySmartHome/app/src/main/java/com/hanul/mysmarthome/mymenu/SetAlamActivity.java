package com.hanul.mysmarthome.mymenu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.hanul.mysmarthome.R;
import com.hanul.mysmarthome.databinding.ActivitySetAlamBinding;

public class SetAlamActivity extends AppCompatActivity {

    ActivitySetAlamBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetAlamBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}