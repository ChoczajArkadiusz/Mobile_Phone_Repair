package pl.choczaj.spring.mobilerepair.web.dto;

import pl.choczaj.spring.mobilerepair.domain.model.Device;
import pl.choczaj.spring.mobilerepair.domain.model.Part;
import pl.choczaj.spring.mobilerepair.domain.model.TaskStatus;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskDto {

    private Long id;
    private LocalDateTime registrationDate;
    private LocalDateTime acceptedAtFacilityDate;
    private LocalDate scheduledRepairDate;
    private LocalDateTime repairEndDate;
    private LocalDateTime deliveryDate;
    private String employeeEmail;

    @Size(min = 10, max = 500)
    private String problemDescription;
    private String repairDescription;
    private Device device;
    private Part part;
    private TaskStatus status;
    private Double costForCustomer;

    private List<String> employeesEmails = new ArrayList<>();
    private List<Part> parts = new ArrayList<>();

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

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
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

    public List<String> getEmployeesEmails() {
        return employeesEmails;
    }

    public void setEmployeesEmails(List<String> employeesEmails) {
        this.employeesEmails = employeesEmails;
    }

    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }


}
