package dev.jsonmusk.entities;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private int id;

    private String username, password, firstname, lastname, email, phone_number, age_num;

    private byte[] photo;

    private boolean isLoggedIn;

    public User() {

    }

    public User(String username){
        this.username = username;
    }
    public User(int id, String username, String password, boolean isLoggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    public User(int id, String username, String password, String firstname, String lastname, String email, String phone_number, String age_num, byte[] photo, boolean isLoggedIn) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone_number = phone_number;
        this.age_num = age_num;
        this.photo = photo;
        this.isLoggedIn = isLoggedIn;
    }

    public User(int id, String username, String password, boolean isLoggedIn, String firstname, String lastname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.isLoggedIn = isLoggedIn;
        this.photo = null;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", age_num='" + age_num + '\'' +
                ", photo=" + Arrays.toString(photo) +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id && isLoggedIn == user.isLoggedIn && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(firstname, user.firstname) && Objects.equals(lastname, user.lastname) && Objects.equals(email, user.email) && Objects.equals(phone_number, user.phone_number) && Objects.equals(age_num, user.age_num) && Arrays.equals(photo, user.photo);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, password, firstname, lastname, email, phone_number, age_num, isLoggedIn);
        result = 31 * result + Arrays.hashCode(photo);
        return result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAge_num() {
        return age_num;
    }

    public void setAge_num(String age_num) {
        this.age_num = age_num;
    }
}
