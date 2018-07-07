package com.yf.fileserver.utils;
import com.yf.fileserver.vo.StorageInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

/**
 * 存储客户端实现
 * @date 2017/10/27
 */
@Component
@Slf4j
public class StorageClientAdapter {
    /**
     * 本地存储路径
     */
    @Value("${define.localStoragePath}")
    private String localStoragePath;

    /**
     * 资源访问地址
     */
    @Value("${define.resourceAccessUri}")
    private String resourceAccessUri;

    /**
     * 日期递增方式存储文件
     *
     * @param zone          存储区块
     * @param multipartFile 文件
     */
    public StorageInfoVO dateIncrement(String zone, MultipartFile multipartFile) {
        //生成存储信息
        StorageInfoVO storageInfoVO = dateIncrementInfo(zone, multipartFile.getOriginalFilename(), multipartFile.getSize());
        //将文件存储到本地
        File storageFile = new File(localStoragePath + "/" + storageInfoVO.getStorageUri());
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(storageFile);
        } catch (Exception e) {
            log.info("上传异常:["+e+"]");
        }
        storageInfoVO.setAccessUri(resourceAccessUri + storageInfoVO.getStorageUri());
        return storageInfoVO;
    }

    /**
     * 分组存储文件
     *
     * @param zone          存储区块
     * @param group         组名称
     * @param multipartFile 文件
     * @return
     */
    public StorageInfoVO group(String zone, String group, MultipartFile multipartFile) {
        //生成存储信息
        StorageInfoVO storageInfoVO = groupInfo(zone, group, multipartFile.getOriginalFilename(), multipartFile.getSize());
        //将文件存储到本地
        File storageFile = new File(localStoragePath + "/" + storageInfoVO.getStorageUri());
        if (!storageFile.getParentFile().exists()) {
            storageFile.getParentFile().mkdirs();
        }
        try {
            multipartFile.transferTo(storageFile);
        } catch (Exception e) {
           log.info("上传异常:["+e+"]");
        }
        storageInfoVO.setAccessUri(resourceAccessUri + storageInfoVO.getStorageUri());
        return storageInfoVO;
    }


    public StorageInfoVO groupInfo(String zone, String group, String formerlyName, Long resourceSize) {
        log.info(zone + " / " + group + "/" + formerlyName + " / " + resourceSize);

        String id = UUID.randomUUID().toString().replace("-", "");
        String suffix = formerlyName.substring(formerlyName.lastIndexOf("."));
        String uri = zone + "/" + group + "/" + id + suffix;

        StorageInfoVO storageInfoVO = new StorageInfoVO();
        storageInfoVO.setId(id);
        storageInfoVO.setFormerlyName(formerlyName);
        storageInfoVO.setResourceSize(resourceSize);
        storageInfoVO.setStorageName(storageInfoVO.getId());
        storageInfoVO.setStorageUri(uri);
        log.info(storageInfoVO.toString());

        //TODO 持久化StorageInfoVO

        return storageInfoVO;
    }



    public StorageInfoVO dateIncrementInfo(String zone, String formerlyName, Long resourceSize) {
        log.info(zone + " / " + formerlyName + " / " + resourceSize);

        String id = UUID.randomUUID().toString().replace("-", "");
        String suffix = formerlyName.substring(formerlyName.lastIndexOf("."));
        String date = DateUtils.getDate().replace("-", "");
        String uri = zone + "/" + date + "/" + id + suffix;

        StorageInfoVO storageInfoVO = new StorageInfoVO();
        storageInfoVO.setId(id);
        storageInfoVO.setFormerlyName(formerlyName);
        storageInfoVO.setResourceSize(resourceSize);
        storageInfoVO.setStorageName(storageInfoVO.getId());
        storageInfoVO.setStorageUri(uri);
        log.info(storageInfoVO.toString());

        //TODO 持久化StorageInfoVO

        return storageInfoVO;
    }

}
