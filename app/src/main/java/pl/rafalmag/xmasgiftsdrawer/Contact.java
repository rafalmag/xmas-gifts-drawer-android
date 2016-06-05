package pl.rafalmag.xmasgiftsdrawer;

public class Contact {

    private String firstName;
    private String lastName;
    private String phoneNumberOffice;
    private String phoneNumberHome;
    private long _id;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumberOffice() {
        return phoneNumberOffice;
    }

    public void setPhoneNumberOffice(String phoneNumberOffice) {
        this.phoneNumberOffice = phoneNumberOffice;
    }

    public String getPhoneNumberHome() {
        return phoneNumberHome;
    }

    public void setPhoneNumberHome(String phoneNumberHome) {
        this.phoneNumberHome = phoneNumberHome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return _id == contact._id;
    }

    @Override
    public int hashCode() {
        return (int) (_id ^ (_id >>> 32));
    }

    @Override
    public String toString() {
        return "Contact [firstName=" + firstName + ", lastName=" + lastName
                + ", phoneNumberOffice=" + phoneNumberOffice
                + ", phoneNumberHome=" + phoneNumberHome + ", _id=" + _id + "]";
    }


}