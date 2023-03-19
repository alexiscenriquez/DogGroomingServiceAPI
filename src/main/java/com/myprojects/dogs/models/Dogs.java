package com.myprojects.dogs.models;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.List;


@Entity
public class Dogs implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @NotBlank
    @Schema(required=true)
    private String name;
    private Integer age;
    private String breed;
    @JsonProperty( access = JsonProperty.Access.WRITE_ONLY )
    @OneToMany(mappedBy ="dog", cascade = CascadeType.ALL)
    private List<Dog_Customer> dogCustomers;

    @ManyToOne
    @JoinColumn(name="employee_id" ,referencedColumnName = "id")
    private Employee employee;
    public Dogs() {
    }

    public Dogs(Integer id, String name, Integer age, String breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Dogs{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", breed='" + breed + '\'' +
                ", dogCustomers=" + dogCustomers +
                ", employee=" + employee +
                '}';
    }
}
