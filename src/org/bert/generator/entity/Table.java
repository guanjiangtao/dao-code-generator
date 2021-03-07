package org.bert.generator.entity;

import java.util.LinkedList;
import java.util.List;

public class Table {

    private String tableName;

    private List<Columns> columns;

    private String clazzName;

    private String xmlName;

    private String mapperType;

    private String modelClassName; // 待导入模块的名称

    private String daoPath; // dao文件存放路径

    private final List<Method> methods = new LinkedList<>(); // 默认初始化

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Columns> getColumns() {
        return columns;
    }

    public void setColumns(List<Columns> columns) {
        this.columns = columns;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getXmlName() {
        return xmlName;
    }

    public void setXmlName(String xmlName) {
        this.xmlName = xmlName;
    }

    public String getMapperType() {
        return mapperType;
    }

    public void setMapperType(String mapperType) {
        this.mapperType = mapperType;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public String getModelClassName() {
        return modelClassName;
    }

    public void setModelClassName(String modelClassName) {
        this.modelClassName = modelClassName;
    }

    public String getDaoPath() {
        return daoPath;
    }

    public void setDaoPath(String daoPath) {
        this.daoPath = daoPath;
    }

    /**
     * 封装方法
     * @param methodName 方法
     * @param params 参数
     * @param resultType 返回类型
     */
    public void buildMethod(String methodName, String params, String resultType) {
        Method method = new Method();
        method.setMethodName(methodName);
        method.setParams(params);
        method.setResultType(resultType);
        methods.add(method);
    }
}
