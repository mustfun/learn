package com.dzy.bizService;

import com.dzy.model.ReturnBase;
import com.dzy.model.UserInfo;
import com.dzy.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by dengzhiyuan on 2016/9/20.
 */

@Service("UserInfoHandleService")
public class UserInfoHandleService {

    private static Logger LOG = Logger.getLogger(UserInfoHandleService.class);

    @Resource
    private UserInfoService userInfoService;

    private ReentrantReadWriteLock myLock = new ReentrantReadWriteLock();

    /**
     * 这一把重量锁解决并发问题，同时吞吐量不行
     * @param id
     * @return
     */
    public synchronized ReturnBase  testsynchronizedLock(String id) {
        ReturnBase returnBase=new ReturnBase();
        Long myid=20124045015l;
        UserInfo userById = userInfoService.getUserById(myid);
        String unumber = userById.getUnumber();
        int sum=Integer.parseInt(unumber);//有1000个库存
        LOG.info("用户"+id+"准备消费库存============剩余库存"+sum);
        //准备写入
        sum=sum-1;
        UserInfo userInfo=new UserInfo();
        userInfo.setId(myid);
        userInfo.setUnumber(sum+"");
        int i=userInfoService.updateByIdCondition(userInfo);
        LOG.info("用户"+id+"消费完毕之后============还余库存"+sum);
        if (i>0){
            return returnBase;
        }else{
            returnBase.setFlag("-1");
            returnBase.setMsg("出现未知错误");
            return returnBase;
        }
    }

    /**
     * 然后测试下一读写锁的性能
     * @param id
     * @return
     */
    public  ReturnBase  testRetrantLock(String id) {
        ReturnBase returnBase=new ReturnBase();
        Long myid=20124045015l;

        myLock.writeLock().lock();

        UserInfo userById = userInfoService.getUserById(myid);
        String unumber = userById.getUnumber();
        int sum=Integer.parseInt(unumber);//有1000个库存
        LOG.info("用户"+id+"准备消费库存============剩余库存"+sum);
        //准备写入
        sum=sum-1;
        UserInfo userInfo=new UserInfo();
        userInfo.setId(myid);
        userInfo.setUnumber(sum+"");
        int i=userInfoService.updateByIdCondition(userInfo);
        LOG.info("用户"+id+"消费完毕之后============还余库存"+sum);
        myLock.writeLock().unlock();
        if (i>0){
            return returnBase;
        }else{
            returnBase.setFlag("-1");
            returnBase.setMsg("出现未知错误");
            return returnBase;
        }
    }
}
