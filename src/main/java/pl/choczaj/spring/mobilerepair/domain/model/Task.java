package pl.choczaj.spring.mobilerepair.domain.model;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "accepted_at_facility_date")
    private LocalDateTime acceptedAtFacilityDate;

    @Column(name = "scheduled_repair_date")
    private LocalDate scheduledRepairDate;

    @Column(name = "repair_end_date")
    private LocalDateTime repairEndDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @ManyToOne
    private Employee employee;

    @NotBlank
    @NotNull
    @Size(min = 10, max = 500)
    @Column(name = "problem_description")
    private String problemDescription;

    @Column(name = "repair_description")
    private String repairDescription;

    @NotNull
    @ManyToOne
    private Device device;

    @NotNull
    @OneToOne
    private Part part;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    @DecimalMin("0.01")
    @Column(name = "cost_for_customer")
    private Double costForCustomer;

    @PrePersist
    private void prePersist() {
        this.registrationDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public LocalDateTime getAcceptedAtFacilityDate() {
        return acceptedAtFacilityDate;
    }

    public void setAcceptedAtFacilityDate(LocalDateTime acceptedAtFacilityDate) {
        this.acceptedAtFacilityDate = acceptedAtFacilityDate;
    }

    public LocalDate getScheduledRepairDate() {
        return scheduledRepairDate;
    }

    public void setScheduledRepairDate(LocalDate scheduledRepairDate) {
        this.scheduledRepairDate = scheduledRepairDate;
    }

    public LocalDateTime getRepairEndDate() {
        return repairEndDate;
    }

    public void setRepairEndDate(LocalDateTime repairEndDate) {
        this.repairEndDate = repairEndDate;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getRepairDescription() {
        return repairDescription;
    }

    public void setRepairDescription(String repairDescription) {
        this.repairDescription = repairDescription;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Double getCostForCustomer() {
        return costForCustomer;
    }

    public void setCostForCustomer(Double costForCustomer) {
        this.costForCustomer = costForCustomer;
    }


}
