package com.example.hellosharedprefs_91;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class SecondActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner spinner_color;
    Spinner spinner_count;
    TextView text_appearance;
    private SharedPreferences mPreferences;
    private final String sharedPrefFile = "com.example.android.hellosharedprefs_91";


    private final String COUNT_KEY = "count";
    // Key for current color
    private final String COLOR_KEY = "color";

    int color = 0;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        text_appearance = findViewById(R.id.textView_appearance);

        spinner_color = (Spinner) findViewById(R.id.color_spinner);
        spinner_color.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter_color = ArrayAdapter.createFromResource(this, R.array.array_colors, android.R.layout.simple_spinner_item);
        adapter_color.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_color.setAdapter(adapter_color);

        spinner_count = (Spinner) findViewById(R.id.count_spinner);
        spinner_count.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter_count = ArrayAdapter.createFromResource(this, R.array.array_nums, android.R.layout.simple_spinner_item);
        adapter_count.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_count.setAdapter(adapter_count);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.color_spinner) {
            if (i == 0) {
                color = R.color.blue_background;
                text_appearance.setTextColor(getResources().getColor(R.color.black));
            } else if (i == 1) {
                color = R.color.red_background;
                text_appearance.setTextColor(getResources().getColor(R.color.black));
            } else if (i == 2) {
                color = R.color.black;
                text_appearance.setTextColor(getResources().getColor(R.color.white));
            } else if (i == 3) {
                color = R.color.green_background;
                text_appearance.setTextColor(getResources().getColor(R.color.black));
            }
            text_appearance.setBackgroundColor(getResources().getColor(color));
        }else if(spinner.getId() == R.id.count_spinner){
            count = i+1;
            text_appearance.setText(" "+ count +" ");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void resetPreferences(View view){
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
    public void savePreferences(View view) {
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, count);
        preferencesEditor.putInt(COLOR_KEY, color);
        preferencesEditor.apply();
    }
}