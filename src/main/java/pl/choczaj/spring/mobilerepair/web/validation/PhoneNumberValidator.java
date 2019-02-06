package pl.choczaj.spring.mobilerepair.web.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {
    private static final Pattern PHONE_PATERN = Pattern.compile("[\\+0]\\d{11}|\\d{9}|([\\+0]\\d{2}[ -]?)?(\\d{9}|(\\d{3}[ -]){2}\\d{3})");

    public void initialize(Phone constraint) {
    }

    public boolean isValid(String phone, ConstraintValidatorContext context) {
        if (phone == null) {
            return true;
        }
        Matcher matcher = PHONE_PATERN.matcher(phone);
        return matcher.matches();
    }


}
