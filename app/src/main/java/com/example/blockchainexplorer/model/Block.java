package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Objects;

public class Block implements Serializable {
    @SerializedName("id")
    private String id;

    @SerializedName("height")
    private long height;

    @SerializedName("version")
    private long version;

    @SerializedName("timestamp")
    private long timestamp;

    @SerializedName("tx_count")
    private int txCount;

    @SerializedName("size")
    private long size;

    @SerializedName("weight")
    private long weight;

    @SerializedName("merkle_root")
    private String merkleRoot;

    @SerializedName("previousblockhash")
    private String previousBlockHash;

    @SerializedName("mediantime")
    private long medianTime;

    @SerializedName("nonce")
    private long nonce;

    @SerializedName("bits")
    private long bits;

    @SerializedName("difficulty")
    private long difficulty;

    public Block(String id, long height, long version, long timestamp, int txCount, long size, long weight, String merkleRoot, String previousBlockHash, long medianTime, long nonce, long bits, long difficulty) {
        this.id = id;
        this.height = height;
        this.version = version;
        this.timestamp = timestamp;
        this.txCount = txCount;
        this.size = size;
        this.weight = weight;
        this.merkleRoot = merkleRoot;
        this.previousBlockHash = previousBlockHash;
        this.medianTime = medianTime;
        this.nonce = nonce;
        this.bits = bits;
        this.difficulty = difficulty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getTxCount() {
        return txCount;
    }

    public void setTxCount(int txCount) {
        this.txCount = txCount;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public String getPreviousBlockHash() {
        return previousBlockHash;
    }

    public void setPreviousBlockHash(String previousBlockHash) {
        this.previousBlockHash = previousBlockHash;
    }

    public long getMedianTime() {
        return medianTime;
    }

    public void setMedianTime(long medianTime) {
        this.medianTime = medianTime;
    }

    public long getNonce() {
        return nonce;
    }

    public void setNonce(long nonce) {
        this.nonce = nonce;
    }

    public long getBits() {
        return bits;
    }

    public void setBits(long bits) {
        this.bits = bits;
    }

    public long getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(long difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return height == block.height &&
                version == block.version &&
                timestamp == block.timestamp &&
                txCount == block.txCount &&
                size == block.size &&
                weight == block.weight &&
                medianTime == block.medianTime &&
                nonce == block.nonce &&
                bits == block.bits &&
                difficulty == block.difficulty &&
                id.equals(block.id) &&
                merkleRoot.equals(block.merkleRoot) &&
                previousBlockHash.equals(block.previousBlockHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, height, version, timestamp, txCount, size, weight, merkleRoot, previousBlockHash, medianTime, nonce, bits, difficulty);
    }

    @NotNull
    @Override
    public String toString() {
        return "Block{" +
                "id='" + id + '\'' +
                ", height=" + height +
                ", version=" + version +
                ", timestamp=" + timestamp +
                ", txCount=" + txCount +
                ", size=" + size +
                ", weight=" + weight +
                ", merkleRoot='" + merkleRoot + '\'' +
                ", previousBlockHash='" + previousBlockHash + '\'' +
                ", medianTime=" + medianTime +
                ", nonce=" + nonce +
                ", bits=" + bits +
                ", difficulty=" + difficulty +
                '}';
    }
}
