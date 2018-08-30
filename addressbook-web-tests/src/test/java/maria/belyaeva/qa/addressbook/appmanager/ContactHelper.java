package maria.belyaeva.qa.addressbook.appmanager;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {
    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactInfo() {
        wd.findElement(By.name("submit")).click();
    }
    public void fillInContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompanyName());
        type(By.name("email"), contactData.getEmail());
        if(creation) {
            try {
                new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
            } catch (NoSuchElementException e) {
                System.err.println("There is no any group to choose yet");
            }

        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmContactDeletion() {
        acceptAlert();
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void editFieldInContactForm(By locator, String text) {
        type(locator, text);
    }

    public void saveContactModification() {
        click(By.name("update"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//tbody//tr[2]//td[1]//input[@type='checkbox']"));
    }

    public void createContact(ContactData contact) {
        addNewContact();
        fillInContactForm(contact, true);
        submitContactInfo();
    }

    public List<ContactData> getContactList() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//table[@id='maintable']//tbody//tr[@name='entry']"));
        for(WebElement element : elements) {
            String lastName = element.findElement(By.xpath("//td[2]")).getText();
            String firstName = element.findElement(By.xpath("//td[3]")).getText();
            ContactData contact = new ContactData(firstName, lastName, null);
            contacts.add(contact);
        }
        return contacts;
    }
}
