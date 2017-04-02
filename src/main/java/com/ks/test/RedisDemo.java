package com.ks.test;

import org.apache.commons.lang.StringUtils;

import com.ks.utils.RedisUtils;

import redis.clients.jedis.Jedis;

public class RedisDemo {

	public static void main(String[] args) {

		Jedis jedisClient = RedisUtils.getInstance();
		String key = "redis_test_000000";
		String vx = jedisClient.get(key);
		jedisClient.set(key, " ");
		if (StringUtils.isNotEmpty(vx)) {
			System.out.println("key值已经存在了");
		} else {
			System.out.println("key值不存在");
		}

		String vx1 = jedisClient.get(key);
		if (StringUtils.isNotEmpty(vx1)) {
			System.out.println("key值已经存在了");
		} else {
			System.out.println("key值不存在");
		}
	}
}
