package maria.belyaeva.qa.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;

@Entity
@Table( name = "addressbook")
public class ContactData {
    @Expose
    @Column( name = "firstname")
    private String firstName;

    @Expose
    @Column( name = "lastname")
    private String lastName;

    @Transient
    private String group;

    @Id
    @Column( name = "id")
    private int id;

    @Column( name = "home")
    @Type(type = "text")
    @Expose
    private String homePhone;

    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobilePhone;

    @Column( name = "work")
    @Type(type = "text")
    @Expose
    private String workPhone;

    @Transient
    private String allPhones;

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Column( name = "email")
    @Type(type = "text")
    @Expose
    private String email;

    @Column(name = "email2")
    @Type(type = "text")
    @Expose
    private String email1;

    @Column(name = "email3")
    @Type(type = "text")
    @Expose
    private String email2;

    @Column( name = "photo")
    @Type(type = "text")
    private String photo;

    @Transient
    private String allEmails;

    @Column(name = "address")
    @Type(type = "text")
    @Expose
    private String address;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (homePhone != null ? !homePhone.equals(that.homePhone) : that.homePhone != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        if (workPhone != null ? !workPhone.equals(that.workPhone) : that.workPhone != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (email1 != null ? !email1.equals(that.email1) : that.email1 != null) return false;
        if (email2 != null ? !email2.equals(that.email2) : that.email2 != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (email1 != null ? email1.hashCode() : 0);
        result = 31 * result + (email2 != null ? email2.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public  String getHomePhone() {
        return  homePhone;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                ", id=" + id +
                ", homePhone='" + homePhone + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", email='" + email + '\'' +
                ", email1='" + email1 + '\'' +
                ", email2='" + email2 + '\'' +
                ", address='" + address + '\'' +
                '}';
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
        return new File(photo);
    }
}
