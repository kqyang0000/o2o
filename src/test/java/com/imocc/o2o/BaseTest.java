package com.imocc.o2o;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>配置spring和junit整合，junit启动时加载springIOC容器
 * <p>告诉junit spring配置文件的位置
 *
 * @author kqyang
 * @version 1.0
 * @date 2019/2/15 13:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BaseTest {
}
