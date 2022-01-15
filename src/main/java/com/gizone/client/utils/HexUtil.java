package com.gizone.client.utils;


import cn.hutool.core.util.ObjectUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yyt
 * 进制转换类
 */
public class HexUtil {
    /**
     * ASCII 转 16进制 大写
     *
     * @param asciiStr
     * @return
     */
    public static String asciiToHexupperCase(String asciiStr) {
        //解析出来补全
        char[] chars = asciiStr.toCharArray();
        StringBuilder hex = new StringBuilder();
        for (char ch : chars) {
            String s = Integer.toHexString((int) ch);
            if (s.length() < 2) {
                s = String.format("%s%s", "0", s);
            }
            hex.append(s);
//            System.out.println("**************ch**************");
//            System.out.println(ch);
//            System.out.println("sssssssssssss");
//            System.out.println(s);
        }

        return StringUtils.upperCase(hex.toString());

//        //电表解析有问题
//        char[] digital = "0123456789ABCDEF".toCharArray();
//        StringBuffer sb = new StringBuffer("");
//        byte[] bs = asciiStr.getBytes();
//        int bit;
//        for (int i = 0; i < bs.length; i++) {
//            bit = (bs[i] & 0x0f0) >> 4;
//            sb.append(digital[bit]);
//            bit = bs[i] & 0x0f;
//            sb.append(digital[bit]);
//        }
//        return StringUtils.upperCase(sb.toString());
    }

    /**
     * 16进制转字符串或者ASCII码
     *
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex) {

//        StringBuilder sb = new StringBuilder();
//        StringBuilder temp = new StringBuilder();
//
//        for (int i = 0; i < hex.length() - 1; i += 2) {
//
//            // grab the hex in pairs
//            String output = hex.substring(i, (i + 2));
//            // convert hex to decimal
//            int decimal = Integer.parseInt(output, 16);
//            // convert the decimal to character
//            sb.append((char) decimal);
//
//            temp.append(decimal);
//        }
//        // System.out.println(sb.toString());
//        return sb.toString();
        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        //49204c6f7665204a617661 split into two characters 49, 20, 4c...
        for (int i = 0; i < hex.length() - 1; i += 2) {

            //grab the hex in pairs
            String output = hex.substring(i, (i + 2));
            //convert hex to decimal
            int decimal = Integer.parseInt(output, 16);
            //convert the decimal to character
            sb.append((char) decimal);

            temp.append(decimal);
        }
//        System.out.println("Decimal : " + temp.toString());

        return sb.toString();
    }

    /**
     * 16转10
     *
     * @param hex
     * @return
     */
    public static String convertHexToDec(String hex) {
        Integer x = Integer.parseInt(hex, 16);
        //不足两位 补零
        return String.format("%02d", x);
    }

    /**
     * 10转16 提取纯数字
     *
     * @param hex
     * @return
     */
    public static String convertDecToHex(String hex) {
        String s = Integer.toHexString(Integer.parseInt(hex));
        //不足两位 补零
        try {
            s = new BigDecimal(s).toString();
        } catch (Exception e) {
            //提取纯数字
            String regEx = "[^0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(s);
            s = m.replaceAll("").trim();
        }
        return String.format("%02d", Integer.parseInt(s));
    }

    /**
     * 10转16
     *不提取纯数字
     * @param hex
     * @return
     */
    public static String convertDecToHexNot(String hex) {
        String s = Integer.toHexString(Integer.parseInt(hex));
        if(s.length()<2){
            return String.format("%02d", Integer.parseInt(s));
        }
        return s;
    }

    /**
     * 10转2
     *
     * @param hex
     * @return
     */
    public static String convertDecToBin(String hex) {
        String s = Integer.toBinaryString(Integer.parseInt(hex));
        //不足两位 补零
        return String.format("%08d", Integer.parseInt(s));
    }

    /**
     * 切割并且 16转10 有起始索引和结束索引
     *
     * @return
     */
    public static String substringHexToDec(Integer startIndex, Integer endIndex, String str) {
        if (StringUtils.isNotBlank(str)) {
            if (ObjectUtil.isNull(endIndex)) {
                return convertHexToDec(str.substring(startIndex));
            }
            return convertHexToDec(str.substring(startIndex, endIndex));
        }
        return null;
    }

    /**
     * 根据长度切割
     *
     * @param length
     * @param data
     * @return
     */
    public static ArrayList<String> subByLen(Integer length, String data) {
        ArrayList<String> lists = new ArrayList<>();
        char[] chars = data.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            if (sb.length() == length || i == chars.length - 1) {
                lists.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        return lists;
    }

    /**
     * 十六进制单精度浮点数，转BigDecimal，保留2为小数，截掉多余小数位
     * @param hex
     * @return
     */
    public static BigDecimal hexFloat2BigDecimal(String hex) {
        float value = Float.intBitsToFloat((int)Long.parseLong(hex, 16));
//        System.out.println(value);
        BigDecimal bd = new BigDecimal(Float.toString(value));
        return bd.setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 十六进制双精度浮点数，转BigDecimal，保留2为小数，截掉多余小数位
     * @param hex
     * @return
     */
    public static BigDecimal hexDouble2BigDecimal2(String hex) {
        double value = Double.longBitsToDouble(Long.valueOf(hex,16).longValue());
//        System.out.println(value);
        BigDecimal bd = BigDecimal.valueOf(value);
        return bd.setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * 切割 反转 16转10
     * @param hex
     * @return
     */
    public static String substringReverseHexToDec(String hex){
        ArrayList<String> strings = subByLen(2, hex);
        StringBuffer sb = new StringBuffer();
        for (String item:strings) {
            item = HexUtil.convertHexToDec(item);
            sb.insert(0,item);
        }
        return sb.toString();
    }

    /**
     * 切割 反转
     * @param hex
     * @return
     */
    public static String substringReverse(String hex){
        ArrayList<String> strings = subByLen(2, hex);
        StringBuffer sb = new StringBuffer();
        for (String item:strings) {
            sb.insert(0,item);
        }
        return sb.toString();
    }

}
