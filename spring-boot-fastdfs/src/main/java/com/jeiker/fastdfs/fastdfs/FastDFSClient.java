package com.jeiker.fastdfs.fastdfs;

import com.jeiker.fastdfs.model.FastDFSFile;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

public class FastDFSClient {

    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);

    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;

    private static StorageClient storageClient;
    private static StorageServer storageServer;

    static {
        try {
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            ClientGlobal.init(filePath);

            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!", e);
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    public static String[] upload(FastDFSFile file) {
        logger.info("File Name: {}, File Length: {}", file.getName(), file.getContent().length);

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file: {}, error: {}", file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploading the file: {}, error: {}", file.getName(), e);
        }
        logger.info("upload_file time used: {} ms" , (System.currentTimeMillis() - startTime) );

        if (uploadResults == null) {
            logger.error("upload file fail, error code: {}", storageClient.getErrorCode());
        }
        String groupName = uploadResults[0];
        String remoteFileName = uploadResults[1];

        logger.info("upload file successfully!!! group_name: {}, remoteFileName: {}", groupName, remoteFileName);
        return uploadResults;
    }

    public static String getTrackerUrl() {
        return "http://" + trackerServer.getInetSocketAddress().getHostString() + ":" + ClientGlobal.getG_tracker_http_port() + "/";
    }

}
