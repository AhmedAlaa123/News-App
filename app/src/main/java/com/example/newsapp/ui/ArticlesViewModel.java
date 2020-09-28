package com.example.newsapp.ui;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapp.data.ArticleClient;
import com.example.newsapp.pojo.ArticleList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.QueryMap;

public class ArticlesViewModel extends ViewModel {
    ArticleClient client = ArticleClient.getInstance();
    public MutableLiveData<Object[]> mutableLiveDataArticals = new MutableLiveData<>();


    public void getTopHeadlines(Map<String,String> paramters) {

        client.getTopHeadlines(paramters).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Log.d("TAG", "onResponse: "+response.body().getArticles().size());
                Object[] objects=new Object[2];
                objects[0]="ok";
                objects[1]=response.body();
                mutableLiveDataArticals.setValue(objects);

            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                Object[] objects=new Object[2];
                objects[0]=t.getMessage();
                objects[1]=null;
                mutableLiveDataArticals.setValue(objects);
                Log.d("TAG", "onFailure: here" + t.getMessage());
                //mutableLiveDataTopHeadlines.setValue(null);

            }
        });
    }

    public void getHeadinesFromTechCrunch() {
        client.getHeadinesFromTechCrunch().enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Object[] objects=new Object[2];
                objects[0]="ok";
                objects[1]=response.body();
                mutableLiveDataArticals.setValue(objects);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                Object[] objects=new Object[2];
                objects[0]=t.getMessage();
                objects[1]=null;
                mutableLiveDataArticals.setValue(objects);
                Log.d("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    public void getWallStreetPublishedArticles() {
        client.getWallStreetPublishedArticles().enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Object[] objects=new Object[2];
                objects[0]="ok";
                objects[1]=response.body();
                mutableLiveDataArticals.setValue(objects);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
                Object[] objects=new Object[2];
                objects[0]=t.getMessage();
                objects[1]=null;
                mutableLiveDataArticals.setValue(objects);
            }
        });
    }

    public void getAllArticlesAboutBitconFromLastMonth(String date) {
        client.getAllArticlesAboutBitconFromLastMonth(date).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Object[] objects=new Object[2];
                objects[0]="ok";
                objects[1]=response.body();
                mutableLiveDataArticals.setValue(objects);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t) {
                Object[] objects=new Object[2];
                objects[0]=t.getMessage();
                objects[1]=null;
                mutableLiveDataArticals.setValue(objects);
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }


    public void getAllArticalsFromQuery(Map<String,String> paramters) {
        client.getAllArticalsFromQuery(paramters).enqueue(new Callback<ArticleList>() {
            @Override
            public void onResponse(Call<ArticleList> call, Response<ArticleList> response) {
                Object[] objects=new Object[2];
                objects[0]="ok";
                objects[1]=response.body();
                mutableLiveDataArticals.setValue(objects);
            }

            @Override
            public void onFailure(Call<ArticleList> call, Throwable t)
            {
                Object[] objects=new Object[2];
                objects[0]=t.getMessage();
                objects[1]=null;
                mutableLiveDataArticals.setValue(objects);
                Log.d("TAG", "onFailure: " + t.getMessage());
            }
        });
    }
}
