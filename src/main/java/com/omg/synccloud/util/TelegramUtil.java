package com.omg.synccloud.util;

public class TelegramUtil {
    private static final int INSERT_BATCH = 200;
    public static final int SEND_BATCH = 15;

    public static boolean overInsertBatch(int size) {
        return size == INSERT_BATCH;
    }
}
