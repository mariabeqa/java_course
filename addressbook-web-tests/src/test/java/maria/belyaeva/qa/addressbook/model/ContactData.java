package maria.belyaeva.qa.addressbook.model;

import java.io.File;

public class ContactData {
    private String firstName;
    private String lastName;
    private String group;
    private int id;
    private String homePhone;
    private String mobilePhone;
    private String workPhone;
    private String allPhones;

    public ContactData withPhoto(File photo) {
        this.photo = photo;
        return this;
    }

    private String email;
    private String email1;
    private String email2;
    private File photo;

    public ContactData withEmail(String email) {
        this.email = email;
        return  this;
    }

    public ContactData withEmail2(String email1) {
        this.email1 = email1;
        return  this;
    }

    public ContactData withEmail3(String email2) {
        this.email2 = email2;
        return  this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    private String allEmails;
    private String address;

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withLastName(String lastName) {

        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +

                ", id='" + id + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGroup() {
        return group;
    }

    public int getId() {
        return id;
    }

    public  String getHomePhone() {
        return  homePhone;
    }

    public  String getMobilePhone() {
        return  mobilePhone;
    }

    public  String getWorkPhone() {
        return  workPhone;
    }

    public String getAllPhones() {
        return allPhones;
    }

    public String getAllEmails() {
        return allEmails;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail1() {
        return email1;
    }

    public String getEmail2() {
        return email2;
    }

    public File getPhoto() {
        return photo;
    }
}
