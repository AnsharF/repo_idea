package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/PromotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;


    /*
        分页查询广告信息
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVO promotionAdVO) {

        PageInfo<PromotionAd> pageInfo = promotionAdService.findAllPromotionByPage(promotionAdVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "广告分页查询成功", pageInfo);
        return responseResult;
    }

    /*
        图片上传
     */
    @RequestMapping("/PromotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {


        try {
            //1.判断接受到的上传文件是否为空
            if (file.isEmpty()) {
                throw new RuntimeException();
            }

            //2.获取项目部署路径
            String realPath = request.getServletContext().getRealPath("/");
            String substring = realPath.substring(0, realPath.indexOf("ssm_web"));

            //3.获取源文件名
            String originalFilename = file.getOriginalFilename();

            //4.生成新文件名
            String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

            //5.文件上传
            String uploadPath = substring + "upload\\";

            File filePath = new File(uploadPath, newFileName);

            //如果目录不存在就创建目录
            if (!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
                System.out.println("创建目录：" + filePath);
            }

            //图片进行了真正的上传
            file.transferTo(filePath);

            //6.将文件名和文件路径返回进行响应
            HashMap<String, String> map = new HashMap<>();
            map.put("fileName", newFileName);
            map.put("filePath", "http://localhost:8080/upload/" + newFileName);

            ResponseResult responseResult = new ResponseResult(true, 200, "图片上传成功", map);

            return responseResult;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
        修改广告状态
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(Integer id, Integer status) {

        promotionAdService.updatePromotionAdStatus(id, status);
        Map<Object, Object> map = new HashMap<>();
        map.put("status",status);
        return new ResponseResult(true,200,"修改广告状态成功",map);
    }
}
