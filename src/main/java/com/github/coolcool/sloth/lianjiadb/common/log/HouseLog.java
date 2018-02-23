package com.github.coolcool.sloth.lianjiadb.common.log;

import com.alibaba.fastjson.JSONObject;
import com.github.coolcool.sloth.lianjiadb.common.MyHttpClient;
import com.github.coolcool.sloth.lianjiadb.model.House;
import com.github.coolcool.sloth.lianjiadb.service.impl.support.LianjiaWebUtil;
import org.apache.commons.lang.StringUtils;

/**
 * Created by dee on 2016/11/7.
 */
public class HouseLog {

    private static final String SPLIT = "|";//'\u0001';

    private House house = new House();

    public HouseLog(House house) {
        this.house = house;
    }

    private String createTime;
    private String code;
    private String city;
    private String area;
    private String road;
    private String communityName;
    private double price;
    private double unitPrice;
    private double size;
    private String decoration;
    private String floorLevel;
    private int totalFloor;
    private int roomCount;
    private int hallCount;
    private int favCount;
    private int cartCount;
    private String towards;
    private int birthday;
    private String school;
    private boolean isFiveYear;
    private boolean isSubway;

    public String getCreateTime() {
        return house.getTransactionContent1();
    }

    public String getCode() {
        return house.getCode();
    }

    public String getCity() {
        return "广州";
    }

    public String getArea() {
        if (house.getAreaName() == null)
            return "";
        String areaName = house.getAreaName().trim();
        return areaName.split(" ")[0];
    }

    public String getRoad() {
        if (house.getAreaName() == null || "未知".equals(house.getAreaName()))
            return "";
        return house.getAreaName().split(" ")[1];
    }

    public String getCommunityName() {
        return house.getCommunityName();
    }

    public double getPrice() {
        if (house.getPrice() == null)
            return new Double(0);
        return house.getPrice().doubleValue();
    }

    public double getUnitPrice() {
        if (house.getUnitprice() == null)
            return new Double(0);
        return house.getUnitprice().doubleValue() / 10000;
    }

    public double getSize() {
        return house.getRoomSize();
    }

    public String getDecoration() {
        return house.getRoomSubType();
    }

    public String getFloorLevel() {

        if (house.getRoomSubInfo() == null || "未知".equals(house.getRoomSubInfo()))
            return "";
        return house.getRoomSubInfo().split("/")[0];//低楼层/共31层
    }

    public int getTotalFloor() {
        if (house.getRoomSubInfo() == null || "未知".equals(house.getRoomSubInfo()))
            return -1;
        return Integer.parseInt(house.getRoomSubInfo().split("/")[1].replace("共", "").replace("层", ""));
    }

    public int getRoomCount() {
        if (house.getRoomMainInfo() == null || "未知".equals(house.getRoomMainInfo()))
            return -1;
        if (house.getRoomMainInfo().indexOf("室")<0 || house.getRoomMainInfo().indexOf("厅")<0)
            return -1;
        return Integer.parseInt(house.getRoomMainInfo().split("室")[0]);
    }

    public int getHallCount() {
        if (house.getRoomMainInfo() == null || "未知".equals(house.getRoomMainInfo()))
            return -1;
        if (house.getRoomMainInfo().indexOf("室")<0 || house.getRoomMainInfo().indexOf("厅")<0)
            return -1;
        return Integer.parseInt(house.getRoomMainInfo().split("室")[1].split("厅")[0]);
    }

    public int getFavCount() {
        return house.getFavcount();
    }

    public int getCartCount() {
        return house.getCartcount();
    }

    public String getTowards() {
        return house.getRoomMainType();
    }

    public int getBirthday() {
        if (house.getAreaSubInfo() == null || house.getAreaSubInfo().indexOf("未知") > -1)
            return -1;
        return Integer.parseInt(house.getAreaSubInfo().split("年")[0]);
    }

    public String getSchool() {
        return house.getSchoolName();
    }

    public boolean isFiveYear() {
        if (house.getTags() == null)
            return false;
        return house.getTags().indexOf("满五年唯一") > -1;
    }

    public boolean isSubway() {
        if (house.getTags() == null)
            return false;
        return house.getTags().indexOf("地铁房") > -1;
    }

    public String toLogString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append(ha(getCreateTime())).append(SPLIT)
                .append(ha(getArea())).append(SPLIT)
                .append(getBirthday()).append(SPLIT)
                .append(getCartCount()).append(SPLIT)
                .append(ha(getCity())).append(SPLIT)
                .append(ha(getCode())).append(SPLIT)
                .append(ha(getCommunityName())).append(SPLIT)
                .append(ha(getDecoration())).append(SPLIT)
                .append(getFavCount()).append(SPLIT)
                .append(ha(getFloorLevel())).append(SPLIT)
                .append(getHallCount()).append(SPLIT)
                .append(getPrice()).append(SPLIT)
                .append(ha(getRoad())).append(SPLIT)
                .append(getRoomCount()).append(SPLIT)
                .append(ha(getSchool())).append(SPLIT)
                .append(getSize()).append(SPLIT)
                .append(getTotalFloor()).append(SPLIT)
                .append(ha(getTowards())).append(SPLIT)
                .append(getUnitPrice()).append(SPLIT)
                .append(isFiveYear()).append(SPLIT)
                .append(isSubway())
        ;
        return sb.toString();
    }

    static String ha(String temp) {
        if (temp == null)
            return "";
        else if ("null".equals(temp))
            return "";
        return temp;
    }

}
