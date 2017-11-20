package org.bupt.scaffold.mis.controller;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuer;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.bupt.common.bean.ErrorResult;
import org.bupt.common.util.Validator;
import org.bupt.scaffold.mis.constant.OauthConsts;
import org.bupt.scaffold.mis.service.RedisService;
import org.bupt.scaffold.mis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 第三方开放认证控制器
 */
@RestController
@RequestMapping("oauth")
public class OauthController {

	private static final Logger logger = LoggerFactory.getLogger(OauthController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RedisService redisService;

//    @Reference
//    private static SayHelloService sayHelloService;


	/**
	 * 下发access_token
	 *
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "access_token", method = RequestMethod.POST)
	public Object accessToken(@RequestBody Map<String, String> params) {


		//获取数据
		String grantType = params.get(OauthConsts.KEY_GRANT_TYPE);
		String clientId = params.get(OauthConsts.KEY_CLIENT_ID);
		String clientSecret = params.get(OauthConsts.KEY_CLIENT_SECRET);
		logger.info("grantType = {}, clientId = {}, clientSecret = {}", grantType, clientId, clientSecret);

		//数据格式校验
		if (Validator.checkEmpty(grantType) || !OauthConsts.CLIENT_CREDENTIALS.equals(grantType)) {
			return new ErrorResult(OauthConsts.ERROR_CODE_INVALID_GRANT_TYPE, OauthConsts.ERROR_MSG_INVALID_GRANT_TYPE);
		}
		if (Validator.checkEmpty(clientId)) {
			return new ErrorResult(OauthConsts.ERROR_CODE_INVALID_CLIENT, OauthConsts.ERROR_MSG_INVALID_CLIENT_ID);
		}
		if (Validator.checkEmpty(clientSecret)) {
			return new ErrorResult(OauthConsts.ERROR_CODE_INVALID_CLIENT, OauthConsts.ERROR_MSG_INVALID_CLIENT_SECRET);
		}

		//进入数据库验证clientId / clientSecret



		//若验证通过，读取数据库中该用户的权限信息



		//生成access_token等相关回复参数
		OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
		try {
			Map<String, Object> response = new HashMap<>();
			response.put(OauthConsts.KEY_ACCESS_TOKEN, oauthIssuerImpl.accessToken());
			response.put(OauthConsts.KEY_REFRESH_TOKEN, oauthIssuerImpl.refreshToken());
			response.put(OauthConsts.KEY_EXPIRES_IN, 259200);
			response.put(OauthConsts.KEY_SCOPE, "");
			response.put(OauthConsts.KEY_SESSION_KEY, "");
			response.put(OauthConsts.KEY_SESSION_SECRET, "");

			return response;
		} catch (OAuthSystemException e) {

			e.printStackTrace();
			return new ErrorResult(OauthConsts.ERROR_CODE_SYSTEM_ERROR, OauthConsts.ERROR_MSG_SYSTEM_ERROR);
		}
	}

}
