package com.think.tool.config;

import com.think.tool.utils.JsonUtils;

import java.util.List;

@Resource(value = "items")
public class ItemsConfig {
    private List<Items> items;

    public ItemsConfig(String content) {
        this.items = JsonUtils.string2List(content, Items.class);
    }

    public List<Items> getItems() {
        return items;
    }
}
