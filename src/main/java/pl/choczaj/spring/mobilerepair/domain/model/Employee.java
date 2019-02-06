package pl.choczaj.spring.mobilerepair.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "employees")
public class Employee extends User {

    @Column(name = "work_hour_cost")
    private Double workHourCost;

    @OneToMany(mappedBy = "employee")
    private List<Task> tasks = new ArrayList<Task>();


    public Double getWorkHourCost() {
        return workHourCost;
    }

    public void setWorkHourCost(Double workHourCost) {
        this.workHourCost = workHourCost;
    }


}
