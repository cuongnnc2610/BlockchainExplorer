package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class TransactionStatus {
    @SerializedName("confirmed")
    private boolean confirmed;

    @SerializedName("block_height")
    private long blockHeight;

    @SerializedName("block_hash")
    private String blockHash;

    @SerializedName("block_time")
    private long blockTime;

    public TransactionStatus(boolean confirmed, long blockHeight, String blockHash, long blockTime) {
        this.confirmed = confirmed;
        this.blockHeight = blockHeight;
        this.blockHash = blockHash;
        this.blockTime = blockTime;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public long getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(long blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public long getBlockTime() {
        return blockTime;
    }

    public void setBlockTime(long blockTime) {
        this.blockTime = blockTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionStatus that = (TransactionStatus) o;
        return confirmed == that.confirmed &&
                blockHeight == that.blockHeight &&
                blockTime == that.blockTime &&
                Objects.equals(blockHash, that.blockHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(confirmed, blockHeight, blockHash, blockTime);
    }

    @NotNull
    @Override
    public String toString() {
        return "TransactionStatus{" +
                "confirmed=" + confirmed +
                ", blockHeight=" + blockHeight +
                ", blockHash='" + blockHash + '\'' +
                ", blockTime=" + blockTime +
                '}';
    }
}
