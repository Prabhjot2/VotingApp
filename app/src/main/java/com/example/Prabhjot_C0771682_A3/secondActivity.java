package com.example.Prabhjot_C0771682_A3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class secondActivity extends AppCompatActivity {
    private Spinner spinner;
    ToggleButton conditions;
    Button submit;
    EditText name, id;
    private ArrayList<candidates> can_list;
    ArrayList<Voter_details> voter_list;
    private boolean accepted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);

        voter_list = new ArrayList<Voter_details>();
        spinner = findViewById(R.id.can_spinner);
        conditions = findViewById(R.id.toggle_condition);
        submit = findViewById(R.id.submit);
        name = findViewById(R.id.name);
        id = findViewById(R.id.voter_id);


        Intent intent = getIntent();
        ArrayList<candidates> candidates = (ArrayList<candidates>) intent.getSerializableExtra("candidates");
        can_list = candidates;
        ArrayAdapter<com.example.Prabhjot_C0771682_A3.candidates> adapter = new ArrayAdapter<com.example.Prabhjot_C0771682_A3.candidates>(this,
                android.R.layout.simple_spinner_item, can_list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(secondActivity.this, "Please fill the name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(id.getText().toString().isEmpty()){
                    Toast.makeText(secondActivity.this, "Please fill the Id", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (Voter_details V : voter_list) {
                    if(V.getId() == Integer.parseInt(id.getText().toString())){
                        Toast.makeText(secondActivity.this, "ID already present!!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                if(!conditions.isChecked()){
                    Toast.makeText(secondActivity.this, "Accept the terms and condition first", Toast.LENGTH_SHORT).show();
                    return;
                }

                voter_list.add(new Voter_details(Integer.parseInt(id.getText().toString()), name.getText().toString()));
                int selectedCandidateIndex = spinner.getSelectedItemPosition();
                com.example.Prabhjot_C0771682_A3.candidates selectedCandidate = can_list.get(selectedCandidateIndex);
                selectedCandidate.setVotes(selectedCandidate.getVotes() + 1);

                Toast.makeText(secondActivity.this, "Your vote has been casted !!", Toast.LENGTH_SHORT).show();


            }
        });

        conditions.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    conditions.setTextOn("Refuse");

                } else {

                    conditions.setTextOff("Accept Terms");
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(secondActivity.this, MainActivity.class);
        intent.putExtra("candidates", can_list);
        startActivity(intent);
    }
}
