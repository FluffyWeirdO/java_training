package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickname());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail1());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        if (contactData.getPhoto() != null) {
            attach(By.name("photo"), contactData.getPhoto());
        }
        if (contactData.getBirthDate() != 0 && contactData.getBirthMonth() != null && contactData.getBirthYear() != null) {
            setBirthday(String.valueOf(contactData.getBirthDate()), contactData.getBirthMonth(), contactData.getBirthYear());
        }
        if (creation) {
            Assert.assertTrue(isElementPresent(By.name("new_group")));
        } else {
            Assert.assertFalse(isElementPresent(By.name("new_group")));
        }
    }

    public void selectById(int id) {
        click(By.xpath(".//*[@id='" + id + "']"));
    }

    public void initContactModificationById(int id) {
//        click(By.cssSelector("a[href='edit.php?id=" + id + "']"));
        click(By.cssSelector(String.format("a[href='edit.php?id=%s']", id)));
    }

    public void submitContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        fillContactForm(contactData, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(ContactData contact) {
        selectById(contact.getId());
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
        contactCache = null;
        waitForPageRefresh(By.id("maintable"));
    }


    public void setBirthday(String birthDate, String birthMonth, String birthYear) {
        selectFromDropDownList(By.name("bday"), birthDate);
        selectFromDropDownList(By.name("bmonth"), birthMonth);
        type(By.name("byear"), birthYear);
    }

    public void returnToHomePage() {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.linkText("home page"));
    }

    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public int checkContactCountLabel() {
        WebElement count = wd.findElement(By.id("search_count"));
        return Integer.valueOf(count.getText());
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if (contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allEmails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

            int id = Integer.parseInt(row.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName)
                    .withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails);
            contactCache.add(contact);
        }
        return new Contacts(contactCache);
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return contact.withFirstName(firstName).withLastName(lastName).withHomePhone(homePhone).withMobilePhone(mobilePhone)
                .withWorkPhone(workPhone).withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }

    public ContactData infoFromDetailsPage(ContactData contact) {
        initContactDetailsPage(contact.getId());
        String allDetails = wd.findElement(By.xpath(".//*[@id='content']")).getText();
        wd.navigate().back();
        return contact.withAllDetails(allDetails);

    }

    public void initContactDetailsPage(int id) {
        click(By.cssSelector("a[href='view.php?id=" + id + "']"));
    }

    public void deleteAll() {
        click(By.id("MassCB"));
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
        wd.switchTo().alert().accept();
        contactCache = null;
        waitForPageRefresh(By.id("maintable"));
    }

    public void addToGroup(ContactData contact, GroupData group) {
        selectById(contact.getId());
        selectGroupForAdding(group.getGroupName());
        click(By.name("add"));
        click(By.xpath(".//*[@id='content']/div/i/a"));
    }

    public void removeFromGroup(ContactData contact, GroupData group) {
        selectGroupForRemoval(group.getGroupName());
        selectById(contact.getId());
        click(By.name("remove"));
        click(By.xpath(".//*[@id='content']/div/i/a"));
    }

    private void selectGroupForAdding(String groupName) {
        new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(groupName);

    }

    private void selectGroupForRemoval(String groupName) {
        new Select(wd.findElement(By.name("group"))).selectByVisibleText(groupName);

    }
}