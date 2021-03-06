package org.bert.generator.core;



import org.bert.generator.common.SQLTemplate;
import org.bert.generator.entity.Columns;
import org.bert.generator.entity.Table;

import java.util.List;

/**
 * 数据库操作
 */
public class DataBaseGeneratorCode implements CodeFactory {

    @Override
    public String generatorCode(Table table, String type) {
        StringBuilder sqlBody = new StringBuilder();
        List<Columns> columns = table.getColumns();
        StringBuilder primaryKeyBuilder = new StringBuilder();
        // 组装SQL
        columns.forEach((column) -> {
            StringBuilder sql = new StringBuilder();
            String nullString = column.getNull() ? "NOT NULL" : "DEFAULT NULL";
            sql.append(" `")
                    .append(column.getName())
                    .append("` ")
                    .append(column.getType())
                    .append(" ")
                    .append(nullString)
                    .append(",\n");
            // 记录主键
            if (column.getPrimaryKey()) {
                primaryKeyBuilder.append("  PRIMARY KEY (`").append(column.getName()).append("`)\n");
            }
            sqlBody.append(sql);
        });
        // 生成主键
        sqlBody.append(primaryKeyBuilder.toString());
        return SQLTemplate.insertTemplate(table.getTableName(), sqlBody.toString());
    }
}
