package com.example.blockchainexplorer.network;

import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.model.BlockStatus;
import com.example.blockchainexplorer.model.Transaction;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("blocks")
    Call<List<Block>> getLatestBlocks();

    @GET("blocks/{start_height}")
    Call<List<Block>> getLatestBlocksStartingAtStartHeight(@Path("start_height") long startHeight);

    @GET("block/{hash}")
    Call<Block> getBlock(@Path("hash") String hash);

    @GET("block/{hash}/status")
    Call<BlockStatus> getBlockStatus(@Path("hash") String hash);

    @GET("block/{hash}/txs/{startIndex}")
    Call<List<Transaction>> getTransactions(@Path("hash") String hash, @Path("startIndex") int startIndex);
}
