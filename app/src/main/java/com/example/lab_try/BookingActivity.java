package com.example.lab_try;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class BookingActivity extends AppCompatActivity {

    private String selectedMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        if (getIntent().getExtras() != null && !getIntent().getExtras().keySet().isEmpty()) {
            String movieKey = getIntent().getExtras().keySet().iterator().next();
            selectedMovie = getIntent().getStringExtra(movieKey);
        }

        TextView selectedMovieText = findViewById(R.id.selected_movie_text);
        selectedMovieText.setText(selectedMovie);

        Button confirmButton = findViewById(R.id.confirm_booking_button);
        Button backButton = findViewById(R.id.back_button);

        confirmButton.setOnClickListener(v -> {
            SharedPreferences preferences = getSharedPreferences("movie_booking", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("booked_movie", selectedMovie);
            editor.apply();
            Toast.makeText(BookingActivity.this, "Ticket Booked Successfully", Toast.LENGTH_SHORT).show();
        });

        backButton.setOnClickListener(v -> finish());
    }
}
