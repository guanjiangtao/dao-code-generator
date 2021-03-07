package org.bert.generator.common;

public class SQLTemplate {
    public static String insertTemplate(String name, String sql) {
        return "CREATE TABLE `" + name + "` (\n" +
                sql +
                ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;";
    }

    /**
     * 生成SQL参数Template
     * @param params 参数SQL
     * @return 生成的SQL字符串
     */
    public static String paramsTemplate(String params) {
        return "<sql id=\"Base_Column_List\">\n" +
                params +
                "</sql>\n";
    }

    /**
     * 生成Select语句
     * @param sql 基础SQL
     * @param resultType 返回类型
     * @param xmlName XML名称
     * @param parameterType 传递参数
     * @return select模版
     */
    public static String selectXMLTemplate(String sql,
                                           String resultType,
                                           String xmlName,
                                           String parameterType) {
        return "<select id=\"" + xmlName + "\" parameterType=\"" + parameterType + "\" resultType=\"" + resultType + "\">\n" +
                sql +
                "</select>\n";
    }

    /**
     * 删除XML模版
     * @param sql 删除SQL
     * @param xmlName XML标签名称
     * @param parameterType 传递参数
     * @return 删除SQL
     */
    public static String deleteXMLTemplate(String sql,
                                           String xmlName,
                                           String parameterType) {
        return "    <delete id=\"" + xmlName + "\" parameterType=\"" + parameterType + "\">\n" +
                sql +
                "    </delete>\n";
    }

    /**
     * 插入模版
     * @param sql 插入SQL
     * @param xmlName 对应的id名称
     * @param parameterType 待传入参数
     * @return
     */
    public static String insertXMLTemplate(String sql,
                                           String xmlName,
                                           String parameterType) {
        return "<insert id=\"" + xmlName + "\" parameterType=\"" + parameterType + "\">\n" +
                sql +
                "</insert>\n";
    }

    public static String updateXMLTemplate(String sql,
                                           String xmlName,
                                           String parameterType) {
        return "    <update id=\"" + xmlName + "\" parameterType=\"" + parameterType + "\">\n" +
                sql +
                "    </update>\n";
    }

    /**
     * 不需要传Params的Select的接口
     * @param sql 待执行sql
     * @param xmlName 方法名称
     * @param resultType 结果类型
     * @return sql
     */
    public static String selectWithoutParamsXMLTemplate(String sql,
                                          String xmlName,
                                          String resultType) {
        return "<select id=\"" + xmlName + "\" resultType=\"" + resultType + "\">\n" +
                sql +
                "</select>\n";
    }

    /**
     * XML文件主体
     * @param sqlContent sql主体
     * @param mapperType 返回的mapper类型
     * @return fileContent
     */
    public static String XMLFileBodyTemplate(String sqlContent,
                                             String mapperType) {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n" +
                "<mapper namespace=\"" + mapperType + "\">\n" +
                sqlContent +
                "</mapper>";
    }

    /**
     * Mapper文件
     * @param content 代码正文
     * @param modelPath 对应类的模块
     * @param className 类名称
     * @return Mapper代码
     */
    public static String MapperTemplate(String content, String modelPath, String className) {
        return "" +
                "package org.scheduler.quartz.dao;\n" +
                "\n" +
                "import " + modelPath + ";\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface " + className + "Mapper {\n" +
                content +
                "}";
    }
}
