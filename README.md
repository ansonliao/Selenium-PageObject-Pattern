# Selenium-PageObject-Pattern

## Pre-Condition
- Java version >= 8.0
- Maven version >= 3.5

## Run
Run the test by command

> Until now (2019-Jun-28), the latest version of ChromeDriver is  `76.0.3809.25`, but no corresponding Chrome
> version is updated, so need to add ChromeDriver specified version, for example version `74.0.3729.6`

```bash
./mvnw clean test -Dtest=MyTestRunner -Dwdm.chromeDriverVersion=74.0.3729.6
```

Test will
1. run across `Chrome` and `Firefox`
2. download `chromddriver`, `geckodriver`, `phantomjsdriver` automatically


## Report
1. please locate the HTML report from `target/ExentReports.html`
2. Please locate the screenshot from `target/screenshots` if any test case failed, and the screenshot
3. Placed the failure test case recording video at the folder `video`, this feature will be updated that attached the video to the testing report as some video recording custom need to clarify
will be attached in the HTML testing report, it means that no need to check the screenshot from `target/screenshots` specially.
4. Test step is logged in details
5. Test group in the HTML testing report
6. Test script author in the HTML testing report
7. Test case's description logged in the HTML testing report


## How to generate the specified Maven version's wrapper

Run the command line as below, and place the Maven version number you want:

```bash
mvn -N io.takari:maven:0.7.6:wrapper -Dmaven=[MAVEN_VERSION_NUMBER]
```

## TODO
Log the testing step with AOP, and the library is [AspectJ](https://www.eclipse.org/aspectj/).
