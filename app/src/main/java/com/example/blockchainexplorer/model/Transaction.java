package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class Transaction {
    @SerializedName("txid")
    private String txid;

    @SerializedName("version")
    private int version;

    @SerializedName("locktime")
    private long locktime;

    @SerializedName("vin")
    private List<Vin> vin;

    @SerializedName("vout")
    private List<Vout> vout;

    @SerializedName("size")
    private int size;

    @SerializedName("weight")
    private int weight;

    @SerializedName("fee")
    private long fee;

    @SerializedName("status")
    private TransactionStatus status;

    public Transaction(String txid, int version, long locktime, List<Vin> vin, List<Vout> vout, int size, int weight, long fee, TransactionStatus status) {
        this.txid = txid;
        this.version = version;
        this.locktime = locktime;
        this.vin = vin;
        this.vout = vout;
        this.size = size;
        this.weight = weight;
        this.fee = fee;
        this.status = status;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public long getLocktime() {
        return locktime;
    }

    public void setLocktime(long locktime) {
        this.locktime = locktime;
    }

    public List<Vin> getVin() {
        return vin;
    }

    public void setVin(List<Vin> vin) {
        this.vin = vin;
    }

    public List<Vout> getVout() {
        return vout;
    }

    public void setVout(List<Vout> vout) {
        this.vout = vout;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public long getFee() {
        return fee;
    }

    public void setFee(long fee) {
        this.fee = fee;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return version == that.version &&
                locktime == that.locktime &&
                size == that.size &&
                weight == that.weight &&
                fee == that.fee &&
                Objects.equals(txid, that.txid) &&
                Objects.equals(vin, that.vin) &&
                Objects.equals(vout, that.vout) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(txid, version, locktime, vin, vout, size, weight, fee, status);
    }

    @NotNull
    @Override
    public String toString() {
        return "Transaction{" +
                "txid='" + txid + '\'' +
                ", version=" + version +
                ", locktime=" + locktime +
                ", vin=" + vin +
                ", vout=" + vout +
                ", size=" + size +
                ", weight=" + weight +
                ", fee=" + fee +
                ", status=" + status +
                '}';
    }
}
