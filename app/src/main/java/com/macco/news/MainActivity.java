package com.macco.news;


import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NewsAdapter.OnNewsClickListener, TopStoriesAdapter.OnTopStoryClickListener {
    private RecyclerView topStoriesRecyclerView;
    private RecyclerView newsRecyclerView;
    private TopStoriesAdapter topStoriesAdapter;
    private NewsAdapter newsAdapter;
    private View mainContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContent = findViewById(R.id.main_content);
        topStoriesRecyclerView = findViewById(R.id.topStoriesRecyclerView);
        newsRecyclerView = findViewById(R.id.newsRecyclerView);

        setupTopStories();
        setupNews();

        // Handle back press to show main content
        getSupportFragmentManager().addOnBackStackChangedListener(() -> {
            if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                mainContent.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupTopStories() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        topStoriesRecyclerView.setLayoutManager(layoutManager);
        List<News> topStoriesList = getDummyTopStories();
        topStoriesAdapter = new TopStoriesAdapter(topStoriesList, this);
        topStoriesRecyclerView.setAdapter(topStoriesAdapter);
    }

    private void setupNews() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2); // 2 columns
        newsRecyclerView.setLayoutManager(layoutManager);
        List<News> newsList = getDummyNews();
        newsAdapter = new NewsAdapter(newsList, this);
        newsRecyclerView.setAdapter(newsAdapter);
    }

    private List<News> getDummyTopStories() {
        List<News> topStories = new ArrayList<>();
        topStories.add(new News("Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", "A detailed description of top story 1", R.drawable.ic_launcher_background));
        topStories.add(new News("Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", "A detailed description of top story 2", R.drawable.ic_launcher_background));
        topStories.add(new News("Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", "A detailed description of top story 3", R.drawable.ic_launcher_background));
        return topStories;
    }

    private List<News> getDummyNews() {
        List<News> news = new ArrayList<>();
        news.add(new News("News 7", "Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", R.drawable.ic_launcher_background));
        news.add(new News("News 9", "Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", R.drawable.ic_launcher_background));
        news.add(new News("News 7", "Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", R.drawable.ic_launcher_background));
        news.add(new News("News 9", "Kamala Harris says Donald Trump has created the 'greatest man-made economic crisis' in modern history\n", R.drawable.ic_launcher_background));
        return news;
    }

    @Override
    public void onNewsClick(News news, int position) {
        showNewsDetail(news);
    }

    @Override
    public void onTopStoryClick(News news, int position) {
        showNewsDetail(news);
    }

    private void showNewsDetail(News news) {
        // Hide main content when showing fragment
        mainContent.setVisibility(View.GONE);

        NewsDetailFragment fragment = NewsDetailFragment.newInstance(news);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}