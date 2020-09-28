package com.example.newsapp.data;


import com.example.newsapp.pojo.ArticleList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ArticleClient {
    private ArticleInterface articleInterface;
    private static ArticleClient instance;
    private final String baseUrl = "http://newsapi.org/v2/";

    private ArticleClient() {
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        articleInterface = retrofit.create(ArticleInterface.class);
    }

    public static ArticleClient getInstance() {
        if (instance == null) instance = new ArticleClient();
        return instance;
    }


    public synchronized Call<ArticleList> getTopHeadlines(Map<String,String>paramters) {
        return articleInterface.getTopHeadlines(paramters);
    }

    public synchronized Call<ArticleList> getHeadinesFromTechCrunch() {
        return articleInterface.getHeadinesFromTechCrunch();
    }

    public synchronized Call<ArticleList> getWallStreetPublishedArticles() {
        return articleInterface.getWallStreetPublishedArticles();
    }
    public synchronized Call<ArticleList> getAllArticlesAboutBitconFromLastMonth( String url)
    {
        return articleInterface.getAllArticlesAboutBitconFromLastMonth(url);
    }
    public synchronized Call<ArticleList> getAllArticalsFromQuery(Map<String,String>pramters){
        return articleInterface.getAllArticalsFromQuery(pramters);
    }
}
