package com.liuyu.mall.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义常用的常量
 *
 * @author liuyu
 */
public class Constants {

    /**
     * 接口url
     */
    public static Map<String,String> URL_MAPPING_MAP = new HashMap<>();

    /**
     *  获取项目根目录
     */
    public static String PROJECT_ROOT_DIRECTORY = System.getProperty("user.dir");

    /**
     * 密码加密相关
     */
    public static String SALT = "liuyu";
    public static final int HASH_ITERATIONS = 1;

    /**
     * 请求头 - token
     */
    public static final String REQUEST_HEADER = "X-Token";

    /**
     * 请求头类型：
     * application/x-www-form-urlencoded ： form表单格式
     * application/json ： json格式
     */
    public static final String REQUEST_HEADERS_CONTENT_TYPE = "application/json";

    /**
     * 登录者角色
     */
    public static final String ROLE_LOGIN = "login";

    /**
     * 获取UUID
     * @return String-UUID
     * */
    public static String createUuid(){
        return java.util.UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
