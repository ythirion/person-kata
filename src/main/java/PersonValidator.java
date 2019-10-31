import java.util.ArrayList;
import java.util.List;

public class PersonValidator {
    public List<String> validate(Person person) throws InvalidPersonException {
        List<String> constraints = new ArrayList<String>();
        boolean hasFoundError = false;

        if (person != null) {
            if (person.postalCode != null) {
                if (person.name != null) {
                    if (person.phoneNumbers != null) {
                        for (String phoneNumber : person.phoneNumbers) {
                            if (phoneNumber != null) {
                                if (phoneNumber.isEmpty()) {
                                    constraints.add("Error - Phone number is empty");
                                    hasFoundError = true;
                                    return constraints;
                                } else {
                                    if(phoneNumber.length() < 10){
                                        constraints.add("Error - Invalid phone number");
                                        return constraints;
                                    }
                                }

                            } else {
                                constraints.add("Error - Invalid phone number");
                                return constraints;
                            }
                        }
                    }
                    else throw new InvalidPersonException("Person is null");
                }
                else constraints.add("Error - No name for the person");
            }
            else constraints.add("Error - No postal code supplied");

            if(person instanceof Kid){
                Kid kid = (Kid)person;
                if(kid.favoriteToy != null){
                    if(kid.favoriteToy.isEmpty()) {
                        constraints.add("Error - No favorite toy for kid");
                        hasFoundError = true;
                    } else {
                        switch(kid.favoriteToy){
                            case "Spongebob":
                                constraints.add("Error - Spongebob is not allowed as favoriteToy");
                            case "Hello Kitty":
                                constraints.add("Error - Hello Kitty is not allowed as favoriteToy");
                        }
                    }
                } else{

                }
            }
        }
        else {
            throw new InvalidPersonException("Person is null");
        }

        if(hasFoundError){
            constraints.add("Error - Validation errors have been found");
        }

        return constraints;
    }
}
