package pl.choczaj.spring.mobilerepair.web.dto;


import pl.choczaj.spring.mobilerepair.domain.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class DeviceDto {

    private Long id;
    private String manufacturer;
    private String model;
    private String description;
    private String owner;

    private List<String> customersEmails = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getCustomersEmails() {
        return customersEmails;
    }

    public void setCustomersEmails(List<String> customersEmails) {
        this.customersEmails = customersEmails;
    }
}
