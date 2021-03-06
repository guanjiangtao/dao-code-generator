package org.bert.generator.entity;

public class Columns {

    private String name;

    private String type;

    private Boolean isNull;

    private Boolean isIncrement;

    private String defaultProps;

    private Boolean isPrimaryKey;

    private String xmlType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getNull() {
        return isNull;
    }

    public void setNull(Boolean aNull) {
        isNull = aNull;
    }

    public Boolean getIncrement() {
        return isIncrement;
    }

    public void setIncrement(Boolean increment) {
        isIncrement = increment;
    }

    public String getDefaultProps() {
        return defaultProps;
    }

    public void setDefaultProps(String defaultProps) {
        this.defaultProps = defaultProps;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public String getXmlType() {
        return xmlType;
    }

    public void setXmlType(String xmlType) {
        this.xmlType = xmlType;
    }
}
