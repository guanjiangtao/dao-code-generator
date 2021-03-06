package org.bert.generator.common;

import java.util.HashMap;

/**
 * SQL常量
 */
public class SQLUtils {

    public static final HashMap<String, String> SQL_XML_PARAMS = new HashMap() {{
        put("Integer", "INTEGER");
        put("String", "VARCHAR");
        put("Date", "TIMESTAMP");
    }};

    public static final HashMap<String, String> SQL_PARAMS = new HashMap() {{
        put("Integer", "int");
        put("String", "varchar(255)"); // 所有的都走这个
        put("Date", "timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"); // 走这个逻辑
    }};

    /**
     * 驼峰转匈牙利
     * @param params 待转换的命名
     * @return 处理后的结果
     */
    public static String camelCase2Hungary(String params) {
        StringBuilder newNameBuilder = new StringBuilder();
        for(int i = 0; i < params.length(); i++) {
            char c = params.charAt(i);
            if (Character.isUpperCase(c)) {
                newNameBuilder.append("_");
                newNameBuilder.append((char) (c + 32));
                continue;
            }
            newNameBuilder.append(c);
        }
        return newNameBuilder.toString();
    }
}
