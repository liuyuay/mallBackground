package com.liuyu.mall.utils;

import com.liuyu.mall.utils.QrCode.QRCodeUtil;

public class CreateQrCode {
    public static void main(String [] args) throws Exception{
        // 存放在二维码中的内容
//        String text = "爱生活，爱万万！\n爱生活，爱万万！\n爱生活，爱万万！\n重要的事情说三遍，哈哈哈！\n\n      ---By LiuYu";
        String text = "大哥，下午好呀！";
        // 嵌入二维码的图片路径
        String imgPath = "E:/QrCode/dage.jpg";
        // 生成的二维码的路径及名称
        String destPath = "E:/QrCode/dage111.jpg";
        //生成二维码
        QRCodeUtil.encode(text, imgPath, destPath, true);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }
}
