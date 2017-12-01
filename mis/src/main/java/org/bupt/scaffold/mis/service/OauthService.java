package org.bupt.scaffold.mis.service;


import org.bupt.scaffold.mis.constant.EnvConsts;
import org.bupt.scaffold.mis.constant.RedisConsts;
import org.bupt.scaffold.mis.pojo.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 认证服务类（发短信、发邮件、缓存验证码）
 * Created by ken on 2017/11/1.
 */
@Service
public class OauthService extends BaseService<User> {

	private static final Logger logger = LoggerFactory.getLogger(OauthService.class);

	@Autowired
	private StringRedisTemplate redisMapper;

	@Autowired
	private EnvConsts envConsts;


	/**
	 * 获取用户对应的角色
	 * @param userId 用户ID号
	 */
	public String getUserRole(Integer userId) {
		return (String) redisMapper.opsForHash().get(RedisConsts.AIOP_USER_ROLE, userId.toString());
	}

	/**
	 * 获取应用对应的权限
	 * @param appId 应用ID号
	 */
	public String getAppPermission(Integer appId) {
		return (String) redisMapper.opsForHash().get(RedisConsts.AIOP_APP_PERMISSION, appId.toString());
	}


	/**
	 * 保存验证码到redis，过期时间为1分钟
	 *
	 * @param key
	 * @param value
	 */
	public void saveSmSCode(String key, String value) {
		redisMapper.opsForValue().set(key, value, envConsts.SMS_CODE_EXPIRE, TimeUnit.SECONDS);
	}

}
