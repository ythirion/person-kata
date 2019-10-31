import lombok.NonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonValidator {
    private static final String PHONE_NUMBER_REGEX = "^[0-9]{1,10}$";
    private static final String[] INVALID_TOYS = new String[] { "Spongebob", "Hello Kitty" };

    public String[] validate(@NonNull Person person) {
        List<String> constraints = new ArrayList<String>();

        check(person.getPostalCode(), "postal code", constraints);
        check(person.getName(), "name", constraints);
        checkPhoneNumbers(person, constraints);
        checkFavoriteToy(person, constraints);

        if (constraints.size() > 0) {
            constraints.add("Error - Validation errors have been found");
        }

        return constraints.toArray(new String[0]);
    }

    private void checkFavoriteToy(Person person, List<String> constraints) {
        if (person instanceof Kid) {
            Kid kid = (Kid) person;

            check(kid.getFavoriteToy(), "favorite toy", constraints);

            if(Arrays.stream(INVALID_TOYS).anyMatch(t -> t == kid.getFavoriteToy())){
                constraints.add("Error - " + kid.getFavoriteToy() + " is not allowed as favoriteToy");
            }
        }
    }

    private void checkPhoneNumbers(Person person, List<String> constraints) {
        if (person.getPhoneNumbers() == null ||
                person.getPhoneNumbers().length == 0){
            constraints.add("Error - No phone number supplied");
        } else {
            boolean containsValidPhoneNumber =
                    Arrays.stream(person.getPhoneNumbers())
                            .filter(phoneNumber -> phoneNumber != null &&
                                    !phoneNumber.isEmpty() &&
                                    phoneNumber.matches(PHONE_NUMBER_REGEX))
                            .count() > 0;

            if(!containsValidPhoneNumber){
                constraints.add("Error - Invalid phone number");
            }
        }
    }

    private void check (String fieldValue, String fieldName, List < String > constraints){
        if (fieldValue == null || fieldValue.isEmpty()) {
            constraints.add("Error - " + fieldName + " is not defined for the person");
        }
    }
}
