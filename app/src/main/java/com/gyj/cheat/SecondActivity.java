package com.gyj.cheat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.gyj.cheat.databinding.ActivityMainBinding;
import com.gyj.cheat.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {
        private ActivitySecondBinding binding2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding2 = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding2.getRoot());
        binding2.fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent returnintent = new Intent();
                returnintent.putExtra("isCheat","true");
                setResult(RESULT_OK,returnintent);
                finish();
            }
        });
        binding2.show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding2.answer.setText(getIntent().getStringExtra("answer"));
            }
        });
    }
}