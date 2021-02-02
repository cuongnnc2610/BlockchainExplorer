package com.example.blockchainexplorer.ui.blockdetail;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.model.BlockStatus;
import com.example.blockchainexplorer.model.Transaction;
import com.example.blockchainexplorer.network.ApiClient;
import com.example.blockchainexplorer.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlockDetailPresenterImpl implements BlockDetailPresenter {

    private final BlockDetailView blockDetailView;
    private final ApiInterface apiInterface;
    private final Context context;

    public BlockDetailPresenterImpl(BlockDetailView blockDetailView, Context context) {
        this.blockDetailView = blockDetailView;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
        this.context = context;
    }

    @Override
    public void getBlock(String hash) {
        Call<Block> call = apiInterface.getBlock(hash);
        call.enqueue(new Callback<Block>() {
            @Override
            public void onResponse(@NonNull Call<Block> call, @NonNull Response<Block> response) {
                blockDetailView.displayBlock(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Block> call, @NonNull Throwable t) {
                blockDetailView.showToast(t.toString());
                getBlock(hash);
            }
        });
    }

    @Override
    public void getBlockStatus(String hash) {
        Call<BlockStatus> call = apiInterface.getBlockStatus(hash);
        call.enqueue(new Callback<BlockStatus>() {
            @Override
            public void onResponse(@NonNull Call<BlockStatus> call, @NonNull Response<BlockStatus> response) {
                blockDetailView.displayBlockStatus(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<BlockStatus> call, @NonNull Throwable t) {
                blockDetailView.showToast(t.toString());
                getBlockStatus(hash);
            }
        });
    }

    @Override
    public void getTransactions(String hash, int startIndex) {
        Call<List<Transaction>> call = apiInterface.getTransactions(hash, startIndex);
        call.enqueue(new Callback<List<Transaction>>() {
            @Override
            public void onResponse(@NonNull Call<List<Transaction>> call, @NonNull Response<List<Transaction>> response) {
                blockDetailView.displayTransactions(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Transaction>> call, @NonNull Throwable t) {
                blockDetailView.showToast(t.toString());
                getTransactions(hash, startIndex);
            }
        });
    }
}
