package com.think.tool;

import cn.hutool.core.io.FileUtil;
import com.think.tool.config.ItemsConfig;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.io.File;

public class TestCuratorJson {

    public static void main(String[] args) throws Exception {
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(3000, 3));
        ZookeeperService zkService = new ZookeeperService(client);
        String itemNode = "/piggy/conf/items";
        String content = FileUtil.readUtf8String(new File("D:\\output\\server\\item.json"));
        zkService.createPersistentNode(itemNode, content);

        String data = zkService.getData(itemNode);
        System.out.println(data);

        ItemsConfig config = new ItemsConfig(data);
        System.out.println(config.getItems());


        CuratorCache curatorCache = zkService.registerNodeCacheListener(itemNode);
        curatorCache.listenable().addListener((type, oldData, childData) -> {
            System.out.println("type:" + type + ",oldData:" + oldData +",data:" + childData);
        });

        Thread.sleep(Integer.MAX_VALUE);
        // 获取当前客户端的状态
        CuratorFrameworkState state = client.getState();
        System.out.println("当前客户端的状态：" + (state));
    }
}
