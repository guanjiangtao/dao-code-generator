package org.bert.generator;



import org.bert.generator.common.FileConfig;
import org.bert.generator.common.FileOperation;
import org.bert.generator.common.GeneratorOptions;
import org.bert.generator.common.SQLUtils;
import org.bert.generator.core.CodeFactory;
import org.bert.generator.core.DaoGeneratorCode;
import org.bert.generator.core.DataBaseGeneratorCode;
import org.bert.generator.demo.Test;
import org.bert.generator.entity.Columns;
import org.bert.generator.entity.Method;
import org.bert.generator.entity.Table;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

/**
 * 代码生成工具
 * 主要用于输出后端的controller，mapper，service等文件
 * // 第一阶段生成XML，db.sql已经完成。
 */
public class CodeGenerator {

    private Table table = new Table();

    public static void main(String[] args) throws ClassNotFoundException {
        // 获取到对应的对象
        CodeGenerator codeGenerator = new CodeGenerator();

        Class clazz = Class.forName("类全名");

        // 解析Class
        codeGenerator.getParams(clazz);
        codeGenerator.start(clazz);
    }

    /**
     * 启动工具
     */
    private void start(Class clazz) {
        // 获取类全名和类名
        String simpleName = clazz.getSimpleName();
        String name = clazz.getName();

        table.setModelClassName(name);
        table.setTableName("数据库表名称");
        table.setXmlName(simpleName);
        table.setMapperType(name + "Mapper");
        // 这里写类全名称
        table.setDaoPath("dao类全名");
        String sql = this.generatorCode(GeneratorOptions.CREATE_ALL_XML_SQL);
        String mapperMethod = this.generatorCode(GeneratorOptions.CREATE_MAPPER_CODE);

        // 导出为XML
        FileOperation.saveDataToFile(simpleName + FileConfig.FileNameType.XML_TYPE, sql, FileConfig.FilePathConfig.XML_PATH);
        // 导出为Mapper.java
        FileOperation.saveDataToFile(simpleName + FileConfig.FileNameType.MAPPER_JAVA_TYPE, mapperMethod, FileConfig.FilePathConfig.MAPPER_JAVA_PATH);
    }

    /**
     * 取得构建参数
     * @param clazz 待剩下的类类信息
     */
    private void getParams(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<Columns> columnsList = new LinkedList<>();
        table.setClazzName(clazz.getName());
        for (Field field : fields) {
            Columns columns = new Columns();
            String simpleName = field.getType().getSimpleName();
            columns.setName(field.getName());
            columns.setType(SQLUtils.SQL_PARAMS.get(field.getType().getSimpleName()));
            columns.setNull(false);
            columns.setIncrement(false);
            columns.setPrimaryKey(false);
            // 主键逻辑处理
            if (field.getName().equals("id")
                    || field.getName().endsWith("id")
                    || field.getName().startsWith("id")) {
                columns.setIncrement(true);
                columns.setPrimaryKey(true);
                columns.setNull(true);
            }
            // XML用的值
            columns.setXmlType(SQLUtils.SQL_XML_PARAMS.get(simpleName));
            columnsList.add(columns);
        }
        table.setColumns(columnsList);
    }

    /**
     * 创建表的SQL
     *
     * @param type 类型
     */
    private String generatorCode(String type) {
        if(GeneratorOptions.CREATE_TABLE.equals(type)) {
            CodeFactory codeFactory = new DataBaseGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.CREATE_TABLE);
        } else if (GeneratorOptions.CREATE_PARAMS.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.CREATE_PARAMS);
        } else if (GeneratorOptions.SELECT_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.SELECT_XML_SQL);
        } else if (GeneratorOptions.DELETE_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.DELETE_XML_SQL);
        } else if (GeneratorOptions.INSERT_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.INSERT_XML_SQL);
        } else if (GeneratorOptions.UPDATE_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.UPDATE_XML_SQL);
        } else if (GeneratorOptions.COUNT_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.COUNT_XML_SQL);
        } else if (GeneratorOptions.CREATE_ALL_XML_SQL.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.CREATE_ALL_XML_SQL);
        } else if (GeneratorOptions.CREATE_MAPPER_CODE.equals(type)) {
            CodeFactory codeFactory = new DaoGeneratorCode();
            return codeFactory.generatorCode(table, GeneratorOptions.CREATE_MAPPER_CODE);
        }
        return "";
    }
}
