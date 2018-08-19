package maria.belyaeva.qa.addressbook.model;

public class ContactData {
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String nickName;
    private final String companyName;
    private final String cellPhoneNumber;
    private final String email;
    private final String group;

    public ContactData(String firstName, String middleName, String lastName, String nickName, String companyName, String cellPhoneNumber, String email, String group) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.companyName = companyName;
        this.cellPhoneNumber = cellPhoneNumber;
        this.email = email;
        this.group = group;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }
}
