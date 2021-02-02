package com.example.blockchainexplorer.ui.blockdetail;

import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.model.BlockStatus;
import com.example.blockchainexplorer.model.Transaction;

import java.util.List;

public interface BlockDetailView {
    void showToast(String message);
    void displayBlock(Block block);
    void displayBlockStatus(BlockStatus blockStatus);
    void displayTransactions(List<Transaction> transactions);
}
