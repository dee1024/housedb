package com.github.coolcool.sloth.lianjiadb;

import com.alibaba.fastjson.JSONObject;
import com.github.coolcool.sloth.lianjiadb.common.MyHttpClient;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.http.client.utils.HttpClientUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

/**
 * Created by dee on 2016/10/30.
 */
public class Test {

    public static  String[] urls = {"http://wx.qlogo.cn/mmopen/qzhvEOMacLmVTbOIBUuWdfwxg0MrGP8NC4cJbjShWT9jouu1KoQ9Fx2IqpAicOic7CDG7fiaEaNNM9tE6N2w81QADGXicFo7Sb5y/0",
            "http://wx.qlogo.cn/mmopen/B2EfAOZfS1jO7gQXfVvKJ7XE2O8XoKiaPWLNVnfgiaH7nqicOh093liaeHFH2Uicm3EJAfCT00WKRuSTokvkeqsBE8Q/0",
            "http://wx.qlogo.cn/mmopen/KaViaaabXicnyaMGET8zGkv62g6XQC2NKruvd3wT9Po4qm9HX5w0vgQlgKicR0F8In9ku7H0l4of4eXq6K0wfKg9y0fQdc4m2p8/0",
            "http://wx.qlogo.cn/mmopen/qzhvEOMacLl44oB6HfPGPUGXribXzdOjuZoBqXVdIcFyfXP7567pO2M1GkdapK2XplrYia5DTjqI9RhIlgScdEVg3NUPiaCodmN/0",
            "http://wx.qlogo.cn/mmopen/PiajxSqBRaEJrBiaXLGvUU8KhgFUPmWntj5icoSyLRmbMuVNU8uOBoqW5pkrEgNZfOV6kiaDYaI3xcIibmGwlvwhLdw/0",
            "http://wx.qlogo.cn/mmopen/Q3auHgzwzM4Ud1QWTvMSRgJXia0gCyeg1Xe0H8SN2JGqiaw6E9iazFSibbXUs4sJiaxITGEXaBqm9JroRCRf11S988A/0",
            "http://wx.qlogo.cn/mmopen/lRyD3gib37XC6BPMGD33b8wulcOYJPic1nemZqlDH6jMniarTfLvTLHBt1kkGcDsvYLI1aq2t0OAOlcu9Q3cNjmwL0MwGmpiat7e/0"};


    public static void main(String[] args) throws Exception {

        final long begin = System.currentTimeMillis();

        final CountDownLatch latch=new CountDownLatch(urls.length);

        for (final String url : urls){

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        MyHttpClient.download(url, "/Users/dee/Downloads/"+(RandomUtils.nextInt(1000))+".png");
                        System.out.println(Thread.currentThread().getId()+":"+ url);
                        latch.countDown();
                        if(latch.getCount()<=0){
                            long end = System.currentTimeMillis();
                            System.out.println((end-begin)+"ms");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }
    }

}
