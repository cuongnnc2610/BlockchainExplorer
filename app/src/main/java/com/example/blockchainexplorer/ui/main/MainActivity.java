package com.example.blockchainexplorer.ui.main;

import android.content.Context;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blockchainexplorer.R;
import com.example.blockchainexplorer.adapter.BlockAdapter;
import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.utils.CacheUtils;
import com.example.blockchainexplorer.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private SharedPreferences sharedPreferences;
    private static final String TAG = MainActivity.class.getSimpleName();
    private MainPresenter mainPresenter;
    private List<Block> allBlocks = new ArrayList<>();
    private BlockAdapter blockAdapter;
    private long nextHeight;
    private boolean loading = true;
    private int scrollY;

    SwipeRefreshLayout srlBlocks;
    NestedScrollView nsvBlocks;
    RecyclerView rvBlocks;
    ProgressBar pbBlocks;
    FloatingActionButton fabScrollToTheTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        this.scrollY = sharedPreferences.getInt(Constants.SCROLL_Y, 0);

        initPresenter();
        initViews();
        getLatestBlocks();
    }

    private void initPresenter() {
        mainPresenter = new MainPresenterImpl(this, getApplicationContext());
    }

    private void initViews() {
        srlBlocks = findViewById(R.id.srl_blocks);
        nsvBlocks = findViewById(R.id.nsv_blocks);
        rvBlocks = findViewById(R.id.rv_blocks);
        pbBlocks = findViewById(R.id.pb_blocks);
        fabScrollToTheTop = findViewById(R.id.fab_scroll_to_the_top);

        this.allBlocks = CacheUtils.getCacheBlocksList(MainActivity.this);
        blockAdapter = new BlockAdapter(this.allBlocks, MainActivity.this);
        rvBlocks.setAdapter(blockAdapter);
        rvBlocks.setLayoutManager(new LinearLayoutManager(this));

        // Pull down to refresh
        srlBlocks.setOnRefreshListener(this::refresh);

        // Scroll to top
        fabScrollToTheTop.setOnClickListener(v -> scrollToTheTop());

        nsvBlocks.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            // Show or hide the scroll-to-the-top button
            if (scrollY > oldScrollY || scrollY == 0) {
                fabScrollToTheTop.hide();
            } else {
                fabScrollToTheTop.show();
            }
            // Load more transactions when reached to bottom
            if (scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) - pbBlocks.getHeight()) {
                if (!loading) {
                    loading = true;
                    getLatestBlocksStartingAtStartHeight(nextHeight);
                }
            }
            this.scrollY = scrollY;
        });

    }

    private void getLatestBlocks() {
        mainPresenter.getLatestBlocks();
    }

    private void getLatestBlocksStartingAtStartHeight(long startHeight) {
        mainPresenter.getLatestBlocksStartingAtStartHeight(startHeight);
    }

    private void refresh() {
        CacheUtils.emptyCacheBlocksList(getApplicationContext());
        this.allBlocks.clear();
        blockAdapter.notifyDataSetChanged();
        this.scrollY = 0;
        getLatestBlocks();
        srlBlocks.setRefreshing(false);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayBlocks(List<Block> blocks) {
        this.loading = false;
        if(!blocks.isEmpty()) {
            // if getLatestBlocksStartingAtStartHeight(startHeight) is called -> addAll, else add each block
            if (this.allBlocks.size() > 0 && blocks.get(0).getHeight() < this.allBlocks.get(this.allBlocks.size() - 1).getHeight()) {
                this.allBlocks.addAll(blocks);
            }
            else {
                for (int i = blocks.size() - 1; i >= 0; i--) {
                    if (!this.allBlocks.contains(blocks.get(i))) {
                        this.allBlocks.add(0, blocks.get(i));
                        // if getLatestBlocks() returns new blocks, increase scrollY so that the application can keep the last view
                        if (rvBlocks.getChildAt(0) != null) {
                            this.scrollY += rvBlocks.getChildAt(0).getHeight();
                        }
                    }
                }
                // keep the last view
                nsvBlocks.smoothScrollTo(0, this.scrollY, 0);
            }
            blockAdapter.notifyDataSetChanged();
            nextHeight = this.allBlocks.get(this.allBlocks.size() - 1).getHeight() - 1;
        } else {
            Log.d(TAG,"Blocks response null");
        }
    }

    public void scrollToTheTop() {
        nsvBlocks.fullScroll(View.FOCUS_UP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Constants.SCROLL_Y, this.scrollY);
        editor.apply();
    }
}