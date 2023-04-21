package com.zjnbit.bbs.api.framework.template;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.StatObjectArgs;
import io.minio.StatObjectResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/19 14:28
 * @Description
 **/
public class MinioTemplate {
    private MinioClient minioClient;
    private String bucket;

    public MinioTemplate(MinioClient minioClient, String bucket) {
        this.minioClient = minioClient;
        this.bucket = bucket;

    }

    public Boolean upload( String fileName, MultipartFile file) {
        try {
            PutObjectArgs objectArgs = PutObjectArgs.builder().bucket(bucket).object(fileName)
                    .stream(file.getInputStream(),file.getSize(),-1).contentType(file.getContentType()).build();
            //文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public StatObjectResponse getObjectInfo(String filePat){
        try {
            StatObjectResponse objectStat =
                    minioClient.statObject(
                            StatObjectArgs.builder().bucket(bucket).object(filePat).build());
            return objectStat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
