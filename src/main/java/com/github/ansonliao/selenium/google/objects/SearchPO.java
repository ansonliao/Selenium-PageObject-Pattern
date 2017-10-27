package com.github.ansonliao.selenium.google.objects;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SearchPO {

    @FindBy(name = "q")
    @Name("Search Input Field")
    public TextInput searchInput;

    @FindBy(name = "btnK")
    @Name("Search Submit Button")
    public Button searchBtn;
}
