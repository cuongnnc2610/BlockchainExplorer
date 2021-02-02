package com.example.blockchainexplorer.ui.blockdetail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.blockchainexplorer.R;
import com.example.blockchainexplorer.adapter.TransactionAdapter;
import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.model.BlockStatus;
import com.example.blockchainexplorer.model.Transaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class BlockDetailActivity extends AppCompatActivity implements BlockDetailView {
    private static final String TAG = BlockDetailActivity.class.getSimpleName();
    private BlockDetailPresenter blockDetailPresenter;
    private final List<Transaction> transactions = new ArrayList<>();
    private TransactionAdapter transactionAdapter;
    private String hash;
    private boolean loading = true;

    SwipeRefreshLayout srlBlock;
    NestedScrollView nsvBlock;
    RecyclerView rvTransactions;
    TextView tvBlock, tvBlockHash, tvPreviousBlockHash, tvTransactions, tvSize, tvWeight, tvStatus, tvVersion, tvMerkleRoot, tvBits, tvDifficulty, tvNonce;
    ProgressBar pbTransactions;
    FloatingActionButton fabScrollToTheTop;
    ImageView ivBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_detail);

        initPresenter();
        initViews();
        getBlockInformation();
    }

    private void initPresenter() {
        blockDetailPresenter = new BlockDetailPresenterImpl(this, getBaseContext());
    }

    private void initViews() {
        srlBlock = findViewById(R.id.srl_block);
        nsvBlock = findViewById(R.id.nsv_block);
        rvTransactions = findViewById(R.id.rv_transactions);
        tvBlock = findViewById(R.id.tv_block);
        tvBlockHash = findViewById(R.id.tv_block_hash);
        tvPreviousBlockHash = findViewById(R.id.tv_previous_block_hash);
        tvTransactions = findViewById(R.id.tv_transactions);
        tvSize = findViewById(R.id.tv_size);
        tvWeight = findViewById(R.id.tv_weight);
        tvStatus = findViewById(R.id.tv_status);
        tvVersion = findViewById(R.id.tv_version);
        tvMerkleRoot = findViewById(R.id.tv_merkle_root);
        tvBits = findViewById(R.id.tv_bits);
        tvDifficulty = findViewById(R.id.tv_difficulty);
        tvNonce = findViewById(R.id.tv_nonce);
        pbTransactions = findViewById(R.id.pb_transactions);
        fabScrollToTheTop = findViewById(R.id.fab_scroll_to_the_top);
        ivBack = findViewById(R.id.iv_back);

        transactionAdapter = new TransactionAdapter(this.transactions, BlockDetailActivity.this);
        rvTransactions.setAdapter(transactionAdapter);
        rvTransactions.setLayoutManager(new LinearLayoutManager(this));

        // Pull down to refresh
        srlBlock.setOnRefreshListener(this::refresh);

        // Scroll to top
        fabScrollToTheTop.setOnClickListener(v -> scrollToTheTop());

        nsvBlock.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
            // Show or hide the scroll-to-the-top button
            if (scrollY > oldScrollY || scrollY == 0) {
                fabScrollToTheTop.hide();
            } else {
                fabScrollToTheTop.show();
            }

            // Load more transactions when reached to bottom
            if (scrollY >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) - pbTransactions.getHeight()) {
                if (!loading) {
                    loading = true;
                    blockDetailPresenter.getTransactions(hash, this.transactions.size());
                }
            }
        });

        ivBack.setOnClickListener(v -> backToHomepage());
    }

    private void getBlockInformation() {
        hash = getIntent().getStringExtra("hash");
        CompletableFuture<Void> cfBlock = CompletableFuture.runAsync(() -> blockDetailPresenter.getBlock(hash));
        CompletableFuture<Void> cfStatus = CompletableFuture.runAsync(() -> blockDetailPresenter.getBlockStatus(hash));
        CompletableFuture<Void> cfTransactions = CompletableFuture.runAsync(() -> blockDetailPresenter.getTransactions(hash, this.transactions.size()));
        CompletableFuture<Void> cfBlockInformation = CompletableFuture.allOf(cfBlock, cfStatus, cfTransactions);
        try {
            cfBlockInformation.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void refresh() {
        this.transactions.clear();
        getBlockInformation();
        srlBlock.setRefreshing(false);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(BlockDetailActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayBlock(Block block) {
        tvBlock.setText(String.valueOf(block.getHeight()));
        tvBlockHash.setText(block.getId());
        tvPreviousBlockHash.setText(block.getPreviousBlockHash());
        tvTransactions.setText(String.valueOf(block.getTxCount()));
        tvSize.setText(String.valueOf(block.getSize() / 1000.0));
        tvWeight.setText(String.valueOf(block.getWeight() / 1000.0));
        tvVersion.setText(getString(R.string.prefix_of_hexadecimal, Long.toHexString(block.getVersion())));
        tvMerkleRoot.setText(block.getMerkleRoot());
        tvBits.setText(getString(R.string.prefix_of_hexadecimal, Long.toHexString(block.getBits())));
        tvDifficulty.setText(String.valueOf(block.getDifficulty()));
        tvNonce.setText(getString(R.string.prefix_of_hexadecimal, Long.toHexString(block.getNonce())));
    }

    @Override
    public void displayBlockStatus(BlockStatus blockStatus) {
        tvStatus.setText(blockStatus.getInBestChain() ? R.string.in_best_chain : R.string.not_in_best_chain);
    }

    @Override
    public void displayTransactions(List<Transaction> transactions) {
        this.loading = false;
        if(!transactions.isEmpty()) {
            this.transactions.addAll(transactions);
            transactionAdapter.notifyDataSetChanged();
        } else {
            Log.d(TAG,"Transactions response null");
        }
        srlBlock.setAlpha(1);
    }

    public void backToHomepage() {
        finish();
    }

    public void scrollToTheTop() {
        nsvBlock.fullScroll(View.FOCUS_UP);
    }
}