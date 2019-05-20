package com.imocc.o2o.dao;

import com.imocc.o2o.BaseTest;
import com.imooc.o2o.dao.LocalAuthDao;
import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest extends BaseTest {
    private static final String username = "testusername";
    private static final String password = "testpassword";

    @Autowired
    private LocalAuthDao localAuthDao;

    @Test
    @Ignore
    public void testAInsertLocalAuth() {
        LocalAuth localAuth = new LocalAuth();
        PersonInfo personInfo = new PersonInfo();
        personInfo.setUserId(1L);
        localAuth.setPersonInfo(personInfo);
        localAuth.setUsername(username);
        localAuth.setPassword(password);
        localAuth.setCreateTime(new Date());
        localAuth.setLastEditTime(localAuth.getCreateTime());
        int effectedNum = localAuthDao.insertLocalAuth(localAuth);
        Assert.assertEquals(1, effectedNum);
    }

    @Test
    public void testBQueryLocalByUserNameAndPwd() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username, password);
        Assert.assertEquals("测试", localAuth.getPersonInfo().getName());
    }

    @Test
    public void testCQueryLocalByUserId() {
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        Assert.assertEquals("测试", localAuth.getPersonInfo().getName());
    }

    @Test
    public void testDUpdateLocalAuth(){
        int effectedNum = localAuthDao.updateLocalAuth(1L, username, password, "123456", new Date());
        Assert.assertEquals(1, effectedNum);
        LocalAuth localAuth = localAuthDao.queryLocalByUserId(1L);
        Assert.assertEquals("123456", localAuth.getPassword());
    }
}