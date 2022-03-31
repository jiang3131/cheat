package com.gyj.cheat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.gyj.cheat.databinding.ActivityMainBinding;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private boolean isCheat = false;
    private int currentIndex=0;
    private ActivityMainBinding binding;
    private List<Question> questionLists= Arrays.asList(
            new Question(R.string.question1,true),
            new Question(R.string.question2,true),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true),
            new Question(R.string.question5,false),
            new Question(R.string.question6,true)

    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.true1.setOnClickListener(this::onClick);
        binding.false1.setOnClickListener(this::onClick);
        binding.next1.setOnClickListener(this::onClick);
        binding.textView1.setText(questionLists.get(currentIndex).getTextResId());
        binding.cheat.setOnClickListener(this::onClick);

    }

    private  void checkAnswer(boolean userAnswer){
        final boolean correntAnswer = questionLists.get(currentIndex).isAnswer();
        int msgId = correntAnswer ==userAnswer ? R.string.corrcet : R.string.incorrect;
        Toast.makeText(this,msgId,Toast.LENGTH_SHORT).show();
    }
    private void updateQuestion(){
        int resId = questionLists.get(currentIndex).getTextResId();
        binding.textView1.setText(resId);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.true1:
                if(isCheat){
                    Toast.makeText(this,"作弊了哦",Toast.LENGTH_SHORT).show();
                    isCheat=false;
                }else{
                    checkAnswer(true);
                }
                break;
            case R.id.false1:
                if(isCheat){
                    Toast.makeText(this,"作弊了哦",Toast.LENGTH_SHORT).show();
                    isCheat=false;
                }else{
                    checkAnswer(false);
                }
                break;
            case R.id.next1:
                currentIndex = (currentIndex + 1)%questionLists.size();
                updateQuestion();
                break;
            case R.id.cheat:
                Intent intent = new Intent(this,SecondActivity.class);
                intent.putExtra("answer",String.valueOf(questionLists.get(currentIndex).isAnswer()));
                startActivityForResult(intent,1);
                return;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( requestCode==1&& resultCode==RESULT_OK&&data!=null){
            String returnAnswer = data.getStringExtra("isCheat");
            if( returnAnswer!=null){
                isCheat=true;
            }

        }
    }
}