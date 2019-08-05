package com.liujin.service;

public interface CityService {
    /**
     * 获取省份信息
     * @return
     */
    public String getProvinceJson();

    /**
     * 获取城市信息
     * @return
     */
    public String getCityJson(String pid);
}
