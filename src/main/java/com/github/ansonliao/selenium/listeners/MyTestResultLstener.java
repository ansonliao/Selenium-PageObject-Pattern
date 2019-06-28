package com.github.ansonliao.selenium.listeners;

import com.automation.remarks.video.annotations.Video;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.github.ansonliao.selenium.factory.WDManager;
import com.github.ansonliao.selenium.internal.ScreenshotManager;
import com.github.ansonliao.selenium.report.factory.ExtentTestManager;
import com.github.ansonliao.selenium.testng.TestResultListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import static com.github.ansonliao.selenium.utils.StringUtils.removeQuoteMark;
import static com.github.ansonliao.selenium.utils.config.SEConfigs.getConfigInstance;


public class MyTestResultLstener extends TestResultListener {

    private static Logger logger =
            LoggerFactory.getLogger(MyTestResultLstener.class);

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Class<?> clazz = getTestRealClass(iTestResult);
        ITestNGMethod method = getTestMethod(iTestResult);
        String browserName = getTestBrowser(iTestResult);

        ScreenshotManager screenshotManager = new ScreenshotManager();
        String imgPrefix = screenshotManager.capture(clazz, method.getMethodName(), browserName);
        ExtentTestManager.getExtentTest().fail(iTestResult.getThrowable());

        try {
            ExtentTestManager.getExtentTest().fail(
                    "Screenshot: ",
                    MediaEntityBuilder.createScreenCaptureFromPath(imgPrefix).build());

            // attach video
            Method m = method.getConstructorOrMethod().getMethod();
            if (m.isAnnotationPresent(Video.class)) {
                String videoName = m.getAnnotation(Video.class).name();
                String.format("video%s%s_%s_", File.separator, videoName, browserName);
                String video = String.format("video%s%s_%s_", File.separator, videoName, browserName);
                ExtentTestManager.getExtentTest().fail(
                        "Video: ",
                        MediaEntityBuilder.createScreenCaptureFromPath(video).build()
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Test fail: {} - {} - {}, screenshot - {}",
                clazz.getName(), method.getMethodName(), browserName, imgPrefix);

        ExtentTestManager.getExtentTest().log(Status.FAIL, "Test Failed");
        WDManager.quitAndClearDriver();
        ExtentTestManager.extentReport.flush();
    }

    private Class<?> getTestRealClass(ITestResult iTestResult) {
        return iTestResult.getTestClass().getRealClass();
    }

    private ITestNGMethod getTestMethod(ITestResult iTestResult) {
        return iTestResult.getMethod();
    }

    private String getTestBrowser(ITestResult iTestResult) {
        return iTestResult.getTestContext().getCurrentXmlTest()
                .getAllParameters().get(removeQuoteMark(getConfigInstance().testngXmlBrowserParamKey()));
    }
}
