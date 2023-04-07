package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.conf.AliyunConf;
import com.zjnbit.bbs.api.model.conf.AuthConf;
import com.zjnbit.bbs.api.model.conf.BbsConf;

public interface BaseConfService {
    AliyunConf initAliyunConf();

    AliyunConf getAliyunConf();

    AuthConf initAuthConf();

    AuthConf getAuthConf();

    BbsConf initBbsConf();

    BbsConf getBbsConf();
}
