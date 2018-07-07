package com.yf.fileserver.controller;
import com.yf.fileserver.utils.StorageClientAdapter;
import com.yf.fileserver.vo.StorageInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
/**
 * @date 2017/10/27
 */
@Slf4j
@RestController
@Api(value = "StorageController", description = "存储接口")
public class StorageController {

    @Autowired
    private StorageClientAdapter storageClientAdapter;

    @ApiOperation(value = "日期递增存储", httpMethod = "POST", notes = "获取最新的动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zone", value = "区块名称", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/dateIncrement", method = RequestMethod.POST)
    public StorageInfoVO dateIncrement(String zone, MultipartFile file) {
        StorageInfoVO storageInfoVO = storageClientAdapter.dateIncrement(zone, file);
        return storageInfoVO;
    }


    @ApiOperation(value = "日期递增存储", httpMethod = "POST", notes = "获取最新的动态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "zone", value = "区块名称", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "group", value = "分组名称", dataType = "String", paramType = "query", required = true),
    })
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public StorageInfoVO group(String zone,String group, MultipartFile file) {
        StorageInfoVO storageInfoVO = storageClientAdapter.group(zone, group, file);
        return storageInfoVO;
    }
}
