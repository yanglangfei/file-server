package com.yf.fileserver.vo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @date 2017/10/27
 */
@Data
@ToString
public class StorageInfoVO implements Serializable {
    /**
     * 资源唯一标识
     */
    private String id;
    /**
     * 存储地址
     */
    private String storageUri;
    /**
     * 存储名称
     */
    private String storageName;
    /**
     * 原名称
     */
    private String formerlyName;
    /**
     * 资源大小
     */
    private Long resourceSize;
    /**
     * 访问地址
     */
    private String accessUri;
}
