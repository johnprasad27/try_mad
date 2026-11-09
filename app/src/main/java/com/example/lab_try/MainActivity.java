package com.example.lab_try;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView previousBookingText = findViewById(R.id.previous_booking_text);
        SharedPreferences preferences = getSharedPreferences("movie_booking", MODE_PRIVATE);
        String previousMovie = preferences.getString("booked_movie", null);
        if (previousMovie == null) {
            previousBookingText.setText("No previous booking");
        } else {
            previousBookingText.setText("Last booked movie: " + previousMovie);
        }

        Button leoButton = findViewById(R.id.leo_button);
        Button interstellarButton = findViewById(R.id.interstellar_button);
        Button inceptionButton = findViewById(R.id.inception_button);

        leoButton.setOnClickListener(v -> openBookingActivity("Leo"));
        interstellarButton.setOnClickListener(v -> openBookingActivity("Interstellar"));
        inceptionButton.setOnClickListener(v -> openBookingActivity("Inception"));
    }

    private void openBookingActivity(String movieName) {
        Intent intent = new Intent(MainActivity.this, BookingActivity.class);
        intent.putExtra(movieName, movieName);
        startActivity(intent);
    }
}