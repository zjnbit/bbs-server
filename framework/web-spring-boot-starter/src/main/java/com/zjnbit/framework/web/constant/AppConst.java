package com.zjnbit.framework.web.constant;

public class AppConst {
    /**
     * bassPackage
     */
    public static final String BASE_PACKAGE = "com.zjnbit.portal";
    /**
     * 跨域允许path
     */
    public static final String CROS_PAHT_PATTERN = "/**";


    public static final String REQUST_ID = "requestId";
    public static final String REQUST_LOG_START_TIME = "request_log_start_time";
    public static final String REQUEST_BODY = "request_body";
    public static final String REQUEST_RESULT = "request_result";


    public static final String LOGIN_TYPE_USERNAME = "username";
    public static final String LOGIN_TYPE_CASDOOR = "casdoor";
    /**
     * 跨域允许方法
     */
    public static final String[] CROSS_ALLOWED_METHODS = new String[]{"GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS"};

    private AppConst() {
    }


}
