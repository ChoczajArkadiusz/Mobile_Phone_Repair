package pl.choczaj.spring.mobilerepair.domain.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Email
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "enabled")
    private boolean enabled;

    @Column
    private String password;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "user")
    private List<UserRole> roles = new ArrayList<>();

    public boolean hasRole(UserRoleEnum roleEnum) {
        for (UserRole role : this.getRoles()) {
            if (role.getRole() == roleEnum) {
                return true;
            }
        }
        return false;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role) {
        this.roles.add(role);
    }

    public void removeRole(UserRole role) {
        this.roles.remove(role);
    }
}
