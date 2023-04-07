package com.zjnbit.framework.db.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zjnbit.framework.web.util.UserUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MyBatisPlusMetaHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Long userId = UserUtils.getCurrentUserId();
        this.setFieldValByName("createUser", userId, metaObject);
        this.setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("updateUser", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Long userId = UserUtils.getCurrentUserId();
        this.setFieldValByName("updateUser", userId, metaObject);
        this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
