import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersonService {
    private final Logger logger;

    public Person createPerson(String name, String[] phoneNumbers,String postalCode, int age, String favoriteToy){
        if(age < 14){
            return createKid(name, phoneNumbers, postalCode, age, favoriteToy);
        }else{
            return createAdult(name, phoneNumbers, postalCode, favoriteToy);
        }
    }

    private Person createKid(String name, String[] phoneNumbers, String postalCode, int age, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Kid kid = new Kid(name, phoneNumbers, postalCode, favoriteToy);

        try{
            List<String> result = validator.validate(kid);

            if(result.size() > 0){
                logger.log("Errors in person validation");

                for(String error : result){
                    logger.log(error);
                }
                return null;
            }
        } catch(InvalidPersonException invalidPersonException){
            logger.log(invalidPersonException.getMessage());
            return null;
        }

        try {
            storePerson(kid);
        } catch(Exception exception){
            logger.log(exception.getMessage());
        }
        return kid;
    }

    private Person createAdult(String name, String[] phoneNumbers, String postalCode, String favoriteToy){
        PersonValidator validator = new PersonValidator();
        Adult adult = new Adult(name, phoneNumbers, postalCode);

        try{
            List<String> result = validator.validate(adult);

            if(result.size() > 0){
                logger.log("Errors in person validation");

                for(String error : result){
                    logger.log(error);
                }
                return null;
            }
        } catch(InvalidPersonException invalidPersonException){
            logger.log(invalidPersonException.getMessage());
            return null;
        }

        try {
            storePerson(adult);
        } catch(Exception exception){
            logger.log(exception.getMessage());
        }
        return adult;
    }

    private void storePerson(Person person) throws Exception {
        if(person == null){
            throw new Exception();
        }
    }
}