package com.arijit.pro;

import android.app.Activity ;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.widget.TextView;
import android.app.ActionBar;
import android.support.v4.app.NavUtils;

public class DisplayMessageActivity extends Activity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get the message from the intent
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        int left = intent.getIntExtra("left_position", -1);

        // Create the text view
        TextView textView = new TextView(this);
        textView.setTextSize(40);
        textView.setText(message + "\n And left position is: " + left);

        // Set the text view as the activity layout
        setContentView(textView);

        // Insert the back button 
        getActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        // Respond to the action bar's Up/Home button
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}