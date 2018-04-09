package com.github.coolcool.sloth.lianjiadb.service.impl.support;

import com.alibaba.fastjson.JSONObject;
import com.github.coolcool.sloth.lianjiadb.common.MyHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dee on 2017/6/19.
 */
public class LianjiaWebUtilTest {

    static Logger logger = LoggerFactory.getLogger(LianjiaWebUtilTest.class);

    @Test
    public void testPattern() throws IOException {
        Assert.assertNotEquals(0,LianjiaWebUtil.fetchAreaTotalPageNo("chebei"));
    }

    @Test
    public void testProxy() throws IOException {
        String houseUrl = "https://gz.lianjia.com/ershoufang/GZ0002802464.html";
        MyHttpClient.HttpProxyConfig httpProxyConfig = new MyHttpClient.HttpProxyConfig("",80,"","");
        String houseHtml = MyHttpClient.get(houseUrl,httpProxyConfig);
        logger.info(""+LianjiaWebUtil.getGzListPageTotalCount(houseHtml));
    }

    @Test
//    {"areaMainInfo":"64.5平米","areaName":"  ","areaSubInfo":"1993年建/塔楼","cartcount":0,"code":"GZ0002180546","communityName":"","decoratingDesc":"装修保养好 业主自住 有重新装修过 小区环境好 临近江边 位置远离马路 小区成熟","favcount":0,"firstPayPrice":30,"price":98,"roomMainInfo":"2室2厅","roomMainType":"西南","roomSize":64.5,"roomSubInfo":"低楼层/共9层","roomSubType":"其他","sellingPointDesc":"装修保养好 位置安静 靠近江边","subtitle":"装修保养好 位置安静 靠近江边","supportingDesc":"渔人码头，超市，菜市场，电影院，银行：中国银行，工商银行，建设银行，浦发银行，民生银行","taxPrice":13.8,"title":"丽江花园丽影楼 2室2厅 98万","trafficDesc":"双地铁沿线，地铁2号线，地铁3号线，地铁5号接驳线，公交：122.125.79.305","unitprice":15194,"url":"https://gz.lianjia.com/ershoufang/GZ0002180546.html","villageDesc":"丽江花园德字楼，地铁2、3号线，公交122.125.79路洛溪大桥、新光大桥、东沙大桥、东新高速、广珠轻轨、地铁2号线延长线、地铁七号线等多种交通网络，15分钟可到达芳村区、20分钟可达荔湾区、30分钟可达海珠，半小时内畅达广州各区域。"}
    public void testFetchAndGenHouseObject() throws IOException {
        String houseUrl = "https://gz.lianjia.com/ershoufang/GZ0003645531.html";
        logger.info(JSONObject.toJSONString(LianjiaWebUtil.fetchAndGenHouseObject(houseUrl)));
    }

    @Test
    public void testFetchPrice() throws IOException {
        String houseUrl = "https://gz.lianjia.com/ershoufang/GZ0001682872.html";
        Assert.assertEquals(98,LianjiaWebUtil.fetchPrice(houseUrl).longValue());

    }

    @Test
    public void testFetchHouseHtml() throws IOException {
        String houseUrl = "https://gz.lianjia.com/ershoufang/GZ0001682872.html";
        String houseHtml = LianjiaWebUtil.fetchHouseHtml(houseUrl);
        logger.info(houseHtml);

    }


    @Test
    public void testFechAreaChenjiaoHouseUrls() throws IOException {
        String area1 = "tianhe";
        int pageNo = 1;
        Set<String> urls = LianjiaWebUtil.fetchAreaChenjiaoHouseUrls(area1,pageNo);
        Iterator<String> i = urls.iterator();
        while (i.hasNext()){
            logger.info(i.next());
        }

    }


    @Test
    public void testFetchAreaHouseUrls() throws IOException {
        String area1 = "tianhe";
        int pageNo = 1;
        Set<String> urls = LianjiaWebUtil.fetchAreaHouseUrls(area1,pageNo);
        Iterator<String> i = urls.iterator();
        while (i.hasNext()){
            logger.info(i.next());
        }

    }


    @Test
    public void testFetchAreaChenjiaoTotalPageNo() throws IOException {
        String area1 = "tianhe";
        logger.info(""+LianjiaWebUtil.fetchAreaChenjiaoTotalPageNo(area1));
    }

    @Test
    public void testFetchAreaTotalPageNo() throws IOException {
        String area1 = "tianhe";
        logger.info(""+LianjiaWebUtil.fetchAreaTotalPageNo(area1));
    }

    @Test
    public void testGetCityAreaIndexUrl(){
        String city1 = "gz";
        String area1 = "tianhe";
        Assert.assertEquals("https://gz.lianjia.com/ershoufang/tianhe/", LianjiaWebUtil.getCityAreaIndexUrl(city1,area1));
    }

    @Test
    public void testGetCityIndexUrl(){
        String city1 = "gz";
        String city2 = "sh";
        String city3 = "bj";
        Assert.assertEquals("https://gz.lianjia.com/ershoufang/", LianjiaWebUtil.getCityIndexUrl(city1));
        Assert.assertEquals("https://sh.lianjia.com/ershoufang/", LianjiaWebUtil.getCityIndexUrl(city2));
        Assert.assertEquals("https://bj.lianjia.com/ershoufang/", LianjiaWebUtil.getCityIndexUrl(city3));
    }



}
