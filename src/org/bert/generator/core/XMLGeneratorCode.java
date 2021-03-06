package org.bert.generator.core;


import org.bert.generator.common.GeneratorOptions;
import org.bert.generator.common.SQLTemplate;
import org.bert.generator.common.SQLUtils;
import org.bert.generator.entity.Columns;
import org.bert.generator.entity.Table;

import java.util.List;

/**
 * 生成XML代码
 */
public class XMLGeneratorCode  implements CodeFactory {

    @Override
    public String generatorCode(Table table, String type) {
        if (GeneratorOptions.CREATE_PARAMS.equals(type)) {
            return this.createParams(table.getColumns());
        } else if (GeneratorOptions.SELECT_XML_SQL.equals(type)) {
            return this.selectXmlSql(table);
        } else if (GeneratorOptions.DELETE_XML_SQL.equals(type)) {
            return this.deleteXmlSql(table);
        } else if (GeneratorOptions.INSERT_XML_SQL.equals(type)) {
            return this.insertXmlSql(table);
        } else if (GeneratorOptions.UPDATE_XML_SQL.equals(type)) {
            return this.updateXmlSql(table);
        } else if (GeneratorOptions.COUNT_XML_SQL.equals(type)) {
            return this.countXmlSql(table);
        }
        return "";
    }

    /**
     * 创建SQL集合
     * @param columns 待创建参数
     * @return SQL集合
     */
    private String createParams(List<Columns> columns) {
        StringBuilder sqlBody = new StringBuilder();
        // 组装SQL
        columns.forEach((column) -> {
            String name = SQLUtils.camelCase2Hungary(column.getName());
            sqlBody.append("  `")
                    .append(name)
                    .append("`,\n");
        });
        sqlBody.deleteCharAt(sqlBody.length() - 2);
        return SQLTemplate.paramsTemplate(sqlBody.toString());
    }

    /**
     * select语句
     * @param table table信息
     * @return select sql
     */
    public String selectXmlSql(Table table) {
        StringBuilder stringBuilder = new StringBuilder();
        String findAll= "        select\n" +
                "           <include refid=\"Base_Column_List\" />\n" +
                "           from " + table.getTableName() + "\n" +
                "        ${sql}\n";
        String selectByPrimaryKey = "    select\n" +
                "        <include refid=\"Base_Column_List\" />\n" +
                "        from " + table.getTableName() + "\n" +
                "        where id = #{" + table.getColumns().get(0).getName() + ",jdbcType=INTEGER}\n";
        // selectAll
        String sql1 =  SQLTemplate.selectXMLTemplate(findAll,
                table.getClazzName(),
                "selectAll" + table.getXmlName() + "bySql",
                "java.lang.String");
        // selectByPrimaryKey
        String sql2 =  SQLTemplate.selectXMLTemplate(selectByPrimaryKey,
                table.getClazzName(),
                "selectByPrimaryKey",
        "java.lang.Integer");
        stringBuilder.append(sql1);
        stringBuilder.append("\n");
        stringBuilder.append(sql2);
        // 返回方法组
        return stringBuilder.toString();
    }

    /**
     * 删除SQL
     * @param table table对象
     * @return 返回删除SQL
     */
    public String deleteXmlSql(Table table) {
        String deleteSql = "    delete from " + table.getTableName() + "\n" +
                "        where id = #{" + table.getColumns().get(0).getName() + ",jdbcType=INTEGER}\n";
        return SQLTemplate.deleteXMLTemplate(deleteSql, "deleteByPrimaryKey", "java.lang.Integer");
    }

    /**
     * 新增SQL模版
     * @param table table属性
     * @return 生成SQL结果
     */
    public String insertXmlSql(Table table) {
        // 产生Params
        List<Columns> columns = table.getColumns();
        StringBuilder params = new StringBuilder();
        // 组装SQL
        columns.forEach((column) -> {
            String name = SQLUtils.camelCase2Hungary(column.getName());
            params.append("     `")
                    .append(name)
                    .append("`,\n");
        });
        params.deleteCharAt(params.length() - 2);
        params.deleteCharAt(params.length() - 1);
        // 产生Values
        StringBuilder values = new StringBuilder();
        // 组装SQL
        columns.forEach((column) -> {
            values.append("     #{")
                    .append(column.getName())
                    .append(",jdbcType= ")
                    .append(column.getXmlType())
                    .append("},\n");
        });
        values.deleteCharAt(values.length() - 2);
        values.deleteCharAt(values.length() - 1);
        String sql = "  insert into " + table.getTableName() + " (\n" +
                params.toString() +
                ")\n" +
                "   values (\n" +
                values.toString() +
                ")\n";
        return SQLTemplate.insertXMLTemplate(sql,
                "insert",
                table.getClazzName());
    }

    /**
     * XML生成
     * @param table table数据
     * @return update SQL
     */
    public String updateXmlSql(Table table) {
        List<Columns> columns = table.getColumns();
        StringBuilder stringBuilder = new StringBuilder();
        columns.forEach((column) -> {
            String name = SQLUtils.camelCase2Hungary(column.getName());
            stringBuilder.append("          `")
                    .append(name)
                    .append("` = #{")
                    .append(column.getName())
                    .append(",jdbcType= ")
                    .append(column.getXmlType())
                    .append("},\n");
        });

        String sql = "      UPDATE " + table.getTableName() + "\n" +
                "        SET\n" +
                stringBuilder +
                "        WHERE\n" +
                "           id = #{" + table.getColumns().get(0).getName() + ",jdbcType=INTEGER}\n";
        return SQLTemplate.updateXMLTemplate(sql, "updateByPrimaryKey", table.getClazzName());
    }

    /**
     * 总数SQL
     * @param table 表数据
     * @return 输出结果
     */
    public String countXmlSql(Table table) {
        String countSql= "  select\n" +
                "       count(*)\n" +
                "  from " + table.getTableName() + "\n";
        return SQLTemplate.selectWithoutParamsXMLTemplate(countSql, "getCounts", "java.lang.Integer");
    }
}
