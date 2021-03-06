# 一个简单的Mybatis XML Code生成工具

## why do it？

简单一句话：mybatis的基础XML的编写实在是太烦了。

## 如何实现

这里使用了一个工厂模式，通过CodeFactory实现对于不同类型的生成器的控制工作。

SQL的操作主要是有一个SQLTemplate进行模版操作。这个就是我们预设的基础模版，可以通过我们这个模版进行生成所有的代码。

当然为了统一管理，我这里GeneratorOptions特地把所有的（目前所支持的）操作做了个封装，所有的通过这个命令即可。

![](./assets/Genetator.jpg)

## how to use it？

### options集合

createTable：创建表，可以直接生成出对应的create的sql语句。

createParams：生成sql集合。

selectXmlSql： 生成select格式的sql。

deleteXmlSql：生成delete格式的sql。

insertXmlSql：生成insert格式的sql。

updateXmlSql：生成update格式的sql。

countXmlSql：生成countsql。

### 使用方式

主代码位于：org.bert.generator.CodeGenerator。

```java
    public static void main(String[] args) {
        // 获取到对应的对象
        CodeGenerator codeGenerator = new CodeGenerator();
        // 待生成的类全名称
        codeGenerator.getParams("这里传入待生成的java对象");
        codeGenerator.start("sys_model", "Model", GeneratorOptions.COUNT_XML_SQL);
    }
```

getParams：这里传入待生成的java对象，切记不要传入类全名，这里还没做class.forName的反射。

start：传入sql的表名称，基础的实体名称，想要进行的操作（操作的话可以在Options集合中可以查到）。

## to do list

1）getParams支持填写类全名。

2）支持生成Mapper文件和XML实体文件。

3）idea插件话，接入idea插件体系，支持UI界面。

## end

欢迎使用～

有兴趣的也可以提MR～