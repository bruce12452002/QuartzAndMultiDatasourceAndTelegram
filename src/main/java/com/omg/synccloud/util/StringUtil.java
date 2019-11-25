package com.omg.synccloud.util;

public class StringUtil {
    public static String getNewLine() {
        return System.getProperty("line.separator");
    }

    public static void combineHeaderAndData(String[] headers, String[] datas, StringBuilder text) {
        if (headers.length == datas.length) {
            for (var i = 0; i < headers.length; i++) {
                text.append(headers[i]).append("|")
                        .append(datas[i]).append(getNewLine());
            }
            text.append(getNewLine()).append(getNewLine());
        } else {
            throw new ArrayIndexOutOfBoundsException("標頭和內容不匹配");
        }
    }

    public static StringBuilder getInitText(String[] initHeaders, String[] initDatas) {
        if (initHeaders.length == initDatas.length) {
            StringBuilder text = new StringBuilder(getNewLine());
            for (var i = 0; i < initHeaders.length; i++) {
                text.append(initHeaders[i]).append(initDatas[i]).append(getNewLine());
            }
            return text;
        } else {
            throw new ArrayIndexOutOfBoundsException("標頭和內容不匹配");
        }
    }

    public static StringBuilder getInitText(String initHeader) {
        return new StringBuilder(getNewLine())
                .append(initHeader).append(getNewLine());
    }
}
