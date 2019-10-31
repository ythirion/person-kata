import Commands.CreatePerson;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class CommandValidator {
    private static final String PHONE_NUMBER_REGEX = "^[0-9]{1,10}$";
    private static final String[] INVALID_TOYS = new String[] { "Spongebob", "Hello Kitty" };

    public static void validate(CreatePerson command) throws InvalidCreatePersonCommandException {
        List<String> validationContext = new ArrayList<>();

        checkField(command.getPostalCode(), "postal code", validationContext);
        checkField(command.getName(), "name", validationContext);
        checkPhoneNumbers(command, validationContext);
        checkFavoriteToy(command, validationContext);

        if (validationContext.size() > 0) {
            validationContext.add("Error - Validation errors have been found");
            throw new InvalidCreatePersonCommandException(validationContext.toArray(new String[0]));
        }
    }

    private static void checkFavoriteToy(CreatePerson command, List<String> validationContext) {
        if (command.getAge() < Kid.MAX_AGE_FOR_KID) {
            checkField(command.getFavoriteToy(), "favorite toy", validationContext);

            if(Arrays.stream(INVALID_TOYS).anyMatch(t -> t == command.getFavoriteToy())){
                validationContext.add("Error - " + command.getFavoriteToy() + " is not allowed as favoriteToy");
            }
        }
    }

    private static void checkPhoneNumbers(CreatePerson command, List<String> validationContext) {
        if (command.getPhoneNumbers() == null ||
                command.getPhoneNumbers().length == 0){
            validationContext.add("Error - No phone number supplied");
        } else {
            boolean containsValidPhoneNumber =
                    Arrays.stream(command.getPhoneNumbers())
                            .filter(phoneNumber -> phoneNumber != null &&
                                    !phoneNumber.isEmpty() &&
                                    phoneNumber.matches(PHONE_NUMBER_REGEX))
                            .count() > 0;

            if(!containsValidPhoneNumber){
                validationContext.add("Error - Invalid phone number");
            }
        }
    }

    private static void checkField(String fieldValue, String fieldName, List<String> validationContext){
        if (fieldValue == null || fieldValue.isEmpty()) {
            validationContext.add("Error - " + fieldName + " is not defined for the person");
        }
    }
}
