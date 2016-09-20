package com.dzy.bizService;

import com.dzy.model.ReturnBase;
import com.dzy.model.UserInfo;
import com.dzy.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by dengzhiyuan on 2016/9/20.
 */

@Service("UserInfoHandleService")
public class UserInfoHandleService {

    private static Logger LOG = Logger.getLogger(UserInfoHandleService.class);

    @Resource
    private UserInfoService userInfoService;

    public ReturnBase testLock(String id) {
        ReturnBase returnBase=new ReturnBase();
        UserInfo userById = userInfoService.getUserById(20124045015l);
        returnBase.getData().put("userById",userById);
        return returnBase;
    }
}
