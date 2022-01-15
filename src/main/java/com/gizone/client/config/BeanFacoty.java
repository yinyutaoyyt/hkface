package com.gizone.client.config;

import com.gizone.client.constant.HkFace;
import com.gizone.client.sdk.hk.face.linux.HCNetSDK;
import com.sun.jna.Native;
import com.sun.jna.Platform;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author yyt
 */
@Component
public class BeanFacoty implements BeanFactoryPostProcessor {

    private String jarSplitName = HkFace.JAR_SPLIT_NAME;
    private String notJarPath = HkFace.NOT_JAR_PATH;
    private String dllName = HkFace.DLL_NAME;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //判断是不是 jar 启动
        HCNetSDK hcNetSDK = null;
        String path = HCNetSDK.class.getResource("/").getPath();
        if (!path.contains(jarSplitName)) {
            hcNetSDK = (HCNetSDK) Native.loadLibrary(String.format("%s%s", notJarPath, dllName), HCNetSDK.class);
        } else {
            if (Platform.isWindows()) {
                String basedir = System.getProperty("user.dir");
                hcNetSDK = (HCNetSDK) Native.loadLibrary(String.format("%s\\%s\\%s", basedir, "\\lib\\hk", dllName), HCNetSDK.class);
            }
            if (Platform.isLinux()) {
                String basedir = System.getProperty("user.dir");
                hcNetSDK = (HCNetSDK) Native.loadLibrary(basedir + "/lib/hklinux/libhcnetsdk.so", HCNetSDK.class);
                String strPathCom = basedir + "/lib/hklinux";
                HCNetSDK.NET_DVR_LOCAL_SDK_PATH struComPath = new HCNetSDK.NET_DVR_LOCAL_SDK_PATH();
                System.arraycopy(strPathCom.getBytes(), 0, struComPath.sPath, 0, strPathCom.length());
                struComPath.write();
                hcNetSDK.NET_DVR_SetSDKInitCfg(2, struComPath.getPointer());

                //设置libcrypto.so所在路径
                HCNetSDK.BYTE_ARRAY ptrByteArrayCrypto = new HCNetSDK.BYTE_ARRAY(256);
                String strPathCrypto = basedir + "/lib/hklinux/libcrypto.so";
                System.arraycopy(strPathCrypto.getBytes(), 0, ptrByteArrayCrypto.byValue, 0, strPathCrypto.length());
                ptrByteArrayCrypto.write();
                hcNetSDK.NET_DVR_SetSDKInitCfg(3, ptrByteArrayCrypto.getPointer());

                //设置libssl.so所在路径
                HCNetSDK.BYTE_ARRAY ptrByteArraySsl = new HCNetSDK.BYTE_ARRAY(256);
                String strPathSsl = basedir + "/lib/hklinux/libssl.so";
                System.arraycopy(strPathSsl.getBytes(), 0, ptrByteArraySsl.byValue, 0, strPathSsl.length());
                ptrByteArraySsl.write();
                hcNetSDK.NET_DVR_SetSDKInitCfg(4, ptrByteArraySsl.getPointer());
            }
        }
        beanFactory.registerSingleton("hcNetSDK", hcNetSDK);
    }
}
