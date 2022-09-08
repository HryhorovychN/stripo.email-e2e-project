import commons.App;
import commons.data.User;
import commons.data.dataPage.PluginDto;
import org.testng.annotations.Test;

import static commons.data.dataPage.Locale.EN;

public class PluginTest extends BaseTest {

    @Test
    public void checkSendEnterpriseForm() {
        App
                .openPluginPage(EN)
                .openCustomForm()
                .sendEnterpriseForm(PluginDto.builder()
                        .senderName("User")
                        .email(User.getValidTestEmail())
                        .website("www.stripo.com")
                        .emailsCreated("50,001 — 100,000")
                        .employees("5 — 10")
                        .customers("1,001 — 5,000")
                        .position("Tester")
                        .phone("+380984385345")
                        .build())
                .checkSuccessMessage("Thank you for your request!\n" +
                        "Your data is on the way to our professionals.\n" +
                        "\n" +
                        "We will contact you in 3 business days.");
    }

    @Test(dataProviderClass = DataProviderForm.class, dataProvider = "baseSubscribeForm")
    public void verifySentSubscribeFormTest(String email, String message) {
        App
                .openPluginPage(EN)
                .sendSubscribeForm(email)
                .checkSubscribeFormMessage(message);
    }
}
