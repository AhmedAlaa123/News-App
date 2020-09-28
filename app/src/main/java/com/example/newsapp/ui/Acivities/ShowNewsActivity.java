package com.example.newsapp.ui.Acivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.newsapp.R;
import com.example.newsapp.pojo.Utils;
import com.example.newsapp.pojo.Article;
import com.example.newsapp.pojo.ArticleList;
import com.example.newsapp.ui.ArticleAdapter;
import com.example.newsapp.ui.ArticlesViewModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ShowNewsActivity extends AppCompatActivity implements
        ArticleAdapter.OnShowPageClickListener, View.OnClickListener {

    private RecyclerView recyclerViewShowArticles;
    private ArticleAdapter adapter;
    private ProgressBar progressBar;
    private Button btnReload;
    private ArticlesViewModel articlesViewModel;
    private myObsrver obsrver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_news);
        recyclerViewShowArticles = findViewById(R.id.recyclerView_show_news);
        progressBar = findViewById(R.id.progressBar_loadArticls);
        btnReload = findViewById(R.id.btn_reload);


        recyclerViewShowArticles.setHasFixedSize(true);
        adapter = new ArticleAdapter();
        recyclerViewShowArticles.setAdapter(adapter);
        adapter.setOnShowPageClickListener(this);
        btnReload.setOnClickListener(this);

        articlesViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);

        loadArticles(getIntent().getIntExtra(Utils.OPTION_EXTRA, 0));
        obsrver = new myObsrver();

        articlesViewModel.mutableLiveDataArticals.observe(this, obsrver);



    }

    private void loadArticles(int option) {
        switch (option) {
            case 1:
                Map<String, String> queryPramters;
                String category = "", countryCode = "";
                category = getIntent().getStringExtra(TopHeadLinesActivity.CATEGORY);
                countryCode = getIntent().getStringExtra(TopHeadLinesActivity.COUNTRY_CODE);
                queryPramters = new HashMap<>();
                queryPramters.put("country", countryCode);
                queryPramters.put("category", category);
                queryPramters.put("apiKey", Utils.API_KEY);
                articlesViewModel.getTopHeadlines(queryPramters);

                break;
            case 2:
                articlesViewModel.getHeadinesFromTechCrunch();
                break;
            case 3:
                articlesViewModel.getWallStreetPublishedArticles();
                break;
            case 4: {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_YEAR, -30);
                String str = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(c.getTime());
                String day, month, year;
                day = str.split("/")[1];
                month = str.split("/")[0];
                year = DateFormat.getDateInstance(DateFormat.YEAR_FIELD).format(c.getTime()).split(",")[1];
                String date = day + "-" + month + "-" + year;
                Log.d("TAG", "loadArticles: " + date);

                articlesViewModel.getAllArticlesAboutBitconFromLastMonth("everything?q=bitcoin&from=" + date + "&sortBy=publishedAt&apiKey=" + Utils.API_KEY);
                break;
            }
            case 5:
                String query = getIntent().getStringExtra(Utils.QUERY);
                Map<String, String> paramters = new HashMap<>();
                Calendar calendar = Calendar.getInstance();
                String date = DateFormat.getDateInstance(DateFormat.DATE_FIELD).format(calendar.getTime());
                paramters.put("q", query);
                paramters.put("from", date);
                paramters.put("to", date);
                paramters.put("sortBy", "popularity");
                paramters.put("apikey", Utils.API_KEY);
                Log.d("TAG", "loadArticles: " + query);
                articlesViewModel.getAllArticalsFromQuery(paramters);

                break;
        }
    }

    @Override
    public void onClick(Article article) {
        Intent intent = new Intent(this, ArticalPageActivity.class);
        articlesViewModel.mutableLiveDataArticals.removeObserver(obsrver);
        obsrver=new myObsrver();
        articlesViewModel.mutableLiveDataArticals.observe(this,obsrver);
        intent.putExtra(Utils.ARTICAL_PAGE_URL_EXATRA, article.getArticlaPageUrl());
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        loadArticles(getIntent().getIntExtra(Utils.OPTION_EXTRA, 0));

        v.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {
        finish();
    }


    class myObsrver implements Observer<Object[]> {

        @Override
        public void onChanged(Object[] objects) {
            progressBar.setVisibility(View.GONE);
            if (objects[0].toString().trim().equals("ok")) {
                ArticleList articleList = (ArticleList) objects[1];
                adapter.setArticles(articleList.getArticles());
            } else {
                Toast.makeText(ShowNewsActivity.this,objects[0].toString() , Toast.LENGTH_SHORT).show();
                btnReload.setVisibility(View.VISIBLE);
            }
        }
    }
}