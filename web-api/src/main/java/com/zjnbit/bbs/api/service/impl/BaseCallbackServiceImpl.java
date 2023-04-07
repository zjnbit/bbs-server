package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zjnbit.bbs.api.framework.template.AliyunOssTemplate;
import com.zjnbit.bbs.api.mapper.BbsAttachMapper;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssDto;
import com.zjnbit.bbs.api.model.entity.BbsAttachEntity;
import com.zjnbit.bbs.api.model.vo.BbsAttachVo;
import com.zjnbit.bbs.api.service.BaseCallbackService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Service
public class BaseCallbackServiceImpl implements BaseCallbackService {

    @Autowired
    AliyunOssTemplate aliyunOssTemplate;
    @Autowired
    BbsAttachMapper bbsAttachMapper;

    @Override
    public BbsAttachVo ossAliyun() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        BaseAliyunOssDto aliyunOssDto = aliyunOssTemplate.callback(request);
        if (null != aliyunOssDto) {
            BbsAttachEntity bbsAttachEntity = BeanUtil.copyProperties(aliyunOssDto, BbsAttachEntity.class);
            bbsAttachMapper.insert(bbsAttachEntity);
            return BeanUtil.copyProperties(bbsAttachEntity, BbsAttachVo.class);
        } else {
            throw new RequestException(RequestError.A0108);
        }

    }
}
