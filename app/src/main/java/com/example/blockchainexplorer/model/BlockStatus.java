package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class BlockStatus {
    @SerializedName("in_best_chain")
    private boolean inBestChain;

    @SerializedName("height")
    private long height;

    @SerializedName("next_best")
    private String nextBest;

    public BlockStatus(boolean inBestChain, long height, String nextBest) {
        this.inBestChain = inBestChain;
        this.height = height;
        this.nextBest = nextBest;
    }

    public boolean getInBestChain() {
        return inBestChain;
    }

    public void setInBestChain(boolean inBestChain) {
        this.inBestChain = inBestChain;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getNextBest() {
        return nextBest;
    }

    public void setNextBest(String nextBest) {
        this.nextBest = nextBest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockStatus blockStatus = (BlockStatus) o;
        return inBestChain == blockStatus.inBestChain &&
                height == blockStatus.height &&
                Objects.equals(nextBest, blockStatus.nextBest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(inBestChain, height, nextBest);
    }

    @NotNull
    @Override
    public String toString() {
        return "BlockStatus{" +
                "inBestChain=" + inBestChain +
                ", height=" + height +
                ", nextBest='" + nextBest + '\'' +
                '}';
    }
}
