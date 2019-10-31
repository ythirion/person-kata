import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class PersonValidator {
    public List<String> validate(@NonNull Person person) {
        List<String> constraints = new ArrayList<String>();

        check(person.getPostalCode(), "postal code", constraints);
        check(person.getName(), "name", constraints);

        if (person.getPhoneNumbers() != null && person.getPhoneNumbers().length > 0) {
            for (String phoneNumber : person.getPhoneNumbers()) {
                if (phoneNumber != null) {
                    if (phoneNumber.isEmpty()) {
                        constraints.add("Error - Phone number is empty");
                        return constraints;
                    } else {
                        if (phoneNumber.length() < 10) {
                            constraints.add("Error - Invalid phone number");
                            return constraints;
                        }
                    }

                } else {
                    constraints.add("Error - Invalid phone number");
                    return constraints;
                }
            }
        }else constraints.add("Error - No phone number supplied");

        if (person instanceof Kid) {
            Kid kid = (Kid) person;
            if (kid.getFavoriteToy() != null) {
                if (kid.getFavoriteToy().isEmpty()) {
                    constraints.add("Error - No favorite toy for kid");
                } else {
                    switch (kid.getFavoriteToy()) {
                        case "Spongebob":
                            constraints.add("Error - Spongebob is not allowed as favoriteToy");
                        case "Hello Kitty":
                            constraints.add("Error - Hello Kitty is not allowed as favoriteToy");
                    }
                }
            } else {

            }
        }

        if (constraints.size() > 0) {
            constraints.add("Error - Validation errors have been found");
        }

        return constraints;
    }

    private void check (String fieldValue, String fieldName, List < String > constraints){
        if (fieldValue == null ||
                fieldValue.isEmpty()) {
            constraints.add("Error - " + fieldName + " is not defined for the person");
        }
    }
}
