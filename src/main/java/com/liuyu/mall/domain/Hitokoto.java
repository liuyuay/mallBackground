package com.liuyu.mall.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author liuyu
 * 一言  实体类
 */
@Component
@TableName("hitokoto")
public class Hitokoto implements Serializable {

    private String id;

    private String hitokoto;

    private String froms;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHitokoto() {
        return hitokoto;
    }

    public void setHitokoto(String hitokoto) {
        this.hitokoto = hitokoto;
    }

    public String getFroms() {
        return froms;
    }

    public void setFroms(String froms) {
        this.froms = froms;
    }
}
