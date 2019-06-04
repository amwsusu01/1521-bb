package com.hanergy.reportForms.service.impl;

import com.hanergy.reportForms.commons.utils.DateUtils;
import com.hanergy.reportForms.service.IGenerateCodeService;
import com.hanergy.reportForms.service.IRedisService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName GenerateCodeServiceImpl
 * @Description TODO
 * @Author DURONGHONG
 * @DATE 2018/9/13 13:51
 * @Version 1.0
 **/
@Service
public class GenerateCodeServiceImpl implements IGenerateCodeService {

    private static Logger logger = LoggerFactory.getLogger(GenerateCodeServiceImpl.class);

    private static Integer DEFALUT_CAPACITY = 100000;
    private static BlockingQueue<Long> blockingQueue = new ArrayBlockingQueue<Long>(DEFALUT_CAPACITY);

    @Autowired
    private IRedisService redisService;

    /**
     * 初始最小数字
     */
    private static Long initial_number = 100000000L;
    /**
     * 最大数字
     */
    private static Long max = 999999999L;

    private static String BACK_GROUND_ID_ORDER = "BACK_GROUND_ID_ORDER";

    private static AtomicLong basic = new AtomicLong(initial_number);

    @Override
    public List<Long> generatedIds(Integer number) {
        logger.info("[ID生成器]===>>>批量获取ID");
        if (number > DEFALUT_CAPACITY) {
            number = DEFALUT_CAPACITY;
        }
        List<Long> ids = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            ids.add(getSingleId());
        }
        return ids;
    }

    @Override
    public Long getSingleId() {
        String dateStr = DateUtils.dateToString(new Date(), "yyMMdd");
        String machineCode = "1";

        String stringGet = redisService.stringGet(BACK_GROUND_ID_ORDER);
        if (StringUtils.isEmpty(stringGet)) {
            redisService.expire(BACK_GROUND_ID_ORDER, getDate(), initial_number + "", TimeUnit.MILLISECONDS);
        }

        Long incr = redisService.incr(BACK_GROUND_ID_ORDER, 1L);
        if (incr >= max) {
            redisService.expire(BACK_GROUND_ID_ORDER, getDate(), initial_number + "", TimeUnit.MILLISECONDS);
        }
        long id = Long.parseLong(dateStr + machineCode + incr);
        return id;
    }

    private Long getDate() {
        long l = System.currentTimeMillis();
        Date endOfDay = DateUtils.getEndOfDay(new Date());
        return endOfDay.getTime() - l;
    }


}
