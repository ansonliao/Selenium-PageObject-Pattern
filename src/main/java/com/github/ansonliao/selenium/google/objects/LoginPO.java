package com.github.ansonliao.selenium.google.objects;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class LoginPO {

    @FindBy(name = "username")
    @Name("User Name Input Field")
    public TextInput userNameInput;

    @FindBy(name = "password")
    @Name("Password Input Field")
    public TextInput passwordInput;

    @FindBy(className = "radius")
    @Name("Login Button")
    public Button loginBtn;
}
