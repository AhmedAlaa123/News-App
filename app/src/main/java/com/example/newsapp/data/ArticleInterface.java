package com.example.newsapp.data;

import com.example.newsapp.pojo.ArticleList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ArticleInterface {

    @GET("top-headlines")
    Call<ArticleList> getTopHeadlines(@QueryMap Map<String,String> paramters);

    @GET("top-headlines?sources=techcrunch&apiKey=ee6f48d632094b8aa52fc6935e97c40b")
    Call<ArticleList> getHeadinesFromTechCrunch();

    @GET("everything?domains=wsj.com&apiKey=ee6f48d632094b8aa52fc6935e97c40b")
    Call<ArticleList> getWallStreetPublishedArticles();

    @GET
    Call<ArticleList> getAllArticlesAboutBitconFromLastMonth(@Url String date);

    @GET("everything")
    Call<ArticleList> getAllArticalsFromQuery(@QueryMap Map<String,String> paramters);

}
