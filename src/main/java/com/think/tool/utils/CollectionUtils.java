package com.think.tool.utils;

import com.think.tool.model.Pair;
import org.apache.commons.compress.utils.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author jaysunxiao
 * @version 3.0
 */
public abstract class CollectionUtils {

    /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     *
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }


    public static int size(Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    public static int size(Map<?, ?> map) {
        return map == null ? 0 : map.size();
    }

    public static <T> Iterator<T> iterator(Collection<T> collection) {
        return isEmpty(collection) ? Collections.emptyIterator() : collection.iterator();
    }

    public static <K, V> Iterator<Map.Entry<K, V>> iterator(Map<K, V> map) {
        return isEmpty(map) ? Collections.emptyIterator() : map.entrySet().iterator();
    }


    /**
     * 固定大小集合，如果初始化容量为0，则后续无法继续增加集合容量
     */
    public static List<?> newFixedList(int size) {
        return size <= 0 ? Collections.EMPTY_LIST : new ArrayList<>(size);
    }

    public static Set<?> newFixedSet(int size) {
        return size <= 0 ? Collections.EMPTY_SET : new HashSet<>(comfortableCapacity(size));
    }

    public static Map<?, ?> newFixedMap(int size) {
        return size <= 0 ? Collections.EMPTY_MAP : new HashMap<>(comfortableCapacity(size));
    }

    /**
     * The largest power of two that can be represented as an {@code int}.
     */
    public static final int MAX_POWER_OF_TWO = 1 << (Integer.SIZE - 2);

    /**
     * 计算HashMap初始化合适的大小
     * <p>
     * from com.google.common.collect.Maps.capacity()
     */
    public static int comfortableCapacity(int expectedSize) {
        if (expectedSize < 3) {
            return expectedSize + 1;
        }

        if (expectedSize < MAX_POWER_OF_TWO) {
            return (int) ((float) expectedSize / 0.75F + 1.0F);
        }

        // any large value
        return Integer.MAX_VALUE;
    }

    public static <T> List<T> collate(List<T> aList, List<T> bList, Comparator<T> comparator) {
        return collate(aList, bList, comparator, true);
    }

    /**
     * Merges two sorted Collections, a and b, into a single, sorted List
     * such that the ordering of the elements according to Comparator c is retained.
     * <p>
     * Uses the standard O(n) merge algorithm for combining two sorted lists.
     * </p>
     *
     * @param <T>               the element type
     * @param aList             the first collection, must not be null
     * @param bList             the second collection, must not be null
     * @param comparator        the comparator to use for the merge.
     * @param includeDuplicates if {@code true} duplicate elements will be retained, otherwise
     *                          they will be removed in the output collection
     * @return a new sorted List, containing the elements of Collection a and b
     */
    public static <T> List<T> collate(List<? extends T> aList, List<? extends T> bList, Comparator<? super T> comparator, boolean includeDuplicates) {

        if (aList == null || bList == null) {
            throw new NullPointerException("The collections must not be null");
        }
        if (comparator == null) {
            throw new NullPointerException("The comparator must not be null");
        }

        int totalSize = aList.size() + bList.size();

        ArrayList<T> mergedList = new ArrayList<>(totalSize);

        int aIndex = 0;
        int bIndex = 0;

        T lastItem = null;
        while (aIndex < aList.size() && bIndex < bList.size()) {
            T a = aList.get(aIndex);
            T b = bList.get(bIndex);
            if (a == null) {
                aIndex++;
                continue;
            }
            if (b == null) {
                bIndex++;
                continue;
            }

            if (comparator.compare(a, b) >= 0) {
                bIndex++;
                if (!includeDuplicates && lastItem != null && lastItem.equals(b)) {
                    continue;
                }
                mergedList.add(b);
                lastItem = b;
            } else {
                aIndex++;
                if (!includeDuplicates && lastItem != null && lastItem.equals(a)) {
                    continue;
                }
                mergedList.add(a);
                lastItem = a;
            }

        }

        if (aIndex < aList.size()) {
            for (int i = aIndex; i < aList.size(); i++) {
                T value = aList.get(i);

                if (!includeDuplicates && lastItem != null && lastItem.equals(value)) {
                    continue;
                }

                mergedList.add(value);
                lastItem = value;
            }
        }

        if (bIndex < bList.size()) {
            for (int i = bIndex; i < bList.size(); i++) {
                T value = bList.get(i);

                if (!includeDuplicates && lastItem != null && lastItem.equals(value)) {
                    continue;
                }

                mergedList.add(value);
                lastItem = value;
            }
        }

        mergedList.trimToSize();
        return mergedList;
    }


    /**
     * list合并
     *
     * @param exclusive 元素是否是独占的，也就是说是否可以重复
     * @param pairs     需要被合并的pairs集合，第一个参数是步数，第二个参数是集合
     * @return 返回合并后的list
     */
    public static <T> List<T> listJoinList(boolean exclusive, Pair<Integer, List<T>>... pairs) {
        return listJoinList(exclusive, Arrays.asList(pairs));
    }

    public static <T> List<T> listJoinList(boolean exclusive, List<Pair<Integer, List<T>>> pairs) {
        List<List<T>> iteratorList = new ArrayList<>();
        Map<List<T>, Iterator<T>> iteratorMap = new HashMap<>();
        Map<List<T>, Integer> stepMap = new HashMap<>();
        for (Pair<Integer, List<T>> pair : pairs) {
            Integer step = pair.getKey();
            List<T> list = pair.getValue();
            AssertionUtils.ge1(step);
            if (isNotEmpty(list)) {
                Iterator<T> iter = list.iterator();
                iteratorList.add(list);
                iteratorMap.put(list, iter);
                stepMap.put(list, step);
            }
        }

        List<T> result = new ArrayList<>();

        while (iteratorMap.values().stream().anyMatch(it -> it.hasNext())) {
            for (List<T> list : iteratorList) {
                Iterator<T> iterator = iteratorMap.get(list);
                int step = stepMap.get(list);
                for (int i = 0; i < step && iterator.hasNext(); i++) {
                    T element = iterator.next();
                    if (exclusive && result.contains(element)) {
                        i--;
                        continue;
                    }
                    result.add(element);
                }
            }
        }

        return result;
    }

    /**
     * 获取集合的最后几个元素
     */
    public static <T> List<T> subListLast(List<T> list, int num) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }

        int startIndex = list.size() - num;
        if (startIndex <= 0) {
            return new ArrayList<>(list);
        }

        List<T> result = new ArrayList<>();


        for (T element : list) {
            startIndex--;
            if (startIndex < 0) {
                result.add(element);
            }
        }

        return result;
    }
}
