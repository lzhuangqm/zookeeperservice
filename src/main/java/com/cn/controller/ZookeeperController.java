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

    @RequestMapping(value = "/create",method=RequestMethod.POST)
    public String create(int type,String znode,String nodeData){
        //znode = "/product-lock-" + znode;
        try {
            //zkClient.createNode(CreateMode.fromFlag(type),znode,nodeData);
            zkSession.acquireDistributedLock(znode,nodeData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return znode;
    }

}
