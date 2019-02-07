package pl.choczaj.spring.mobilerepair.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.choczaj.spring.mobilerepair.domain.model.*;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.DeviceRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.PartRepository;
import pl.choczaj.spring.mobilerepair.domain.repository.TaskRepository;
import pl.choczaj.spring.mobilerepair.web.dto.DeviceDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PartService {

    private PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void deleteAll() {
        partRepository.deleteAll();
    }

    public void initWithDemoParts() {
        Part[] demoParts = new Part[15];
        for (int i = 0; i < demoParts.length; i++) {
            demoParts[i] = new Part();
        }
        demoParts[0].setType("bateria");
        demoParts[0].setManufacturer("Samsung");
        demoParts[0].setModel("S6");
        demoParts[0].setDescription("3200 mAh");
        demoParts[0].setPrice(79.0);
        demoParts[0].setWorkHours(0.50);
        demoParts[0].setQuantity(25L);
        demoParts[0].setSerialNumber("456465465");

        demoParts[1].setType("wyświetlacz");
        demoParts[1].setManufacturer("Samsung");
        demoParts[1].setModel("S6");
        demoParts[1].setDescription("AMOLED");
        demoParts[1].setPrice(449.0);
        demoParts[1].setWorkHours(3.50);
        demoParts[1].setQuantity(15L);
        demoParts[1].setSerialNumber("456465987");

        demoParts[2].setType("głośnik");
        demoParts[2].setManufacturer("Samsung");
        demoParts[2].setModel("S6");
        demoParts[2].setDescription("głośnik do rozmów");
        demoParts[2].setPrice(99.0);
        demoParts[2].setWorkHours(1.25);
        demoParts[2].setQuantity(20L);
        demoParts[2].setSerialNumber("456465789");

        demoParts[3].setType("bateria");
        demoParts[3].setManufacturer("Xiaomi");
        demoParts[3].setModel("Mi6");
        demoParts[3].setDescription("3350 mAh");
        demoParts[3].setPrice(55.0);
        demoParts[3].setWorkHours(0.50);
        demoParts[3].setQuantity(12L);
        demoParts[3].setSerialNumber("123465465");

        demoParts[4].setType("wyświetlacz");
        demoParts[4].setManufacturer("Xiaomi");
        demoParts[4].setModel("Mi6");
        demoParts[4].setDescription("IPS");
        demoParts[4].setPrice(255.0);
        demoParts[4].setWorkHours(3.00);
        demoParts[4].setQuantity(8L);
        demoParts[4].setSerialNumber("123465987");

        demoParts[5].setType("głośnik");
        demoParts[5].setManufacturer("Xiaomi");
        demoParts[5].setModel("Mi6");
        demoParts[5].setDescription("głośnik do rozmów");
        demoParts[5].setPrice(80.0);
        demoParts[5].setWorkHours(1.0);
        demoParts[5].setQuantity(10L);
        demoParts[5].setSerialNumber("123465789");

        demoParts[6].setType("gniazdo ładowania");
        demoParts[6].setManufacturer("Xiaomi");
        demoParts[6].setModel("Mi6");
        demoParts[6].setDescription("USB-C");
        demoParts[6].setPrice(120.0);
        demoParts[6].setWorkHours(1.5);
        demoParts[6].setQuantity(18L);
        demoParts[6].setSerialNumber("123465123");

        demoParts[7].setType("obudowa");
        demoParts[7].setManufacturer("Xiaomi");
        demoParts[7].setModel("Mi6");
        demoParts[7].setDescription("klapka tył, czarna");
        demoParts[7].setPrice(24.0);
        demoParts[7].setWorkHours(0.5);
        demoParts[7].setQuantity(4L);
        demoParts[7].setSerialNumber("123465222");

        demoParts[8].setType("wyświetlacz");
        demoParts[8].setManufacturer("Xiaomi");
        demoParts[8].setModel("Redmi Note 5");
        demoParts[8].setDescription("IPS");
        demoParts[8].setPrice(290.0);
        demoParts[8].setWorkHours(3.00);
        demoParts[8].setQuantity(8L);
        demoParts[8].setSerialNumber("129465987");

        demoParts[9].setType("głośnik");
        demoParts[9].setManufacturer("Xiaomi");
        demoParts[9].setModel("Redmi Note 5");
        demoParts[9].setDescription("głośnik do rozmów");
        demoParts[9].setPrice(87.0);
        demoParts[9].setWorkHours(1.0);
        demoParts[9].setQuantity(9L);
        demoParts[9].setSerialNumber("129465789");

        demoParts[10].setType("bateria");
        demoParts[10].setManufacturer("Samsung");
        demoParts[10].setModel("S7");
        demoParts[10].setDescription("3200 mAh");
        demoParts[10].setPrice(83.5);
        demoParts[10].setWorkHours(0.50);
        demoParts[10].setQuantity(32L);
        demoParts[10].setSerialNumber("459776455");

        demoParts[11].setType("wyświetlacz");
        demoParts[11].setManufacturer("Samsung");
        demoParts[11].setModel("S7");
        demoParts[11].setDescription("AMOLED");
        demoParts[11].setPrice(255.0);
        demoParts[11].setWorkHours(3.50);
        demoParts[11].setQuantity(5L);
        demoParts[11].setSerialNumber("459465437");

        demoParts[12].setType("głośnik");
        demoParts[12].setManufacturer("Samsung");
        demoParts[12].setModel("S7");
        demoParts[12].setDescription("głośnik do rozmów");
        demoParts[12].setPrice(90.0);
        demoParts[12].setWorkHours(1.75);
        demoParts[12].setQuantity(52L);
        demoParts[12].setSerialNumber("459498784");

        demoParts[13].setType("gniazdo ładowania");
        demoParts[13].setManufacturer("Samsung");
        demoParts[13].setModel("S7");
        demoParts[13].setDescription("USB-C");
        demoParts[13].setPrice(100.0);
        demoParts[13].setWorkHours(1.50);
        demoParts[13].setQuantity(27L);
        demoParts[13].setSerialNumber("459475883");

        demoParts[14].setType("obudowa");
        demoParts[14].setManufacturer("Samsung");
        demoParts[14].setModel("S7");
        demoParts[14].setDescription("klapka tył, czarna");
        demoParts[14].setPrice(47.0);
        demoParts[14].setWorkHours(0.75);
        demoParts[14].setQuantity(12L);
        demoParts[14].setSerialNumber("459572022");

        for (Part part : demoParts) {
            partRepository.save(part);
        }
    }

}
