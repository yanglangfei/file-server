package com.yf.fileserver.controller;

import com.yf.fileserver.utils.FastDFSClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@Api(value = "FileController", description = "文件接口")
public class FileController {
    @Autowired
    private FastDFSClient dfsClient;

    // 上传图片
    @ApiOperation(value = "上传文件", httpMethod = "POST", notes = "上传文件")
    @ApiImplicitParams({
    })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 省略业务逻辑代码。。。
        String imgUrl = dfsClient.uploadFile(file);
        return imgUrl;
    }
}
