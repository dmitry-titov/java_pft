package ru.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class ContactData {
    @Id
    @Column(name = "id")
    @XStreamOmitField
    private int id = Integer.MAX_VALUE;

    @Column(name = "firstname")
    @Expose
    private String firstName;

    @Column(name = "middlename")
    @Expose
    private String middleName;

    @Column(name = "lastname")
    @Expose
    private String lastName;

    @Column(name = "nickname")
    @Expose
    private String nickname;

    @Column(name = "mobile")
    @Type(type = "text")
    @Expose
    private String mobilePhone;

    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;

    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email;

    @Transient
    private String email2;

    @Transient
    private String email3;

    @Transient
    @Expose
    private String bday;

    @Transient
    @Expose
    private String bmonth;

    @Transient
    @Expose
    private String byear;

    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Transient
    private String address;
    @Transient
    private String fullName;

    @Expose
    @Column(name = "photo")
    @Type(type = "text")
    private String photoPath;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmail2() {
        return email2;
    }

    public String getEmail3() {
        return email3;
    }

    public String getBday() {
        return bday;
    }

    public String getBmonth() {
        return bmonth;
    }

    public String getByear() {
        return byear;
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

    public String getFullName() {
        return fullName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public ContactData withPhotoPath(String photoPath) {
        this.photoPath = photoPath;
        return this;
    }

    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public ContactData withMobile(String mobile) {
        this.mobilePhone = mobile;
        return this;
    }

    public ContactData withHome(String home) {
        this.homePhone = home;
        return this;
    }

    public ContactData withWork(String work) {
        this.workPhone = work;
        return this;
    }

    public ContactData withEmail(String email) {
        this.email = email;
        return this;
    }

    public ContactData withEmail2(String email2) {
        this.email2 = email2;
        return this;
    }

    public ContactData withEmail3(String email3) {
        this.email3 = email3;
        return this;
    }

    public ContactData withBday(String bday) {
        this.bday = bday;
        return this;
    }

    public ContactData withBmonth(String bmonth) {
        this.bmonth = bmonth;
        return this;
    }

    public ContactData withByear(String byear) {
        this.byear = byear;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public Groups getGroups() {
        return new Groups(groups);
    }

    public String mergePhones() {
        return Arrays.asList(this.getHomePhone(), this.getMobilePhone(), this.getWorkPhone())
                .stream().filter((s) -> s != null && !s.isEmpty())
                .map(this::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public String mergeNames() {
        return Arrays.asList(this.getFirstName(), this.getMiddleName(), this.getLastName())
                .stream().filter((s) -> s != null && !s.isEmpty())
                .collect(Collectors.joining(" "));
    }

    public String mergeEmails() {
        return Arrays.asList(this.getEmail(), this.getEmail2(), this.getEmail3())
                .stream().filter((s) -> s != null && !s.isEmpty())
                .collect(Collectors.joining("\n"));
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (mobilePhone != null ? !mobilePhone.equals(that.mobilePhone) : that.mobilePhone != null) return false;
        return address != null ? address.equals(that.address) : that.address == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    private String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}
