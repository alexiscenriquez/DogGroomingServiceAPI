package com.myprojects.dogs.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank
    private String fName;
    @NotBlank
   private String lName;
    @Pattern(regexp = "^.+@.+$")
    @Column(unique = true, nullable = false)
    private String email;
    private String mobile;
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY )
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Dog_Customer> dogCustomers;
    public Customer() {
    }
    public Customer(Integer id, String fName, String lName, String email, String mobile) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.mobile = mobile;
    }

    public void setCustomerId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerID() {
        return id;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;

    }

    public List<Dog_Customer> getDogCustomers() {
        return dogCustomers;
    }

    public void setDogCustomers(List<Dog_Customer> dogCustomers) {
        this.dogCustomers = dogCustomers;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", fName='" + fName + '\'' +
                ", lName='" + lName + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dogCustomers=" + dogCustomers +
                '}';
    }
}
