package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.dto.BaseAliyunOssCallbackDto;
import com.zjnbit.bbs.api.model.vo.BbsAttachVo;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface BaseCallbackService {


    BbsAttachVo ossAliyun(BaseAliyunOssCallbackDto data, HttpServletResponse response);
}
