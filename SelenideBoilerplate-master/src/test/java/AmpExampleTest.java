import commons.App;
import org.testng.annotations.Test;

import static commons.data.dataPage.Locale.EN;

public class AmpExampleTest extends BaseTest {

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "baseSubscribeForm")
    public void verifySentSubscribeFormTest(String email, String message) {
        App
                .openAmpExamplePage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "esputnikSubscribeForm", priority = 2)
    public void sendGamificationForm(String email, String message) {

        App
                .openAmpExamplePage(EN)
                .clickGamification(true)
                .clickGamification(true)
                .sendEsputnikForm(email)
                .checkEsputnikMessage(message);
    }
}
