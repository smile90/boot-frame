package com.frame.boot.frame.security.dataSource;

public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceTypeEnum> contextHolder = new ThreadLocal<>();

    /**
     * 设置数据源
     * @param dbTypeEnum
     */
    public static void setDataSourceType(DataSourceTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static DataSourceTypeEnum getDataSourceType() {
        return contextHolder.get() == null ? DataSourceTypeEnum.PRIMARY : contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clearDbType() {
        contextHolder.remove();
    }
}
