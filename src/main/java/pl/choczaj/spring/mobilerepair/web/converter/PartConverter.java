package pl.choczaj.spring.mobilerepair.web.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.choczaj.spring.mobilerepair.domain.model.Part;
import pl.choczaj.spring.mobilerepair.domain.repository.PartRepository;

public class PartConverter implements Converter<String, Part> {

    private PartRepository partRepository;

    @Autowired
    public void PartConverter(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    @Override
    public Part convert(String s) {
        return partRepository.findById(Long.parseLong(s)).orElse(null);
    }


}
