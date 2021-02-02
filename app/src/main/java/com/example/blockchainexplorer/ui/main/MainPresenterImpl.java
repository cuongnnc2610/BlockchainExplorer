package com.example.blockchainexplorer.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.network.ApiClient;
import com.example.blockchainexplorer.network.ApiInterface;
import com.example.blockchainexplorer.utils.CacheUtils;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenterImpl implements MainPresenter {

    private final MainView mainView;
    private final ApiInterface apiInterface;
    private final Context context;

    public MainPresenterImpl(MainView mainView, Context context) {
        this.mainView = mainView;
        this.apiInterface = ApiClient.getClient().create(ApiInterface.class);
        this.context = context;
    }

    @Override
    public void getLatestBlocks() {
        Call<List<Block>> call = apiInterface.getLatestBlocks();
        call.enqueue(new Callback<List<Block>>() {
            @Override
            public void onResponse(@NonNull Call<List<Block>> call, @NonNull Response<List<Block>> response) {
                mainView.displayBlocks(response.body());
                CacheUtils.cacheBlocksList(response.body(), context);
            }

            @Override
            public void onFailure(@NonNull Call<List<Block>> call, @NonNull Throwable t) {
                mainView.showToast(t.toString());
                getLatestBlocks();
            }
        });
    }

    @Override
    public void getLatestBlocksStartingAtStartHeight(long startHeight) {
        Call<List<Block>> call = apiInterface.getLatestBlocksStartingAtStartHeight(startHeight);
        call.enqueue(new Callback<List<Block>>() {
            @Override
            public void onResponse(@NonNull Call<List<Block>> call, @NonNull Response<List<Block>> response) {
                mainView.displayBlocks(response.body());
                CacheUtils.cacheBlocksList(response.body(), context);
            }

            @Override
            public void onFailure(@NonNull Call<List<Block>> call, @NonNull Throwable t) {
                mainView.showToast(t.toString());
                getLatestBlocksStartingAtStartHeight(startHeight);
            }
        });
    }


}
