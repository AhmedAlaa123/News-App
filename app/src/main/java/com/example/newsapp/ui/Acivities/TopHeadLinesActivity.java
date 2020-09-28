package com.example.newsapp.ui.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.Utils;

public class TopHeadLinesActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String COUNTRY_CODE = "COUNTRY_CODE";
    public static final String CATEGORY = "CATEGORY";
    private TextView btnShowArticles;
    private Spinner spinnerCountryCode, spinnerCategory;
    private String category;
    private String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_head_lines);
        btnShowArticles = findViewById(R.id.btn_show_articles);
        spinnerCategory = findViewById(R.id.spinner_category);
        spinnerCountryCode = findViewById(R.id.spinner_countrCode);
        ArrayAdapter countryCodeAdapter = ArrayAdapter.createFromResource(this, R.array.countriesCodes, android.R.layout.simple_spinner_item);
        countryCodeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountryCode.setAdapter(countryCodeAdapter);
        spinnerCountryCode.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                countryCode = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter categoryAdapter = ArrayAdapter.createFromResource(this, R.array.categories, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        spinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnShowArticles.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, ShowNewsActivity.class);
        intent.putExtra(Utils.OPTION_EXTRA, getIntent().getIntExtra(Utils.OPTION_EXTRA, 0));
        intent.putExtra(TopHeadLinesActivity.CATEGORY, category);
        intent.putExtra(TopHeadLinesActivity.COUNTRY_CODE, countryCode);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}