import commons.App;
import commons.data.dataPage.TemplateType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static commons.data.dataPage.ItemType.POPULAR;
import static commons.data.dataPage.Locale.*;
import static commons.data.dataPage.Locale.EN;
import static commons.data.dataPage.TemplateType.*;
import static commons.data.dataPage.TemplateType.ALL;
import static commons.data.dataPage.TemplateType.FREE;
import static commons.data.dataPage.TemplateType.PREMIUM;

public class TemplateTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "baseSubscribeForm")
    public void verifySentSubscribeFormTest(String email, String message) {
        App
                .openTemplatesPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @DataProvider(name = "templateFilter")
    public Object[] TemplateFilter() {
        return new Object[]{
                PREMIUM, FREE, ALL
        };
    }
    @Test(dataProvider = "templateFilter")
    public void templateFiltersShouldWorkCorrectlyTest(TemplateType templateType) {
        App
                .openTemplatesPage(EN)
                .setTemplateType(templateType)
                .setItemType(POPULAR)
                .checkTemplatesType(templateType);
    }

    @DataProvider(name = "templateCategory")
    public Object[] TemplateCategory() {
        return new Object[][]{
                {"Type", List.of("Apology", "Cold Emails", "Alerts & Notifications")}
        };
    }

    @Test(dataProvider = "templateCategory")
    public void selectedCategoriesShouldBeSaved(String category, List<String> categoriesName) {
        App
                .openTemplatesPage(EN)
                .expandTemplateCategory(category)
                .selectTemplateCategories(categoriesName)
                .checkChosenCategories(categoriesName);
    }

    @Test(dependsOnMethods = {"selectedCategoriesShouldBeSaved"})
    public void selectedCategoriesShouldBeRemoved() {
        App
                .openTemplatesPage(EN)
                .closeItemCategory("Apology")
                .checkChosenCategories(List.of("Cold Emails", "Alerts & Notifications"))
                .closeAllItemCategory()
                .checkChosenCategories(List.of(""));
    }
}
