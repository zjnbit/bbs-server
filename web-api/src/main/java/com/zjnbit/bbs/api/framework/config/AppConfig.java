package com.zjnbit.bbs.api.framework.config;

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
    public void initBbsConf() {
        baseConfService.initBbsConf();
        log.info("加载论坛配置完成");
    }

    public BbsConf getBbsConf() {
        return baseConfService.getBbsConf();
    }
}
