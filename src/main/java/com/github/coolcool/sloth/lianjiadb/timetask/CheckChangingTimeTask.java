package com.github.coolcool.sloth.lianjiadb.timetask;

import com.github.coolcool.sloth.lianjiadb.common.MyHttpClient;
import com.github.coolcool.sloth.lianjiadb.service.ProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.TimerTask;


@EnableScheduling
@Service
public class CheckChangingTimeTask extends TimerTask {

    private static final Logger log = LoggerFactory.getLogger(CheckChangingTimeTask.class);


    @Value("${dev:false}")
    boolean dev;

    @Value("${com.github.coolcool.sloth.lianjiadb.timetask.checkchanging.hour:9}")
    int checkchangingHour;

    @Autowired
    private ProcessService processService;

    static boolean running = false;

    /**
     * 检查价格变化、下架
     */
    @Override
    @Scheduled(cron="0 0/1 * * * ?")
    public void run() {

//        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
//
//        if( hour != checkchangingHour && !dev) // 每天特定时间执行一次
//            return;

        if(MyHttpClient.available && !running){
            running = true;
            log.info("开始执行 checkChanging ...");
            try {
                processService.checkChange();
            }catch (Throwable t){
                t.printStackTrace();
            }
            running = false;
        }
    }

}
