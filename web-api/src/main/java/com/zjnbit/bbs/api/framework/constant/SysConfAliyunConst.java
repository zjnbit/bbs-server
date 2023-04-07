package com.zjnbit.bbs.api.framework.constant;

public class SysConfAliyunConst {
    public static final String CONF_KEY_PREFIX = "aliyun:";
    public static final String CACHE_KEY_BASIC = "conf:aliyun:";
    public static final String CACHE_KEY_SECURITY = CACHE_KEY_BASIC + "security";
    public static final String CACHE_KEY_OSS = CACHE_KEY_BASIC + "oss";


    public static final String CONF_KEY_SECURITY_ACCESS_KEY = "aliyun:security:access-key";
    public static final String CONF_KEY_SECURITY_ACCESS_KEY_SECRET = "aliyun:security:access-key-secret";
    public static final String CONF_KEY_OSS_BUCKET = "aliyun:oss:bucket";
    public static final String CONF_KEY_OSS_ENDPOINT = "aliyun:oss:endpoint";
    public static final String CONF_KEY_OSS_BUCKET_URL = "aliyun:oss:bucket-url";
    public static final String CONF_KEY_OSS_CDN_URL = "aliyun:oss:cdn-url";
    public static final String CONF_KEY_OSS_CALLBACK_URL = "aliyun:oss:callback-url";
}
