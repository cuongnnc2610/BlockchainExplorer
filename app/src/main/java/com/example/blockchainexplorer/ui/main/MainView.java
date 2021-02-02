package com.example.blockchainexplorer.ui.main;

import com.example.blockchainexplorer.model.Block;

import java.util.List;

public interface MainView {
    void showToast(String message);
    void displayBlocks(List<Block> blocks);
}
