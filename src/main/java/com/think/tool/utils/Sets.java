package com.think.tool.utils;

import java.util.HashSet;
import java.util.Set;

public class Sets {

    public static <V> Set<V> of(V... values) {
        Set<V> set = new HashSet<>();
        for (V v : values) {
            set.add(v);
        }
        return set;
    }
}
