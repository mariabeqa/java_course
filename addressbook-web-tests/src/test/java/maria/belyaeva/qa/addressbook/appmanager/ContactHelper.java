package maria.belyaeva.qa.addressbook.appmanager;

import maria.belyaeva.qa.addressbook.model.ContactData;
import maria.belyaeva.qa.addressbook.model.Contacts;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public void create(ContactData contact) {
        fillInContactForm(contact, true);
        submitContactInfo();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillInContactForm(contact, false);
        saveContactModification();
    }

    public void delete(ContactData contact) {
        System.out.println(contact.getId());
        selectContactById(contact.getId());
        initContactDeletion();
        confirmContactDeletion();
    }

    public void initiateContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void initContactModificationById(int id) {
        wd.findElement(By.xpath("//tr[@name='entry']/td/input[@id='" + id + "']//..//../td[@class='center'][3]//a//img")).click();
    }

    private void selectContactForModififcationById(int id) {
        WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
        WebElement row = checkbox.findElement(By.xpath("./../.."));
        List<WebElement> cells = row.findElements(By.tagName("td"));
        cells.get(7).findElement(By.tagName("a")).click();

//        wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id)));
//        wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']/td[8]/a", id)));
//        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    private void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id='" + id + "']")).click();
    }

    public void initContactDeletion() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    public void confirmContactDeletion() {
        acceptAlert();
    }

    public void initContactModification(int index) {
        wd.findElements(By.xpath("//img[@title='Edit']")).get(index).click();
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
        initiateContactCreation();
        fillInContactForm(contact, true);
        submitContactInfo();
    }

    public Contacts all() {
        Contacts contacts = new Contacts();
        List<WebElement> rows = wd.findElements(By.cssSelector("tr[name='entry']"));
        for(WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            int id = Integer.parseInt(cells.get(0).findElement(By.tagName("input")).getAttribute("id"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String allPhones = cells.get(5).getText();

            System.out.println(id + " " + firstName + " " + lastName);
            contacts.add(new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones));
        }
        return contacts;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String work = wd.findElement(By.name("work")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withFirstName(firstname).withLastName(lastname).withGroup("test1")
                .withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work);
    }
}
