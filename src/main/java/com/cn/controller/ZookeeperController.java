package com.cn.controller;

/**
 * Zookeeper测试
 */
import com.cn.zookeeper.session.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController

public class ZookeeperController {

    @Autowired
    private ZooKeeperSession zkSession;
    //@Autowired
    //private ZkClient zkClient;

    public ZookeeperController() {
    }

    /**
     * 获取分布式锁
     * @param type
     * @param znode
     * @param nodeData
     * @return
     */
    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public String create(int type,String znode,String nodeData){
        //znode = "/product-lock-" + znode;
        try {
            //zkClient.createNode(CreateMode.fromFlag(type),znode,nodeData);
            zkSession.acquireDistributedLock(znode,nodeData); // 如果没有成功获取锁，下面的语句将不会执行，程序进入等待状态。
            System.out.println("成功的获取了分布式锁：－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return znode;
    }

    /**
     * 释放分布锁
     */
    @RequestMapping(value = "/release",method=RequestMethod.POST)
    public String releaseDistributedLock(String znode) {
        try {
            zkSession.releaseDistributedLock(znode);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return znode;
    }
}
