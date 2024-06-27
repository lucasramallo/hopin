package github.lucasramallo.hopin.core.domain.customer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidations {
    public static void validateName(String name) {
        Pattern pattern = Pattern.compile("^[A-ZÀ-ÿ][A-Za-zÀ-ÿ ]{5,50}$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid name!");
        }
    }

    public static void validateEmial(String name) {
        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(name);

        if (!matcher.matches()) {
            throw new RuntimeException("Invalid email!");
        }
    }
}
