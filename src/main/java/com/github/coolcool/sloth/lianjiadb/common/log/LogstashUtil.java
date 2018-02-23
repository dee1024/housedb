package com.github.coolcool.sloth.lianjiadb.common.log;

import com.github.coolcool.sloth.lianjiadb.model.House;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dee on 2016/11/7.
 */
public abstract class LogstashUtil {

    private static final Logger log = LoggerFactory.getLogger(LogstashUtil.class);

    public static void genlog(House house){
        HouseLog houseLog = new HouseLog(house);

        log.info(houseLog.toLogString());
    }

}
