package com.github.ansonliao.selenium.google.objects;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.TextInput;

public class SearchResultPO {

    @FindBy(name = "q")
    @Name("Search Input Field")
    public TextInput searchInput;
}
