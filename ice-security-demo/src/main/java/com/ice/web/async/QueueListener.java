package com.ice.web.async;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 16:50
 */
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        new Thread(() ->{
            while (true) {
                if (StringUtils.isNotBlank(mockQueue.getComplateOrder())) {
                    String orderNum = mockQueue.getComplateOrder();
                    logger.info("返回订单处理结果" + orderNum);
                    deferredResultHolder.getMap().get(orderNum).setResult("place order success");

                    mockQueue.setComplateOrder(null);
                } else {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
