package com.zjnbit.bbs.api.api.base;

import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import com.zjnbit.framework.web.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Slf4j
public class ErrorApi implements ErrorController {

    /**
     * 错误处理
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/error")
    public Result<String> error(HttpServletRequest request, HttpServletResponse response) {
        if (response.getStatus() == 404) {
            throw new RequestException(RequestError.B0404);
        }
        throw new RequestException(RequestError.B0500);
    }

}
