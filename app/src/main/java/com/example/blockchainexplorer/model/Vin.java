package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Vin {
    @SerializedName("txid")
    private String txid;

    @SerializedName("vout")
    private long vout;

    @SerializedName("prevout")
    private Vout prevout;

    @SerializedName("scriptsig")
    private String scriptsig;

    @SerializedName("scriptsig_asm")
    private String scriptsigAsm;

    @SerializedName("witness")
    private String[] witness;

    @SerializedName("is_coinbase")
    private boolean isCoinbase;

    @SerializedName("sequence")
    private long sequence;

    @SerializedName("inner_redeemscript_asm")
    private String innerRedeemscriptAsm;

    @SerializedName("inner_witnessscript_asm")
    private String innerWitnessscriptAsm;

    public Vin(String txid, long vout, Vout prevout, String scriptsig, String scriptsigAsm, String[] witness, boolean isCoinbase, long sequence, String innerRedeemscriptAsm, String innerWitnessscriptAsm) {
        this.txid = txid;
        this.vout = vout;
        this.prevout = prevout;
        this.scriptsig = scriptsig;
        this.scriptsigAsm = scriptsigAsm;
        this.witness = witness;
        this.isCoinbase = isCoinbase;
        this.sequence = sequence;
        this.innerRedeemscriptAsm = innerRedeemscriptAsm;
        this.innerWitnessscriptAsm = innerWitnessscriptAsm;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public long getVout() {
        return vout;
    }

    public void setVout(long vout) {
        this.vout = vout;
    }

    public Vout getPrevout() {
        return prevout;
    }

    public void setPrevout(Vout prevout) {
        this.prevout = prevout;
    }

    public String getScriptsig() {
        return scriptsig;
    }

    public void setScriptsig(String scriptsig) {
        this.scriptsig = scriptsig;
    }

    public String getScriptsigAsm() {
        return scriptsigAsm;
    }

    public void setScriptsigAsm(String scriptsigAsm) {
        this.scriptsigAsm = scriptsigAsm;
    }

    public String[] getWitness() {
        return witness;
    }

    public void setWitness(String[] witness) {
        this.witness = witness;
    }

    public boolean isCoinbase() {
        return isCoinbase;
    }

    public void setCoinbase(boolean coinbase) {
        isCoinbase = coinbase;
    }

    public long getSequence() {
        return sequence;
    }

    public void setSequence(long sequence) {
        this.sequence = sequence;
    }

    public String getInnerRedeemscriptAsm() {
        return innerRedeemscriptAsm;
    }

    public void setInnerRedeemscriptAsm(String innerRedeemscriptAsm) {
        this.innerRedeemscriptAsm = innerRedeemscriptAsm;
    }

    public String getInnerWitnessscriptAsm() {
        return innerWitnessscriptAsm;
    }

    public void setInnerWitnessscriptAsm(String innerWitnessscriptAsm) {
        this.innerWitnessscriptAsm = innerWitnessscriptAsm;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vin vin = (Vin) o;
        return vout == vin.vout &&
                isCoinbase == vin.isCoinbase &&
                sequence == vin.sequence &&
                Objects.equals(txid, vin.txid) &&
                Objects.equals(prevout, vin.prevout) &&
                Objects.equals(scriptsig, vin.scriptsig) &&
                Objects.equals(scriptsigAsm, vin.scriptsigAsm) &&
                Arrays.equals(witness, vin.witness) &&
                Objects.equals(innerRedeemscriptAsm, vin.innerRedeemscriptAsm) &&
                Objects.equals(innerWitnessscriptAsm, vin.innerWitnessscriptAsm);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(txid, vout, prevout, scriptsig, scriptsigAsm, isCoinbase, sequence, innerRedeemscriptAsm, innerWitnessscriptAsm);
        result = 31 * result + Arrays.hashCode(witness);
        return result;
    }

    @NotNull
    @Override
    public String toString() {
        return "Vin{" +
                "txid='" + txid + '\'' +
                ", vout=" + vout +
                ", prevout=" + prevout +
                ", scriptsig='" + scriptsig + '\'' +
                ", scriptsigAsm='" + scriptsigAsm + '\'' +
                ", witness=" + Arrays.toString(witness) +
                ", isCoinbase=" + isCoinbase +
                ", sequence=" + sequence +
                ", innerRedeemscriptAsm='" + innerRedeemscriptAsm + '\'' +
                ", innerWitnessscriptAsm='" + innerWitnessscriptAsm + '\'' +
                '}';
    }
}
