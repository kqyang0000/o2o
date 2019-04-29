package com.imooc.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolWriter {
    /**
     * Redis连接池对象
     */
    private JedisPool jedisPool;

    public JedisPoolWriter(final JedisPoolConfig poolConfig, final String host, final int port, final int timeout, final String password) {
        try {
            jedisPool = new JedisPool(poolConfig, host, port, timeout, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Redis连接池对象
     *
     * @return
     */
    public JedisPool getJedisPool() {
        return jedisPool;
    }

    /**
     * 注入Redis连接池对象
     *
     * @param jedisPool
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }
}
