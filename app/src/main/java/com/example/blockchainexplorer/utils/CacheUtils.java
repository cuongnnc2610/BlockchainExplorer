package com.example.blockchainexplorer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.blockchainexplorer.model.Block;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class CacheUtils {

    private static SharedPreferences sharedPreferences;

    public static void cacheBlocksList(List<Block> blocks, Context context) {
        try {
            // get all blocks in cache if exists
            List<Block> cachedBlocks = getCacheBlocksList(context);

            // if getLatestBlocksStartingAtStartHeight(startHeight) is called -> addAll, else add/override each block
            if (cachedBlocks.size() > 0 && blocks.get(0).getHeight() < cachedBlocks.get(cachedBlocks.size() - 1).getHeight()) {
                cachedBlocks.addAll(blocks);
            }
            else {
                for (int i = blocks.size() - 1; i >= 0; i--) {
                    int finalI = i;
                    Block blockInCache = cachedBlocks.stream().filter(block -> block.getHeight() == (blocks.get(finalI).getHeight())).findFirst().orElse(null);
                    if (blockInCache == null) {
                        cachedBlocks.add(0, blocks.get(i));
                    } else {
                        cachedBlocks.set(cachedBlocks.indexOf(blockInCache), blocks.get(i));
                    }
                }
            }

            // write blocks to cache
            sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
            File file = new File(context.getCacheDir(), sharedPreferences.getString(Constants.BLOCKS_CACHE_FILE_NAME, null));
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cachedBlocks);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Block> getCacheBlocksList(Context context) {
        List<Block> blocks = new ArrayList<>();
        try {
            sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
            File file = new File(context.getCacheDir(), sharedPreferences.getString(Constants.BLOCKS_CACHE_FILE_NAME, null));
            if (file.length() == 0) {
                return new ArrayList<>();
            }

            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            blocks = (List<Block>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return blocks;
    }

    public static void emptyCacheBlocksList(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
        File file = new File(context.getCacheDir(), sharedPreferences.getString(Constants.BLOCKS_CACHE_FILE_NAME, null));
        if (file.isFile()) {
            try {
                RandomAccessFile raf = new RandomAccessFile(file, "rw");
                raf.setLength(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
