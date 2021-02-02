package com.example.blockchainexplorer.ui.blockdetail;

public interface BlockDetailPresenter {
    void getBlock(String hash);
    void getBlockStatus(String hash);
    void getTransactions(String hash, int startIndex);
}
