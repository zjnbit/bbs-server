package com.zjnbit.framework.web.exception;


import com.zjnbit.framework.web.model.BaseError;

public enum RequestError implements BaseError {

    A0001("A0001", "用户端错误"),
    A0002("A0002", "ID资源未找到"),
    A0003("A0003", "参数缺失"),
    A0004("A0004", "资源重复"),
    A0005("A0005", "父节点资源未找到"),
    A0101("A0101", "Email格式错误"),
    A0102("A0102", "Email已使用"),
    A0103("A0103", "密码格式错误，最少8位英文加数字"),
    A0104("A0104", "不被支持的文件格式"),
    A0105("A0105", "用户名已使用"),
    A0106("A0106", "验证码错误"),
    A0107("A0107", "验证码发送过于频繁"),
    A0108("A0108", "文件上传失败"),
    A0201("A0201", "不被支持的登录方式"),
    A0202("A0202", "账户未注册"),
    A0203("A0203", "密码不正确"),
    A0204("A0204", "无效的token"),
    A0205("A0205", "token已过期"),
    A0206("A0206", "无访问权限"),
    A0301("A0301", "公司名或统一社会信用代码已存在"),
    A0302("A0302", "未找到对应的公司信息"),
    A0303("A0303", "未找到对应的评论信息"),
    B0001("B0001", "字典未找到"),
    B0002("B0002", "字典组未找到"),
    B0404("B0404", "资源未找到"),
    B0500("B0500", "系统错误"),
    C0001("C0001", "微信接口调用失败"),
    C0002("C0002", "微信登录已过期"),
    C0003("C0003", "统一登录接口调用失败"),
    D0101("D0101", "系统参数配置错误:过滤规则错误"),
    ;

    final String errCode;
    final String errMsg;


    RequestError(String errorCode, String errorMsg) {
        this.errCode = errorCode;
        this.errMsg = errorMsg;
    }

    @Override
    public String getErrCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }
}
