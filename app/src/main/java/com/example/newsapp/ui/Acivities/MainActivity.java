package com.example.newsapp.ui.Acivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView btnTopHeadLines;
    private TextView btn_HeadinesFromTechCrunch;
    private TextView btn_WallStreetPublishedArticles;
    private TextView btn_AllArticlesAboutBitconFromLastMonth;
    private TextView btn_AllArticalsFromQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTopHeadLines = findViewById(R.id.btn_TopHeadlines);
        btn_HeadinesFromTechCrunch = findViewById(R.id.btn_HeadinesFromTechCrunch);
        btn_WallStreetPublishedArticles = findViewById(R.id.btn_WallStreetPublishedArticles);
        btn_AllArticlesAboutBitconFromLastMonth = findViewById(R.id.btn_AllArticlesAboutBitconFromLastMonth);
        btn_AllArticalsFromQuery = findViewById(R.id.btn_AllArticalsFromQuery);


        btnTopHeadLines.setOnClickListener(this);
        btn_HeadinesFromTechCrunch.setOnClickListener(this);
        btn_WallStreetPublishedArticles.setOnClickListener(this);
        btn_AllArticlesAboutBitconFromLastMonth.setOnClickListener(this);
        btn_AllArticalsFromQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_TopHeadlines: {
                Intent intent = new Intent(this, TopHeadLinesActivity.class);
                intent.putExtra(Utils.OPTION_EXTRA, 1);
                startActivity(intent);
                break;
            }
            case R.id.btn_HeadinesFromTechCrunch: {
                Intent intent = new Intent(this, ShowNewsActivity.class);
                intent.putExtra(Utils.OPTION_EXTRA, 2);
                startActivity(intent);
                break;
            }
            case R.id.btn_WallStreetPublishedArticles: {
                Intent intent = new Intent(this, ShowNewsActivity.class);
                intent.putExtra(Utils.OPTION_EXTRA, 3);
                startActivity(intent);
                break;
            }
            case R.id.btn_AllArticlesAboutBitconFromLastMonth: {
                Intent intent = new Intent(this, ShowNewsActivity.class);
                intent.putExtra(Utils.OPTION_EXTRA, 4);
                startActivity(intent);
                break;
            }
            case R.id.btn_AllArticalsFromQuery: {
                Intent intent = new Intent(this, AllArticlesActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}