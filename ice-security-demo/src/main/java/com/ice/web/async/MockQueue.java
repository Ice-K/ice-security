package com.ice.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Description：模拟消息队列
 * Cteated by wangpeng
 * 2018/3/9 16:25
 */
@Component
public class MockQueue {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String placeOrder;

    private String complateOrder;

    public String getPlaceOrder() {
        return placeOrder;
    }

    public void setPlaceOrder(String placeOrder) throws InterruptedException {
        new Thread(() -> {
            logger.info("接到下单请求"+placeOrder);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.complateOrder = placeOrder;
            logger.info("下单请求处理完毕"+placeOrder);
        }).start();
    }

    public String getComplateOrder() {
        return complateOrder;
    }

    public void setComplateOrder(String complateOrder) {
        this.complateOrder = complateOrder;
    }
}
