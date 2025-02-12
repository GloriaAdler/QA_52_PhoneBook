package phoneBook;

import phoneBook.data.ContactData;
import phoneBook.data.UserData;
import phoneBook.model.Contact;
import phoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static phoneBook.fw.ContactHelper.CONTACT_LOCATOR;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {
//        if (app.getUserHelper().isSignOutButtonPresent()) {
//            logger.info("User already logged in. Sign out...");
//            app.getUserHelper().clickOnSignOutButton();
//        } else {
//            logger.info("LOGIN link is present. No need to Sign Out");
//        }
        app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);
    }

    @Test
    public void addContactPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        String name = "Name";
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(name)
                .setLastName("LastName")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Berlin")
                .setDescription("Some Description"));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactDataPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.EMAIL)
                //.setEmail(UserData.RANDOM_EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESC));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test
    public void addContactWODescriptionPositiveTest() {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        //addContactPositiveData(CONTACT_NAME);
        app.getContactHelper().addContactPositiveData(new Contact()
                        .setName(ContactData.NAME)
                        .setLastName(ContactData.LAST_NAME)
                        .setPhone(ContactData.PHONE)
                        .setEmail(ContactData.EMAIL)
                        .setEmail(UserData.RANDOM_EMAIL)
                        .setAddress(ContactData.ADDRESS)
                //.setDescription(ContactData.DESC))
        );
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(ContactData.NAME));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }



    @Test(dataProvider = "contactDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderPositiveTest(String name, String lastName, String phone, String email, String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        String nameContact = name;
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(nameContact)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(nameContact));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "iteratorDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderIteratorPositiveTest(String name, String lastName, String phone, String email, String address, String description) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        String nameContact = name;
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(nameContact)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(nameContact));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "objectDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderObjectPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactDataProviderFromCSVPositiveTest(Contact contact) {
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО теста: " + contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ теста: " + contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore + 1);
    }

    @AfterMethod(enabled = false)
    public void postCondition() {
        // Сколько контактов на странице
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО удаления: " + contactsBefore);

        if (contactsBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять");
            return;
        }
        app.getContactHelper().click(By.className(CONTACT_LOCATOR));
        app.getContactHelper().click(By.xpath("//button[.='Remove']"));
        // Ждём, пока не выполнено условие:
        // условие: уменьшилось ли количество контактов по сравнению с исходным значением contactsBefore
        //new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsBefore);
        // Явное ожидание, пока количество контактов не уменьшится (contactsBefore - 1)
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR), contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления: " + contactsAfter);
        // Проверяем, что стало на -1 контакт
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }


}
//    @Test
//    public void addContactPositiveTest(){
//        int contactsBefore = app.getContactHelper().getContactsCount();
//        System.out.println("Кол-во до теста: " + contactsBefore);
//        //addContactPositiveData(CONTACT_NAME);
//        app.getContactHelper().addContactPositiveData(new Contact()
//                .setName(ContactData.NAME)
//                .setLastName("LastName")
//                .setPhone("1234567890")
//                .setEmail("gloria2025@gmail.com")
//                .setAddress("Germany, Berlin")
//                .setDescription("Some Description"));
//        int contactsAfter = app.getContactHelper().getContactsCount();
//        System.out.println("Кол-во после теста: " + contactsAfter);
//        //Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
//        Assert.assertEquals(contactsBefore + 1, contactsAfter);
//    }


