package com.github.ansonliao.selenium.google.runner;

import com.github.ansonliao.selenium.testng.TestNGRunner;
import com.github.ansonliao.selenium.testng.XmlSuiteBuilder;
import com.github.ansonliao.selenium.utils.DefaultSettingUtils;
import org.testng.annotations.Test;
import org.testng.xml.XmlSuite;

public class MyTestRunner {

    @Test
    public void run() {
        DefaultSettingUtils.set();
        XmlSuite xmlSuite = XmlSuiteBuilder.build();
        System.out.println(xmlSuite.toXml());
        TestNGRunner.Run();
    }
}
