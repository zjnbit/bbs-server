package com.zjnbit.bbs.api.framework.constant;

public class CacheKeyConst {

    /**
     * 用户角色 userId:[roleCode]
     */
    public static final String SECURITY_USER_ROLE = "security:user-role:";
    /**
     * 角色权限 roleCode:[permissionCode]
     */
    public static final String SECURITY_ROLE_PERMISSION = "security:role-permission:";

    public static final String SECURITY_ROLE_INFO="security:role-info:";


    public static final String BBS_NODE_ALL = "cache:bbs:node:all";
    public static final String BBS_NODE = "cache:bbs:node:";

    public static final String BBS_USER_INFO = "cache:bbs:user:";

    /**
     * 字典
     */
    public static final String SYSTEM_DICT = "system:dict:";

    public static final String FILE_URL = "file:url:";

    public static final String CONF_THIRD = "conf:third:";


    public static final String OAUTH_SIGNUP_EMAIL_CODE = "oauth:signup:email:code:";
    public static final String OAUTH_SIGNUP_EMAIL_TIMER = "oauth:signup:email:timer:";

    public static final String VERIFY_CAPTCHA_CODE = "verify:captcha:code:";
}
