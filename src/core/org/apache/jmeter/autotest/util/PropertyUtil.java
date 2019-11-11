package org.apache.jmeter.autotest.util;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Map;
import java.util.Properties;

/**
 * @author vigoss
 * @date 2019/11/7
 */
public class PropertyUtil {

    public String getProperty(String targetKey) throws Exception {

        String targetValue = null;
        Properties prop = new Properties();
        // 读取属性文件a.properties
        String userPath = System.getProperty("user.dir");

        InputStream in = new BufferedInputStream(new FileInputStream(userPath + "\\config.properties"));
        // 加载属性列表
        prop.load(in);
        for (String key : prop.stringPropertyNames()) {
            // 取出需要的属性值
            if (StringUtils.equals(key, targetKey)) {
                targetValue = prop.getProperty(key);
            }
        }
        in.close();

        return targetValue;
    }

    public static void writeProperties(Map<String, String> propertyMap) throws Exception {

        Properties prop = new Properties();
        String userPath = System.getProperty("user.dir");

        InputStream in = new BufferedInputStream(new FileInputStream(userPath + "\\config.properties"));
        // 加载属性列表
        prop.load(in);

        OutputStream output = new FileOutputStream(userPath + "\\config.properties");
        for (Map.Entry<String, String> entry : propertyMap.entrySet()) {
            // 写入属性值
            prop.setProperty(entry.getKey(), entry.getValue());
        }
        prop.store(output, null);
        output.close();
    }
}
