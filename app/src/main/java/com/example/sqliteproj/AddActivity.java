package com.example.sqliteproj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {
    private Button btSave,btCancel;
    private EditText etTeamHome,etTeamGuest,etGoalsHome,etGoalsGuest;
    private Context context;
    private long MyMatchID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etTeamHome=(EditText)findViewById(R.id.TeamHome);
        etTeamGuest=(EditText)findViewById(R.id.TeamGuest);
        etGoalsHome=(EditText)findViewById(R.id.GoalsHome);
        etGoalsGuest=(EditText)findViewById(R.id.GoalsGuest);
        btSave=(Button)findViewById(R.id.butSave);
        btCancel=(Button)findViewById(R.id.butCancel);

        if(getIntent().hasExtra("Matches")){
            Matches matches=(Matches)getIntent().getSerializableExtra("Matches");
            etTeamHome.setText(matches.getTeamhouse());
            etTeamGuest.setText(matches.getTeamguest());
            etGoalsHome.setText(String.valueOf(matches.getGoalshouse()));
            etGoalsGuest.setText(String.valueOf(matches.getGoalsguest()));
            MyMatchID=matches.getId();
        }
        else
        {
            MyMatchID=-1;
        }
        btSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matches matches=new Matches(
                        MyMatchID,
                        etTeamHome.getText().toString(),
                        etTeamGuest.getText().toString(),
                        Integer.parseInt(etGoalsHome.getText().toString()),
                        Integer.parseInt(etGoalsGuest.getText().toString())
                );
                Intent intent = getIntent();
                intent.putExtra("Matches", matches);
                setResult(RESULT_OK,intent);
                finish();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}