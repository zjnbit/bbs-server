package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.conf.AliyunConf;
import com.zjnbit.bbs.api.model.conf.AuthConf;
import com.zjnbit.bbs.api.model.conf.BbsConf;

public interface BaseConfService {

    BbsConf initBbsConf();

    BbsConf getBbsConf();
}
