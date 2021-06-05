package com.dispnt.mall;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringWebserviceApplicationTests {

    @Test
    public void contextLoads() {
        DocsConfig docsConfig = new DocsConfig();
        docsConfig.setProjectPath("D:\\学校3\\计算机应用能力大赛\\1\\backend"); // root project path
        docsConfig.setProjectName("Campus-Mall"); // project name
        docsConfig.setApiVersion("V1.0");       // api version
        docsConfig.setDocsPath("D:\\学校3\\计算机应用能力大赛\\1\\backend\\Docs"); // api docs target path
        docsConfig.setAutoGenerate(Boolean.TRUE);  // auto generate
        Docs.buildHtmlDocs(docsConfig); // execute to generate
    }

}
