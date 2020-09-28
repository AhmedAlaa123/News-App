package com.example.newsapp.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.pojo.Article;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> articles = new ArrayList<>();
    private OnShowPageClickListener listener;

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.textViewArticle.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).into(holder.imageViewArtcleImage);
        holder.textViewDescription.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setArticles(List<Article> articles)
    {
        this.articles=articles;
        notifyDataSetChanged();
        Log.d("TAG", "setArticles: "+this.articles.size());
    }
    private Article getArticleAt(int position)
    {
        return articles.get(position);
    }
    class ArticleViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewArtcleImage;
        public TextView textViewArticle;
        public TextView textViewDescription;
        public TextView textViewShowArticlePage;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewArtcleImage = itemView.findViewById(R.id.image_view_article_image);
            textViewArticle = itemView.findViewById(R.id.text_view_article_title);
            textViewDescription = itemView.findViewById(R.id.text_view_article_description);
            textViewShowArticlePage = itemView.findViewById(R.id.text_view_show_article_page);
            textViewShowArticlePage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null&&getAdapterPosition()!=RecyclerView.NO_POSITION)
                    {
                        listener.onClick(getArticleAt(getAdapterPosition()));
                    }
                }
            });

        }
    }
    public interface OnShowPageClickListener{
        void onClick(Article article);
    }
    public void setOnShowPageClickListener(OnShowPageClickListener listener)
    {
        this.listener=listener;
    }

}
