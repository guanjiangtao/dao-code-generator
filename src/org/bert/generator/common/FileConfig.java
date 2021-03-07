package org.bert.generator.common;

public class FileConfig {

    /**
     * 路径的配置
     */
    public static class FilePathConfig {

        private static final String basePath = "/Users/guanjiangtao/IdeaProjects/dao-code-generator/src/main";

        public static final String XML_PATH = basePath + "/resources/org/bert/generator";

        public static final String MAPPER_JAVA_PATH = basePath + "/java/org/bert/generator/dao";
    }

    /**
     * 文件类型的配置
     */
    public static class FileNameType {
        public static final String XML_TYPE = "Mapper.xml";
        public static final String MAPPER_JAVA_TYPE = "Mapper.java";

    }
}
