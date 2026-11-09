package com.example.studentprofilemanager;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final String PREFERENCES_NAME = "student_profile";
    private static final String NAME_KEY = "student_name";
    private static final String ROLL_KEY = "roll_number";
    private static final String DEPARTMENT_KEY = "department";

    private EditText nameInput;
    private EditText rollInput;
    private EditText departmentInput;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.nameInput);
        rollInput = findViewById(R.id.rollInput);
        departmentInput = findViewById(R.id.departmentInput);
        Button saveButton = findViewById(R.id.saveButton);
        Button viewProfileButton = findViewById(R.id.viewProfileButton);
        Button clearButton = findViewById(R.id.clearButton);

        preferences = getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
        loadSavedDetails();

        saveButton.setOnClickListener(view -> saveDetails());
        viewProfileButton.setOnClickListener(view -> openProfile());
        clearButton.setOnClickListener(view -> clearDetails());
    }

    private void saveDetails() {
        String name = nameInput.getText().toString().trim();
        String rollNumber = rollInput.getText().toString().trim();
        String department = departmentInput.getText().toString().trim();

        if (!areDetailsValid(name, rollNumber, department)) {
            return;
        }

        Editor editor = preferences.edit();
        editor.putString(NAME_KEY, name);
        editor.putString(ROLL_KEY, rollNumber);
        editor.putString(DEPARTMENT_KEY, department);
        editor.apply();

        Toast.makeText(this, "Details Saved Successfully", Toast.LENGTH_SHORT).show();
    }

    private void openProfile() {
        String name = nameInput.getText().toString().trim();
        String rollNumber = rollInput.getText().toString().trim();
        String department = departmentInput.getText().toString().trim();

        if (!areDetailsValid(name, rollNumber, department)) {
            return;
        }

        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        intent.putExtra(NAME_KEY, name);
        intent.putExtra(ROLL_KEY, rollNumber);
        intent.putExtra(DEPARTMENT_KEY, department);
        startActivity(intent);
    }

    private boolean areDetailsValid(String name, String rollNumber, String department) {
        if (name.isEmpty() || rollNumber.isEmpty() || department.isEmpty()) {
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void loadSavedDetails() {
        nameInput.setText(preferences.getString(NAME_KEY, ""));
        rollInput.setText(preferences.getString(ROLL_KEY, ""));
        departmentInput.setText(preferences.getString(DEPARTMENT_KEY, ""));
    }

    private void clearDetails() {
        Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        nameInput.setText("");
        rollInput.setText("");
        departmentInput.setText("");
        Toast.makeText(this, "Details Cleared Successfully", Toast.LENGTH_SHORT).show();
    }
}
