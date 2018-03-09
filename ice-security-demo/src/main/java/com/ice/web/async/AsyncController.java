package com.ice.web.async;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * Description：
 * Cteated by wangpeng
 * 2018/3/9 16:08
 */
@RestController
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * Runnable形式实现异步处理rest服务器
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/order")
    public DeferredResult<String> order() throws InterruptedException {
        logger.info("主线程开始");

        String orderNum = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNum);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNum, result);
        /*Callable<String> result = new Callable<String>() {
            @Override
            public String call() throws Exception {
                 logger.info("副线程开始");
                 Thread.sleep(1000);
                 logger.info("副线程返回");
                return "success";
            }
        };*/
        logger.info("主线程返回");
        return result;
    }
}
