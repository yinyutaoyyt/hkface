package com.gizone.client.config.listener;

import com.gizone.client.sdk.hk.face.linux.HCNetSDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 监听器
 * @author yyt
 */
@Component
public class CustomizeListener {

    @Autowired
    public HCNetSDK hcNetSDK;

    @Value("${hk.sdk.face.log.patch}")
    private String logPath;

    /**
     *项目启动
     * @param event
     */
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //SDK初始化
        hcNetSDK.NET_DVR_Init();
        //SDK启用写日志
        hcNetSDK.NET_DVR_SetLogToFile(3, logPath, false);
    }

    /**
     * 项目关闭
     * @param event
     */
    @EventListener
    public void onApplicationEvent(ContextStoppedEvent event) {
        hcNetSDK.NET_DVR_Cleanup();
    }
}
