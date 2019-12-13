package com.liuyu.mall.utils;

import com.liuyu.mall.utils.QrCode.QRCodeUtil;

public class CreateQrCode {

    public static void main(String [] args){
        String text = "大哥，下午好呀！";
        String imgPath = "E:/QrCode/dage.jpg";
        String destPath = "E:/QrCode/dage111.jpg";
        Create("","","");
    }

    /**
     * @Param text      存放在二维码中的内容
     * @Param imgPath   嵌套在二维码中间的图片
     * @Param destPath  生成二维码的路径
     * */
    public static void Create(String text, String imgPath, String destPath){
        String str = "";
        try {
            /**
             * 浏览器调用throws的方法，有异常好像后台不会报错
             * 如果用throws抛出异常，那么调用这个类，还要处理异常，如果这样写，调用这个类就不用再处理异常
             * */
            //生成二维码
            QRCodeUtil.encode(text, imgPath, destPath, true);
            // 解析二维码
            str = QRCodeUtil.decode(destPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 打印出解析出的内容
        System.out.println(str);
    }
}
