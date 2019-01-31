package pl.choczaj.spring.mobilerepair.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.choczaj.spring.mobilerepair.domain.model.Customer;
import pl.choczaj.spring.mobilerepair.domain.repository.CustomerRepository;

public class CustomerConverter implements Converter<String, Customer> {

    private CustomerRepository customerRepository;

    @Autowired
    public void CustomerConverter(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer convert(String s) {
        return customerRepository.findById(Long.parseLong(s)).orElse(null);
    }


}
