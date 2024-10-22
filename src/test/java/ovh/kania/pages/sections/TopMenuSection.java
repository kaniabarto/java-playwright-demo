package ovh.kania.pages.sections;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import ovh.kania.pages.BasePage;
import ovh.kania.pages.ContactUsPage;

public class TopMenuSection extends BasePage {

    private Locator contactUsLink;

    public TopMenuSection(Page page) {
        super(page);
        this.contactUsLink = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Contact us"));
    }

    public ContactUsPage clickOnContactUsLink() {
        contactUsLink.click();
        return new ContactUsPage(page);
    }

}
