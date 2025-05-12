package com.macco.news;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class NewsDetailFragment extends Fragment implements NewsAdapter.OnNewsClickListener {
    private static final String ARG_NEWS = "news";
    private News news;
    private RecyclerView relatedNewsRecyclerView;
    private NewsAdapter relatedNewsAdapter;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment fragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (News) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail, container, false);

        ImageView newsImage = view.findViewById(R.id.detailNewsImage);
        TextView newsTitle = view.findViewById(R.id.detailNewsTitle);
        TextView newsDescription = view.findViewById(R.id.detailNewsDescription);
        relatedNewsRecyclerView = view.findViewById(R.id.relatedNewsRecyclerView);

        if (news != null) {
            newsTitle.setText(news.getTitle());
            newsDescription.setText(news.getDescription());
            if (news.getImageUrl() != null) {
                // Use Glide or Picasso to load image
                // Glide.with(this).load(news.getImageUrl()).into(newsImage);
            } else {
                newsImage.setImageResource(news.getImageResource());
            }
        }

        setupRelatedNews();

        return view;
    }

    private void setupRelatedNews() {
        relatedNewsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        List<News> relatedNewsList = getDummyRelatedNews(); // Replace with actual data
        relatedNewsAdapter = new NewsAdapter(relatedNewsList, this);
        relatedNewsRecyclerView.setAdapter(relatedNewsAdapter);
    }

    private List<News> getDummyRelatedNews() {
        // Replace with actual related news data
        List<News> relatedNews = new ArrayList<>();
        relatedNews.add(new News("Related News 1", "Description 1", R.drawable.ic_launcher_background));
        relatedNews.add(new News("Related News 2", "Description 2", R.drawable.ic_launcher_background));
        return relatedNews;
    }

    @Override
    public void onNewsClick(News news, int position) {
        // Handle related news click
    }
}