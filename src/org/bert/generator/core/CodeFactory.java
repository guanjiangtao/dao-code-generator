package org.bert.generator.core;


import org.bert.generator.entity.Table;

/**
 * 代码工厂
 */
public interface CodeFactory {

    String generatorCode(Table table, String type);
}
