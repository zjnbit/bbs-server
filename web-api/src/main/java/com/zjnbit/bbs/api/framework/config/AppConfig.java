package com.zjnbit.bbs.api.framework.config;

import com.zjnbit.bbs.api.model.conf.AliyunConf;
import com.zjnbit.bbs.api.model.conf.AuthConf;
import com.zjnbit.bbs.api.model.conf.BbsConf;
import com.zjnbit.bbs.api.service.BaseConfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;


@Service
@Slf4j
public class AppConfig {
    @Autowired
    BaseConfService baseConfService;

    @PostConstruct
    public void initAliyunConf() {
        baseConfService.initAliyunConf();
        log.info("加载Aliyun配置完成");
    }

    public AliyunConf getAliyunConf() {
        return baseConfService.getAliyunConf();
    }

    @PostConstruct
    public void initAuthConf() {
        baseConfService.initAuthConf();
        System.setProperty("https.protocols", "TLSv1.2,TLSv1.1,SSLv3");
        log.info("加载权限配置完成");
    }

    public AuthConf getAuthConf() {
        return baseConfService.getAuthConf();
    }

    @PostConstruct
    public void initBbsConf() {
        baseConfService.initBbsConf();
        log.info("加载论坛配置完成");
    }

    public BbsConf getBbsConf() {
        return baseConfService.getBbsConf();
    }
}
