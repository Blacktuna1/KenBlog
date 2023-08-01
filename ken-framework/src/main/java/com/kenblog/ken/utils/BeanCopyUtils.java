package com.kenblog.ken.utils;

import com.kenblog.ken.domain.Article;
import com.kenblog.ken.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    private BeanCopyUtils() {
    }

    // 单个对象的拷贝
    public static <V> V copyBean(Object source,Class<V> clazz) {
        //创建目标对象
        V result = null;
        try {
            result = clazz.newInstance();
            //实现属性copy
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    //对集合对象的拷贝
    public static <O,V> List<V> copyBeanList(List<O> list, Class<V> clazz){
        //使用stream流
        return list.stream()
                .map(o -> copyBean(o, clazz))
                .collect(Collectors.toList());
    }
}
