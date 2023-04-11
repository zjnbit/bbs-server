package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.zjnbit.bbs.api.framework.template.AliyunOssTemplate;
import com.zjnbit.bbs.api.mapper.BbsAttachMapper;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssCallbackDto;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssDto;
import com.zjnbit.bbs.api.model.entity.BbsAttachEntity;
import com.zjnbit.bbs.api.model.vo.BbsAttachVo;
import com.zjnbit.bbs.api.service.BaseCallbackService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class BaseCallbackServiceImpl implements BaseCallbackService {

    @Autowired
    AliyunOssTemplate aliyunOssTemplate;
    @Autowired
    BbsAttachMapper bbsAttachMapper;

    @Override
    public BbsAttachVo ossAliyun(BaseAliyunOssCallbackDto data, HttpServletResponse response) {
        BaseAliyunOssDto aliyunOssDto = aliyunOssTemplate.callback(data);
        if (null != aliyunOssDto) {
            BbsAttachEntity bbsAttachEntity = BeanUtil.copyProperties(aliyunOssDto, BbsAttachEntity.class);
            bbsAttachMapper.insert(bbsAttachEntity);
            return BeanUtil.copyProperties(bbsAttachEntity, BbsAttachVo.class);
        } else {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            return null;
        }

    }
}
