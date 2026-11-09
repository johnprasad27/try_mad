package com.example.studentprofilemanager;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView nameText = findViewById(R.id.profileNameText);
        TextView rollText = findViewById(R.id.profileRollText);
        TextView departmentText = findViewById(R.id.profileDepartmentText);
        Button backButton = findViewById(R.id.backButton);

        nameText.setText(getString(R.string.profile_name, getIntent().getStringExtra("student_name")));
        rollText.setText(getString(R.string.profile_roll, getIntent().getStringExtra("roll_number")));
        departmentText.setText(getString(
                R.string.profile_department,
                getIntent().getStringExtra("department")));

        backButton.setOnClickListener(view -> finish());
    }
}
