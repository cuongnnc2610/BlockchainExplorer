package com.example.blockchainexplorer.ui.main;

public interface MainPresenter {
    void getLatestBlocks();
    void getLatestBlocksStartingAtStartHeight(long startHeight);
}
