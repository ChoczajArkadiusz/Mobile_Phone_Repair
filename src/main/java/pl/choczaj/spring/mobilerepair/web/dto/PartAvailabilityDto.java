package pl.choczaj.spring.mobilerepair.web.dto;

public class PartAvailabilityDto {

    private Long quantityReserved;

    private Long quantityAvailable;

    public Long getQuantityReserved() {
        return quantityReserved;
    }

    public void setQuantityReserved(Long quantityReserved) {
        this.quantityReserved = quantityReserved;
    }

    public Long getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Long quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }


}
