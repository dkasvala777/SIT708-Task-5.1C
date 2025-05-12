package com.macco.news;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class TopStoriesAdapter extends RecyclerView.Adapter<TopStoriesAdapter.TopStoryViewHolder> {
    private List<News> topStoriesList;
    private OnTopStoryClickListener listener;

    public interface OnTopStoryClickListener {
        void onTopStoryClick(News news, int position);
    }

    public TopStoriesAdapter(List<News> topStoriesList, OnTopStoryClickListener listener) {
        this.topStoriesList = topStoriesList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TopStoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_top_story, parent, false);
        return new TopStoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopStoryViewHolder holder, int position) {
        News news = topStoriesList.get(position);
        holder.newsTitle.setText(news.getTitle());

        if (news.getImageUrl() != null) {
            // Use an image loading library like Glide or Picasso here
            // Glide.with(holder.itemView).load(news.getImageUrl()).into(holder.newsImage);
        } else {
            holder.newsImage.setImageResource(news.getImageResource());
        }

        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onTopStoryClick(news, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topStoriesList.size();
    }

    static class TopStoryViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle;

        TopStoryViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
        }
    }
}