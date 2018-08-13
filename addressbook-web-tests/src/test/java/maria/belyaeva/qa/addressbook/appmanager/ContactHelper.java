package maria.belyaeva.qa.addressbook.appmanager;

import maria.belyaeva.qa.addressbook.model.ContactData;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ContactHelper extends HelperBase{
    public ContactHelper(FirefoxDriver wd) {
        super(wd);
    }

    public void submitContactInfo() {
        wd.findElement(By.name("submit")).click();
    }
    public void fillInContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("company"), contactData.getCompanyName());
        type(By.name("mobile"), contactData.getCellPhoneNumber());
        type(By.name("email"), contactData.getEmail());
    }

    public void addNewContact() {
        click(By.linkText("add new"));
    }

    public void selectContact() {
        click(By.id("2"));
    }

    public void initContactModification() {
        click(By.xpath("//table[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void modifyContactField(String fieldName) {
        type;
    }
}
