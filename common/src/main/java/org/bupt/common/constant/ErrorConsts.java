package org.bupt.common.constant;

/**
 * 错误常量类
 */
public class ErrorConsts {


	public static final String KEY_ERROR_CODE = "error_code";
	public static final String KEY_ERROR_MSG = "error_msg";


	/**
	 * Oauth2.0错误
	 */
	//错误码(100-199)
	public static final int OAUTH_CODE_SYSTEM_ERROR = 100;
	public static final int OAUTH_CODE_INVALID_GRANT_TYPE = 101;
	public static final int OAUTH_CODE_INVALID_CLIENT = 102;
	public static final int OAUTH_CODE_ACCESS_TOKEN_INVALID = 103;
	public static final int OAUTH_CODE_TOKEN_INVALID = 103;
	public static final int OAUTH_CODE_PERMISSION_DENIED = 104;
	public static final int OAUTH_CODE_ROLE_DENIED = 105;
	public static final int OAUTH_CODE_UNDEFINED_ERROR = 199;

	//错误描述
	public static final String OAUTH_MSG_SYSTEM_ERROR = "system error";
	public static final String OAUTH_MSG_INVALID_GRANT_TYPE = "invalid grant type";
	public static final String OAUTH_MSG_INVALID_CLIENT_ID = "invalid client";
	public static final String OAUTH_MSG_INVALID_CLIENT_SECRET = "invalid client";
	public static final String OAUTH_MSG_ACCESS_TOKEN_INVALID = "access token invalid or no longer valid";
	public static final String OAUTH_MSG_TOKEN_INVALID = "token invalid or no longer valid";
	public static final String OAUTH_MSG_PERMISSION_DENIED = "No permission to access data";
	public static final String OAUTH_MSG_ROLE_DENIED = "Invalid role to access data";
	public static final String OAUTH_MSG_UNDEFINED_ERROR = "Undefined oauth error";

}
