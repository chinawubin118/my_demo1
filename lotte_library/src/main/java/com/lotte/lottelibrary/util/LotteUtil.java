package com.lotte.lottelibrary.util;

import android.os.Looper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lotte on 2016/10/8.
 * 综合工具类
 */
public class LotteUtil {
    /**
     * 根据min和max随机生成一个范围在[min,max]的随机数，包括min和max
     *
     * @param min
     * @param max
     * @return int
     */
    public static int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    /**
     * 根据min和max随机生成count个不重复的随机数组
     *
     * @param min
     * @param max
     * @param count
     * @return int[]
     */
    public static int[] getRandoms(int min, int max, int count) {
        int[] randoms = new int[count];
        List<Integer> listRandom = new ArrayList<Integer>();

        if (count > (max - min + 1)) {
            return null;
        }
        // 将所有的可能出现的数字放进候选list
        for (int i = min; i <= max; i++) {
            listRandom.add(i);
        }
        // 从候选list中取出放入数组，已经被选中的就从这个list中移除
        for (int i = 0; i < count; i++) {
            int index = getRandom(0, listRandom.size() - 1);
            randoms[i] = listRandom.get(index);
            listRandom.remove(index);
        }

        return randoms;
    }

    /**
     * 生成uuid
     */
    public static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 判断是否在主线程
     */
    public boolean isUIThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * 数据库用到的.....没啥实际意义
     */
    public static boolean isEmpty(List list) {
        return list == null || list.isEmpty();
    }

    /**
     * 验证输入的数字是否是...有效的手机号码
     */
    public static boolean isValidMobileNo(String mobileNo) {
        boolean flag = false;
        // Pattern p = Pattern.compile("^(1[358][13567890])(\\d{8})$");
        Pattern p = Pattern

                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher match = p.matcher(mobileNo);
        if (mobileNo != null) {
            flag = match.matches();
        }

        return flag;
    }
}
