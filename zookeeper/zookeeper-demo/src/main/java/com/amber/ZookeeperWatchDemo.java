package com.amber;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * watch more
 */
public class ZookeeperWatchDemo {
    private ZooKeeper zookeeper;
    private String oldValue = "";
    private String newValue = "";
    public ZooKeeper zkConnect( ) throws IOException {
        String path = "127.0.0.1:2181";
        zookeeper = new ZooKeeper(path, 20 * 1000, null);
        return zookeeper;
    }

    public void createZnode(String path, byte[] value, Watcher watcher, CreateMode node ) throws KeeperException, InterruptedException {
        zookeeper.create(path, value, ZooDefs.Ids.OPEN_ACL_UNSAFE, node);
    }

    public String getZnodeValue(String path ) throws KeeperException, InterruptedException {
        byte[] data = zookeeper.getData(path, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                triggerWatch(path);
            }
        }, new Stat());
        oldValue = new String(data);
        return new String(data);
    }

    public boolean triggerWatch (String path) {
        byte[] data = new byte[0];
        try {
            data = zookeeper.getData(path, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    triggerWatch(path);
                }
            }, new Stat());
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        newValue = new String(data);
        if (oldValue.equals(newValue)) {
            System.out.println("on change");
            return false;
        } else {
            System.out.println("oldvalue: " + oldValue + "new value: "  + newValue);
            oldValue = newValue;
            return true;
        }
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //创建
        ZookeeperWatchDemo zookeeperWatchDemo = new ZookeeperWatchDemo();
        ZooKeeper zooKeeper = zookeeperWatchDemo.zkConnect();
//        zookeeperDemo.createZnode("/amber", "hahaha".getBytes(), null, CreateMode.EPHEMERAL_SEQUENTIAL);
        String znodeValue = zookeeperWatchDemo.getZnodeValue("/amber");
        System.out.println(znodeValue);

        Thread.sleep(1000 * 60 * 50);
    }
}
