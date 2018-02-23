package com.github.coolcool.sloth.lianjiadb.timetask;

import com.github.coolcool.sloth.lianjiadb.common.log.HouseLog;
import com.github.coolcool.sloth.lianjiadb.common.log.LogstashUtil;
import com.github.coolcool.sloth.lianjiadb.model.House;
import com.github.coolcool.sloth.lianjiadb.model.Houseindex;
import com.github.coolcool.sloth.lianjiadb.service.HouseService;
import com.github.coolcool.sloth.lianjiadb.service.HouseindexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TimerTask;

/**
 * Created by dee on 2016/11/17.
 */
@EnableScheduling
@Service
public class GenLogTimeTask extends TimerTask {

    private static final Logger logstash = LoggerFactory.getLogger(LogstashUtil.class);
    private static final Logger log = LoggerFactory.getLogger(GenLogTimeTask.class);

    @Autowired
    private HouseService houseService;

    @Autowired
    private HouseindexService houseindexService;

    @Override
    @Scheduled(cron="0 30 23 * * ?")
    public void run() {

        log.info("开始执行 GenLogTimeTask...");

        int pageNo = 1;
        int pageSize = 500;

        while (true) {
            List<Houseindex> houseindexList = houseindexService.listToday(pageNo, pageSize);
            if(houseindexList==null  || houseindexList.size()==0)
                break;

            for (int i = 0; i < houseindexList.size(); i++) {
                Houseindex houseindex = houseindexList.get(i);
                House house = houseService.getByCode(houseindex.getCode());
                if(house==null || house.getTitle() == null)
                    continue;
                HouseLog houseLog = new HouseLog(house);
                logstash.info(houseLog.toLogString());
            }
            pageNo++;
        }
        log.info(" GenLogTimeTask finished................");

    }
}
