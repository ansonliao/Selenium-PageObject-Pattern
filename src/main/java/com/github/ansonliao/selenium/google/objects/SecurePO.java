package com.github.ansonliao.selenium.google.objects;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;


public class SecurePO {

    @FindBy(linkText = "Logout")
    @Name("Logout Button")
    public Button logoutBtn;
}
