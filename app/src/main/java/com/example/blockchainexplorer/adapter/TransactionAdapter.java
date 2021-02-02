package com.example.blockchainexplorer.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blockchainexplorer.R;
import com.example.blockchainexplorer.model.Transaction;
import com.example.blockchainexplorer.model.Vout;

import java.text.DecimalFormat;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private final List<Transaction> transactions;
    private final Context context;

    public TransactionAdapter(List<Transaction> transactions, Context context) {
        this.transactions = transactions;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);

        // Get total value of transaction
        double value = transaction.getVout().stream().mapToDouble(Vout::getValue).sum() / 100000000;

        // Display transaction information
        DecimalFormat df = new DecimalFormat("0.#");
        df.setMaximumFractionDigits(20);
        holder.tvValue.setText(context.getString(R.string.btc_value, df.format(value)));
        holder.tvTxid.setText(transaction.getTxid());
    }


    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvValue, tvTxid;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvValue = itemView.findViewById(R.id.tv_value);
            tvTxid = itemView.findViewById(R.id.tv_txid);
        }
    }
}
