package com.github.coolcool.sloth.lianjiadb.timetask;

import com.github.coolcool.sloth.lianjiadb.common.MyHttpClient;
import com.github.coolcool.sloth.lianjiadb.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.TimerTask;


@EnableScheduling
@Service
public class FetchHouseIndexTimeTask extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(GenAndExeDailyProcessTimeTask.class);

    @Autowired
    private ProcessService processService;


    static boolean houseDetailFetching = false;


    /**
     * 根据 HouseIndex表,获取新录入的房源信息
     */
    //@Override
    //@Scheduled(cron="1 0/1 * * * ?")   //每分钟执行一次
    public void run() {
        if(MyHttpClient.available && !houseDetailFetching){
            houseDetailFetching = true;
            log.info("开始执行houseDetailFetching...");
            try {
                //processService.fetchHouseDetail();
            }catch (Throwable t){
                t.printStackTrace();
            }
            houseDetailFetching = false;
        }
    }
    
}
