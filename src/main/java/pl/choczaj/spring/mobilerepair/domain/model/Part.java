package pl.choczaj.spring.mobilerepair.domain.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String manufacturer;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String model;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String type;

    private String description;

    @Column(name = "serial_number")
    private String serialNumber;

    @NotNull
    @Min(0)
    private Long quantity;

    @NotNull
    @DecimalMin("0.01")
    private Double price;

    @NotNull
    @DecimalMin("0.25")
    @Column(name = "work_hours")
    private Double workHours;

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

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getWorkHours() {
        return workHours;
    }

    public void setWorkHours(Double workHours) {
        this.workHours = workHours;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
