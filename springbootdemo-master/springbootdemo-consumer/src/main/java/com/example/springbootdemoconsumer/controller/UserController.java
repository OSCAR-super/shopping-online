package com.example.springbootdemoconsumer.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springbootdemoconsumer.service.UserService;
import com.example.springbootdemoentity.entity.*;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    public class SystemStaticService {
        public static final String SOLT="wdnmd";

    }

    @RequestMapping(value = "/a")
    public String a(){
        return "aaa";
    }

    @RequestMapping(value = "/UserLogin")
    public String UserLogin(String UserLoginName,String UserPassword,String Code,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        String CodeE= String.valueOf(request.getSession().getAttribute("EmailCode"));
        System.out.println("卖家开始登录了");
        User user = userService.getUser();
        String userName=user.getSellerLoginName();
        String userPassword=user.getSellerPassword();
        System.out.println("验证码:"+Code+CodeE);
        if (CodeE.equalsIgnoreCase(Code)){
            System.out.println("验证码正确");
            String password = DigestUtils.md5Hex(UserPassword);
            //此处加密后加盐再进行加密
            String psd=DigestUtils.md5Hex(password+SystemStaticService.SOLT);

            System.out.println("密碼："+psd);
        if (userName.equals(UserLoginName)&&userPassword.equals(psd)){
            JSONObject result = new JSONObject();
            result.put("卖家姓名",user.getSellerName());
            result.put("卖家电话",user.getSellerPhone());
            result.put("卖家地址",user.getSellerAddress());
            result.put("卖家商店",user.getSellerStore());
            result.put("卖家头像",user.getSellerImage());

            return result.toString();
        }else {
            return "用户名或密码错误";
        }

        }else {
            return "验证码错误";
        }


    }

    @RequestMapping(value = "/getUser")
    public String getUser() {
        System.out.println("调用getUser方法");
        User user = userService.getUser();
        System.out.println(user.toString());
        return user.toString();
    }

    @RequestMapping(value = "/showAllProduct")
    public String showAllProduct(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        List<Product> product=userService.findAllProduct();
        for (Product p:product){
            List<image> img=userService.findProductImage(String.valueOf(p.getProductId()));
            for(image i:img){
                if (i.getImageState().equals("main")){
                p.setProductState(i.getImageName());
                }
            }
        }
        JSONObject result = new JSONObject();
        result.put("所有货物",product);
        return result.toString();
    }

    @RequestMapping(value = "/showOneProduct")
    public String showOneProduct(String ProductName,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        Product product=userService.findProductOne(ProductName);
        List<image> img=userService.findProductImage(String.valueOf(product.getProductId()));
        for(image i:img){
            if (i.getImageState().equals("main")){
            product.setProductState(i.getImageName());
            }
        }
        JSONObject result = new JSONObject();
        result.put("这个货物",product);
        return result.toString();
    }
    @RequestMapping(value = "/showProductUniqueness")
    public String showProductUniqueness(String ProductName,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        Product product=userService.findProductOne(ProductName);

        JSONObject result = new JSONObject();
        if (product==null){
        result.put("唯一性","唯一");
        }else {
            result.put("唯一性","不唯一");
        }
        return result.toString();
    }

    @RequestMapping(value = "/showProductImage")
    public String showProductImage(String ProductId,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        List<image> img=userService.findProductImage(ProductId);
        JSONObject result = new JSONObject();
        result.put("对应货物的图片",img);
        return result.toString();
    }

    @RequestMapping(value = "/addProduct")
    public String addProduct(String ProductName,String ProductSurplus,String ProductDescription,String ProductTag,String ProductBrand,String ProductUnit,String ProductPrice,String ProductPlace,String ProductDdl,Boolean ProductChange,String ProductLevel,String ProductContent,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        userService.addProduct(ProductName,ProductSurplus,ProductDescription,ProductPrice,ProductTag,ProductBrand,ProductUnit,ProductPlace,ProductDdl,ProductChange,ProductLevel,ProductContent);
        Product product=userService.findProductOne(ProductName);
        JSONObject result = new JSONObject();
        result.put("货物参数",product);

        return result.toString();
    }

    @RequestMapping(value = "/soldoutProduct")
    public String soldoutProduct(String ProductId,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        userService.soldoutProduct(ProductId);
        return "下架完成";
    }

    @RequestMapping(value = "/IncreaseProduct")
    public String IncreaseProduct(String ProductId,String ProductSurplus,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        userService.IncreaseProduct(ProductId,ProductSurplus);
        return "数量变成了:"+ProductSurplus;
    }
    @RequestMapping(value = "/changePriceProduct")
    public String changePriceProduct(String ProductId,String ProductPrice,HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        userService.changePriceProduct(ProductId,ProductPrice);
        return "价格变成了:"+ProductPrice;
    }
    @CrossOrigin
    @RequestMapping(value = "showEInvoice")
    public String showEInvoice(String FileName,HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);


        String face=FileName;
        FileInputStream fis = null;
        OutputStream os = null;
        System.out.println(face);
        String filepath = "C:\\upload\\"+face;     //path是你服务器上图片的绝对路径
        File file = new File(filepath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/jpg");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "显示完成";
    }
    @RequestMapping(value = "/t")
    public String t(HttpServletRequest request,HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        List<String>i=userService.t();
        JSONObject result = new JSONObject();
        result.put("t",i);
        return result.toString();
    }
    @CrossOrigin
    @RequestMapping(value = "showEInvoice1")
    public String showEInvoice1(String FileName,HttpServletRequest request,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);


        String face=FileName;
        FileInputStream fis = null;
        OutputStream os = null;
        System.out.println(face);
        String filepath = "C:\\txl\\"+face;     //path是你服务器上图片的绝对路径
        File file = new File(filepath);
        if(file.exists()){
            try {
                fis = new FileInputStream(file);
                long size = file.length();
                byte[] temp = new byte[(int) size];
                fis.read(temp, 0, (int) size);
                fis.close();
                byte[] data = temp;
                response.setContentType("image/jpg");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "显示完成";
    }

    @CrossOrigin
    @RequestMapping(value = "/UpLoadFile")
    public String UpLoadFile(String imageState,String ProductId,MultipartFile files, HttpServletRequest request, HttpServletResponse response){
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String path="C:\\upload";


        System.out.println(files.getSize());
        InputStream inputStream = null;
        File file = null;
        try {
            file = File.createTempFile("temp", null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            files.transferTo(file);   //sourceFile为传入的MultipartFile
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();

        OutputStream os = null;

        String fileName = null;

        fileName = files.getOriginalFilename();


        try {

            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len=1024;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);

            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
            // 开始读取

            int i=0;

            while (true) {
                len = inputStream.read(bs) ;
                System.out.println(len);
                if (len==-1){
                    break;
                }
                i++;
                System.out.println(i);
                os.write(bs, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                os.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("news", fileName+"上传成功,谢谢分享");

        String UserFace=fileName;
        System.out.println(fileName);
        userService.addImage(ProductId,fileName,imageState);
        return "上传完成";
    }

    @CrossOrigin
    @RequestMapping(value = "downFile")
    public void downFile( String fileName,HttpServletResponse response, HttpServletRequest request)throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String origin = request.getHeader("Origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        String downloadFilePath = "C:\\upload\\HomeWork\\"+fileName;

                File file = new File(downloadFilePath);
        System.out.println(file.exists()+"111222");
                if (file.exists()) {
                    response.setContentType("text/html; charset=UTF-8");
                    response.setContentType("application/force-download");// 设置强制下载不打开
                    System.out.println(fileName);
                    fileName = new String(fileName.getBytes("UTF-8"),"iso-8859-1");
                    response.addHeader("Content-Disposition", "attachment;fileName="+fileName);
                    byte[] buffer = new byte[1024];
                    FileInputStream fis = null;
                    BufferedInputStream bis = null;
                    try {
                        System.out.println("開始下載");
                        fis = new FileInputStream(file);
                            bis = new BufferedInputStream(fis);
                            OutputStream outputStream = response.getOutputStream();
                            int i = bis.read(buffer);
                            while (i != -1) {
                            outputStream.write(buffer, 0, i);
                            i = bis.read(buffer);

                        }

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                    if (bis != null) {
                                try {
                                    bis.close();
                            } catch (IOException e) {
                                    e.printStackTrace();
                                }
                        }
                        if (fis != null) {
                            try {
                            fis.close();
                        } catch (IOException e) {
                                    e.printStackTrace();
                            }
                        }
                    }
                }

    }

}

