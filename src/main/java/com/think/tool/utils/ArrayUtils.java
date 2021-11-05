package com.think.tool.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public abstract class ArrayUtils {

    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
    public static final int[] EMPTY_INT_ARRAY = new int[0];
    public static final long[] EMPTY_LONG_ARRAY = new long[0];
    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
    public static final char[] EMPTY_CHAR_ARRAY = new char[0];


    /**
     * length
     */
    public static int length(boolean[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(byte[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(short[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(int[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(long[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(float[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(double[] array) {
        return array == null ? 0 : array.length;
    }

    public static int length(char[] array) {
        return array == null ? 0 : array.length;
    }

    public static <T> int length(T[] array) {
        return array == null ? 0 : array.length;
    }

    /**
     * isEmpty
     */
    public static boolean isEmpty(boolean[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(byte[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(short[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(int[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(long[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(float[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(double[] array) {
        return (array == null || array.length == 0);
    }

    public static boolean isEmpty(char[] array) {
        return (array == null || array.length == 0);
    }

    public static <T> boolean isEmpty(T[] array) {
        return (array == null || array.length == 0);
    }

    /**
     * isNotEmpty
     */
    public static boolean isNotEmpty(boolean[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(byte[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(short[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(int[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(long[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(float[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(double[] array) {
        return !isEmpty(array);
    }

    public static boolean isNotEmpty(char[] array) {
        return !isEmpty(array);
    }

    public static <T> boolean isNotEmpty(T[] array) {
        return !isEmpty(array);
    }


    /**
     * toList
     */
    public static List<Boolean> toList(boolean[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Boolean> list = new ArrayList<>();
        for (Boolean value : array) {
            list.add(value);
        }
        return list;
    }

    public static List<Byte> toList(byte[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Byte> list = new ArrayList<>();
        for (Byte value : array) {
            list.add(value);
        }
        return list;
    }

    public static List<Short> toList(short[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Short> list = new ArrayList<>();
        for (Short value : array) {
            list.add(value);
        }
        return list;
    }

    public static List<Integer> toList(int[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Integer> list = new ArrayList<>();
        for (Integer j : array) {
            list.add(j);
        }
        return list;
    }

    public static List<Long> toList(long[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Long> list = new ArrayList<>();
        for (Long j : array) {
            list.add(j);
        }
        return list;
    }

    public static List<Float> toList(float[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Float> list = new ArrayList<>();
        for (Float j : array) {
            list.add(j);
        }
        return list;
    }

    public static List<Double> toList(double[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Double> list = new ArrayList<>();
        for (double j : array) {
            list.add(j);
        }
        return list;
    }

    public static List<Character> toList(char[] array) {
        if (array == null || array.length == 0) {
            return Collections.emptyList();
        }
        List<Character> list = new ArrayList<>();
        for (Character j : array) {
            list.add(j);
        }
        return list;
    }

    public static <T> List<T> toList(T[] array) {
        if (ArrayUtils.isEmpty(array)) {
            return Collections.emptyList();
        }
        return Arrays.asList(array);
    }


    /**
     * toArray
     */
    public static boolean[] booleanToArray(List<Boolean> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_BOOLEAN_ARRAY;
        }
        int size = list.size();
        boolean[] array = new boolean[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static byte[] byteToArray(List<Byte> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_BYTE_ARRAY;
        }
        int size = list.size();
        byte[] array = new byte[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static short[] shortToArray(List<Short> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_SHORT_ARRAY;
        }
        int size = list.size();
        short[] array = new short[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static int[] intToArray(List<Integer> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_INT_ARRAY;
        }
        int size = list.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }


    public static long[] longToArray(List<Long> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_LONG_ARRAY;
        }
        int size = list.size();
        long[] array = new long[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static float[] floatToArray(List<Float> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_FLOAT_ARRAY;
        }
        int size = list.size();
        float[] array = new float[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static double[] doubleToArray(List<Double> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_DOUBLE_ARRAY;
        }
        int size = list.size();
        double[] array = new double[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static char[] charToArray(List<Character> list) {
        if (CollectionUtils.isEmpty(list)) {
            return EMPTY_CHAR_ARRAY;
        }
        int size = list.size();
        char[] array = new char[size];
        for (int i = 0; i < size; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static <T> T[] listToArray(List<T> list, Class<T> clazz) {
        AssertionUtils.notNull(list);
        AssertionUtils.notNull(clazz);

        int length = list.size();
        Object objectArray = Array.newInstance(clazz, length);

        System.arraycopy(list.toArray(), 0, objectArray, 0, length);
        return (T[]) objectArray;
    }

}
