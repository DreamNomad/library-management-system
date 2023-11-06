package com.book;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.fill.Column;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MySQLGenerator{

    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://127.0.0.1:3306/book?useUnicode=true&characterEncoding=UTF-8&useSSL=false", "root", "root")
            .schema("book");

    @Test
    public void testSimple() {
        /*
            代码生成器配置  如果控制无法输入内容
            选择IDEA菜单栏的Help -> Edit Custom VM Options
            添加-Deditable.java.test.console=true然后重启IDEA
        */
        String property = System.getProperty("user.dir");
        Path globalPath = Paths.get(property, "src", "main", "java");
        Path xmlPath = Paths.get(property, "src", "main", "resources", "mapper");
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                // 全局配置
                .globalConfig((scanner, builder) -> builder.author(scanner.apply("请输入作者名称:")).outputDir(globalPath.toString()))
                // 包配置
                .packageConfig((scanner, builder) -> builder.parent("com.book").mapper("dao").pathInfo(Collections.singletonMap(OutputFile.xml, xmlPath.toString())))
                // 策略配置
                .strategyConfig((scanner, builder) -> builder.addInclude(getTables(scanner.apply("请输入表名，多个英文逗号分隔？所有输入 all")))
                        .controllerBuilder().enableRestStyle().enableHyphenStyle()
                        .entityBuilder().enableLombok().addTableFills(
                                new Column("create_time", FieldFill.INSERT),
                                new Column("update_time", FieldFill.INSERT_UPDATE),
                                new Column("delete_time", FieldFill.DEFAULT)
                        ).enableTableFieldAnnotation().logicDeleteColumnName("delete_time").enableChainModel().idType(IdType.AUTO).mapperBuilder().formatMapperFileName("%sDao").build())
                /*
                    模板引擎配置，默认 Velocity 可选模板引擎 Beetl 或 Freemarker
                   .templateEngine(new BeetlTemplateEngine())
                   .templateEngine(new FreemarkerTemplateEngine())
                 */
                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }
}
