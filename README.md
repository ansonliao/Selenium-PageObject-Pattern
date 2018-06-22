# Selenium-PageObject-Pattern

## Pre-Condition
- Java version >= 8.0
- Maven version >= 3.5

## Run
Run the test by command

```bash
mvn clean test -Dtest=MyTestRunner
```

Test will
1. run across `Chrome` , `Firefox`, and `PhantomJs`
2. download `chromddriver`, `geckodriver`, `phantomjsdriver` automatically


## Report
1. please locate the HTML report from `target/ExentReports.html`
2. Please locate the screenshot from `target/screenshots` if any test case failed, and the screenshot
will be attached in the HTML testing report, it means that no need to check the screenshot from `target/screenshots` specially.
