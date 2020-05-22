package com.bazn.vip.multi;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceType> contextHolder = new ThreadLocal<>();

    public static void setContextHolder(DataSourceType type){
        contextHolder.set(type);
    }

    // 获取
    public static DataSourceType getContextHolder(){
        return contextHolder.get();
    }

    // 清除
    public static void clearContextHolder(){
        contextHolder.remove();
    }
}
