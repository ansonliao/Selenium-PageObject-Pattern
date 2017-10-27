package com.github.ansonliao.selenium.google.runner;

import com.github.ansonliao.selenium.testng.TestNGRunner;
import com.github.ansonliao.selenium.utils.DefaultSettingUtils;
import org.testng.annotations.Test;

public class GoogleTestRunner {

    @Test
    public void run() {
        DefaultSettingUtils.set();
        DefaultSettingUtils.setDefaultBrowser();
        TestNGRunner.Run();
    }
}
