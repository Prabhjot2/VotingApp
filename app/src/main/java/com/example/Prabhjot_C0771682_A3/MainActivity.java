package com.example.Prabhjot_C0771682_A3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<candidates> candidateArray;
    private TextView var1, var2, var3;
    private Button cast_vote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        var1 = findViewById(R.id.can1_view);
        var2 = findViewById(R.id.can2_view);
        var3 = findViewById(R.id.can3_view);

        cast_vote = findViewById(R.id.cast_vote);

        candidateArray = new ArrayList<candidates>();
        Intent intent = getIntent();

        ArrayList<candidates> candidates = (ArrayList<candidates>) intent.getSerializableExtra("candidates");
        if(candidates == null){
            candidateArray.add(new candidates(1,"A",17));
            candidateArray.add(new candidates(2,"B",10));
            candidateArray.add(new candidates(3,"C",12));
        }
        else{
            candidateArray = candidates;
        }

        var1.setText(candidateArray.get(0).getName()+" : " + candidateArray.get(0).getVotes());
        var2.setText(candidateArray.get(1).getName()+" : " + candidateArray.get(1).getVotes());
        var3.setText(candidateArray.get(2).getName()+" : " + candidateArray.get(2).getVotes());

        cast_vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, secondActivity.class);
                intent.putExtra("candidates", candidateArray);
                startActivity(intent);
            }
        });


    }
}