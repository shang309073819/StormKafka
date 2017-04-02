package com.ks.utils;

import redis.clients.jedis.Jedis;

public class RedisUtils {
	private static Jedis jedisClient = null;
	public static Jedis getInstance() {
		if (jedisClient == null) {
			jedisClient = new Jedis(Constant.REDIS_HOST, Constant.REDIS_port);
		}
		return jedisClient;
	}
}
