package com.macco.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<News> newsList;
    private OnNewsClickListener listener;

    public interface OnNewsClickListener {
        void onNewsClick(News news, int position);
    }

    public NewsAdapter(List<News> newsList, OnNewsClickListener listener) {
        this.newsList = newsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        News news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());

        if (news.getImageUrl() != null) {
            // Use an image loading library like Glide or Picasso here
            // Glide.with(holder.itemView).load(news.getImageUrl()).into(holder.newsImage);
        } else {
            holder.newsImage.setImageResource(news.getImageResource());
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onNewsClick(news, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
        }
    }
}