package com.harri.invoicesspring.models;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import com.harri.invoicesspring.models.Role;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotBlank(message = "username is mandatory")
    @Column(nullable = false, unique = true)
    private String username;

    @Size(max = 500)
    @NotBlank(message = "password is mandatory")
    @Column(nullable = false)
    private String password;

    @Size(max = 50)
    @NotBlank(message = "First name is mandatory")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Size(max = 50)
    @NotBlank(message = "Last is mandatory")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(max = 50)
    @NotBlank(message = "E-mail is required")
    @Column(nullable = false)
    private String email;

    @Size(max = 50)
    @NotBlank(message = "Mobile number is required")
    @Column(name = "mobile_number", nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    @NotNull(message = "Status is required")
    private Boolean active;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Role is required")
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Invoice> invoices;

    public User(){

    }

    public User(@Size(max = 50) String username, @Size(max = 500) String password, @Size(max = 50) String firstName, @Size(max = 50) String lastName, @Size(max = 50) String email, @Size(max = 50) String mobileNumber, Boolean active, Role role, List<Invoice> invoices) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.active = active;
        this.role = role;
        this.invoices = invoices;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
