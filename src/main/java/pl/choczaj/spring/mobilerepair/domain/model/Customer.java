package pl.choczaj.spring.mobilerepair.domain.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.LinkedList;
import java.util.List;

@Entity(name = "customers")
public class Customer extends User {

    @OneToMany(mappedBy = "owner")
    private List<Device> devices = new LinkedList<>();

    public List<Device> getDevices() {
        return devices;
    }


}
