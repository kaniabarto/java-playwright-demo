package ovh.kania;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ovh.kania.common.BaseTest;
import ovh.kania.dto.ContactUsDTO;
import ovh.kania.pages.ContactUsPage;
import ovh.kania.pages.HomePage;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class SendContactUsFormTest extends BaseTest {

    private HomePage homePage;

    @BeforeEach
    void beforeEach() {
        homePage = new HomePage(page);
        page.navigate("http://www.automationpractice.pl/");
    }

    @Test
    void shouldShowAnErrorWhenTryingToSendEmptyFormTest() {
        ContactUsPage contactUsPage = homePage.getTopMenuSection().clickOnContactUsLink();
        contactUsPage.getContactUsFormSection().clickOnSendMessageButton();
        assertThat(contactUsPage.getContactUsFormSection().getErrorMessage()).isVisible();
    }

    @Test
    void shouldFillAndSendContactUsFormTest() {
        ContactUsPage contactUsPage = homePage.getTopMenuSection().clickOnContactUsLink();
        contactUsPage.getContactUsFormSection().sendContactUsForm(ContactUsDTO.getDefaultContactUsDTO());
        assertThat(contactUsPage.getContactUsFormSection().getConfirmationMessage()).isVisible();
    }

}
