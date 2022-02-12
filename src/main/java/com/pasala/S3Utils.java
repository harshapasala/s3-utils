package com.pasala;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.util.List;

public class S3Utils {
    public void upload(String bucket, String filePath) {
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        String key = FilenameUtils.getName(filePath);
        s3.putObject(bucket, key, new File(filePath));


    }

    public List<S3ObjectSummary> listFiles(String bucket) {
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        return s3.listObjects(bucket).getObjectSummaries();
    }

    public S3Object download(String bucket, String key){
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        return s3.getObject(new GetObjectRequest(bucket, key));
    }

}
