package com.example.blockchainexplorer.model;

import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Vout {
    @SerializedName("scriptpubkey")
    private String scriptpubkey;

    @SerializedName("scriptpubkey_asm")
    private String scriptpubkeyAsm;

    @SerializedName("scriptpubkey_type")
    private String scriptpubkeyType;

    @SerializedName("scriptpubkey_address")
    private String scriptpubkeyAddress;

    @SerializedName("value")
    private long value;

    public Vout(String scriptpubkey, String scriptpubkeyAsm, String scriptpubkeyType, String scriptpubkeyAddress, long value) {
        this.scriptpubkey = scriptpubkey;
        this.scriptpubkeyAsm = scriptpubkeyAsm;
        this.scriptpubkeyType = scriptpubkeyType;
        this.scriptpubkeyAddress = scriptpubkeyAddress;
        this.value = value;
    }

    public String getScriptpubkey() {
        return scriptpubkey;
    }

    public void setScriptpubkey(String scriptpubkey) {
        this.scriptpubkey = scriptpubkey;
    }

    public String getScriptpubkeyAsm() {
        return scriptpubkeyAsm;
    }

    public void setScriptpubkeyAsm(String scriptpubkeyAsm) {
        this.scriptpubkeyAsm = scriptpubkeyAsm;
    }

    public String getScriptpubkeyType() {
        return scriptpubkeyType;
    }

    public void setScriptpubkeyType(String scriptpubkeyType) {
        this.scriptpubkeyType = scriptpubkeyType;
    }

    public String getScriptpubkeyAddress() {
        return scriptpubkeyAddress;
    }

    public void setScriptpubkeyAddress(String scriptpubkeyAddress) {
        this.scriptpubkeyAddress = scriptpubkeyAddress;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vout vout = (Vout) o;
        return value == vout.value &&
                Objects.equals(scriptpubkey, vout.scriptpubkey) &&
                Objects.equals(scriptpubkeyAsm, vout.scriptpubkeyAsm) &&
                Objects.equals(scriptpubkeyType, vout.scriptpubkeyType) &&
                Objects.equals(scriptpubkeyAddress, vout.scriptpubkeyAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scriptpubkey, scriptpubkeyAsm, scriptpubkeyType, scriptpubkeyAddress, value);
    }

    @NotNull
    @Override
    public String toString() {
        return "Vout{" +
                "scriptpubkey='" + scriptpubkey + '\'' +
                ", scriptpubkeyAsm='" + scriptpubkeyAsm + '\'' +
                ", scriptpubkeyType='" + scriptpubkeyType + '\'' +
                ", scriptpubkeyAddress='" + scriptpubkeyAddress + '\'' +
                ", value=" + value +
                '}';
    }
}
