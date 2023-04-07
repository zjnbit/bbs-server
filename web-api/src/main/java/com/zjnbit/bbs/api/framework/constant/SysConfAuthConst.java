package com.zjnbit.bbs.api.framework.constant;

public class SysConfAuthConst {
    public static final String CONF_KEY_PREFIX = "auth:";
    public static final String CACHE_KEY_BASIC = "conf:auth:";
    public static final String CACHE_KEY_SECRET = CACHE_KEY_BASIC + "secret";
    public static final String CACHE_KEY_CAS = CACHE_KEY_BASIC + "cas";

    public static final String CONF_KEY_SECRET_AES_KEY = "auth:secret:aes-key";
    public static final String CONF_KEY_CAS_CLIENT_ID = "auth:cas:client-id";
    public static final String CONF_KEY_CAS_CLIENT_SECRET = "auth:cas:client-secret";
    public static final String CONF_KEY_CAS_SERVER_URL = "auth:cas:server-url";

    public static final String API_HEADER_AUTHORIZATION = "Authorization";
    public static final String API_HEADER_AUTHORIZATION_VALUE = "Bearer ";
    public static final String API_URL_ACCESS_TOKEN = "/api/login/oauth/access_token";
    public static final String API_PARAM_ACCESS_TOKEN_GRANT_TYPE = "authorization_code";

    public static final String API_URL_USERINFO = "/api/userinfo";


}
