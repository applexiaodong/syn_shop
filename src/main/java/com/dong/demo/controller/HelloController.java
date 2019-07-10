package com.dong.demo.controller;


import com.dong.demo.domain.Boy;
import com.dong.demo.domain.ResultMessage;
import com.dong.demo.util.JwtUtil;
import com.dong.demo.util.OrderInfoTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class HelloController {

    @Autowired
    private Boy boy;

    @RequestMapping(value = "/hello/{id}",method = RequestMethod.GET)
    public String hello(@PathVariable(name = "id",required = true) Integer id){

        return "亲爱的"+boy.getName()+"，今年"+boy.getAge()+"岁，性别:"+boy.getSex()+",编号："+id+",I am Happy";
    }

    @RequestMapping(value = "/look",method = RequestMethod.GET)
    public String say(@RequestParam(name = "id",required = false,defaultValue = "100") Integer id){

        return "亲爱的"+boy.getName()+"，今年"+boy.getAge()+"岁，性别:"+boy.getSex()+",编号："+id.toString();
    }

    @PostMapping("/uploadSingle")
    public ResultMessage uploadImage(@RequestParam(value = "file",required = false) MultipartFile file){
        ResultMessage resultMessage = new ResultMessage();
        if (file == null){
            resultMessage.setMsg("fail");
            return resultMessage;
        }else{
            //后缀名
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String fileName = UUID.randomUUID()+suffix;
            String filePath = "H:\\images";
            String newUrl = filePath+"\\"+fileName;

            File saveFile = new File(newUrl);
            if(!saveFile.getParentFile().exists()){
                saveFile.getParentFile().mkdirs();
            }
            try {
                file.transferTo(saveFile);
            } catch (IOException e) {
                e.printStackTrace();
                resultMessage.setMsg("上传失败！");
                return resultMessage;
            }
            resultMessage.setMsg("ok");
            return resultMessage;
        }

    }

    @PostMapping("/access_token")
    public Map createAccessToken(@RequestBody Map<String,Object> map){
        Map<String,Object> data = new HashMap<>();
        String userId = (String)map.get("user_id");
        String token = JwtUtil.createToken(userId);
        data.put("access_token",token);
        data.put("expire_in",24*60*60*1000);
        return data;
    }

    @Autowired
    OrderInfoTask infoTask;

    @GetMapping("/getAddress")
    public BigDecimal getProvinceName(@RequestParam(defaultValue = "12") String addressCode){
        return infoTask.transferCityAddress(addressCode,"915");
    }

}
