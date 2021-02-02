package com.example.blockchainexplorer.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blockchainexplorer.R;
import com.example.blockchainexplorer.model.Block;
import com.example.blockchainexplorer.ui.blockdetail.BlockDetailActivity;
import com.example.blockchainexplorer.utils.StringUtils;

import java.util.List;

public class BlockAdapter extends RecyclerView.Adapter<BlockAdapter.ViewHolder> {
    private final List<Block> blocks;
    private final Context context;

    public BlockAdapter(List<Block> blocks, Context context) {
        this.blocks = blocks;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.block_item, parent, false)
        );
    }

    public Context getContext() {
        return context;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Block block = blocks.get(position);

        holder.tvHeight.setText(String.valueOf(block.getHeight()));
        holder.tvTimestamp.setText(StringUtils.timestampInSecondToDateString(block.getTimestamp()));
        holder.tvHash.setText(block.getId());
        holder.tvWeight.setText(String.valueOf(block.getWeight() / 1000.0));
        holder.tvSize.setText(String.valueOf(block.getSize() / 1000.0));
        holder.tvTransactions.setText(String.valueOf(block.getTxCount()));

        holder.llBlock.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BlockDetailActivity.class);
            intent.putExtra("hash", block.getId());
            context.startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return blocks.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout llBlock;
        TextView tvHeight, tvTimestamp, tvHash, tvWeight, tvSize, tvTransactions;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            llBlock = itemView.findViewById(R.id.ll_block);
            tvHeight = itemView.findViewById(R.id.tv_height);
            tvTimestamp = itemView.findViewById(R.id.tv_timestamp);
            tvHash = itemView.findViewById(R.id.tv_hash);
            tvWeight = itemView.findViewById(R.id.tv_weight);
            tvSize = itemView.findViewById(R.id.tv_size);
            tvTransactions = itemView.findViewById(R.id.tv_transactions);
        }
    }
}