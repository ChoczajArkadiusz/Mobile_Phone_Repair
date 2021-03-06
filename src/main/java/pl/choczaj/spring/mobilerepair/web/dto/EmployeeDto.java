package pl.choczaj.spring.mobilerepair.web.dto;

import pl.choczaj.spring.mobilerepair.domain.model.UserRoleEnum;
import pl.choczaj.spring.mobilerepair.web.validation.Phone;
import pl.choczaj.spring.mobilerepair.web.validation.ValidGroupEmployeeAdd;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeDto {

    private Long id;

    @NotBlank
    @NotNull
    private String firstName;

    @NotBlank
    @NotNull
    private String lastName;

    @NotBlank
    @NotNull
    @Email
    @Size(min = 3)
    private String email;

    @Phone
    private String phone;

    @NotBlank(groups = ValidGroupEmployeeAdd.class)
    @NotNull(groups = ValidGroupEmployeeAdd.class)
    @Size(min = 3, max = 10, groups = ValidGroupEmployeeAdd.class)
    private String password;

    @NotBlank(groups = ValidGroupEmployeeAdd.class)
    @NotNull(groups = ValidGroupEmployeeAdd.class)
    @Size(min = 3, max = 10, groups = ValidGroupEmployeeAdd.class)
    private String confirmPassword;

    private String address;

    private List<UserRoleEnum> roles = new ArrayList<>();

    private List<UserRoleEnum> appRoles = new ArrayList<>();

    @Min(0)
    private Double workHourCost;

    private Boolean enabled;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Double getWorkHourCost() {
        return workHourCost;
    }

    public void setWorkHourCost(Double workHourCost) {
        this.workHourCost = workHourCost;
    }

    public List<UserRoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(List<UserRoleEnum> roles) {
        this.roles = roles;
    }

    public List<UserRoleEnum> getAppRoles() {
        return Arrays.asList(UserRoleEnum.values());
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }


}
