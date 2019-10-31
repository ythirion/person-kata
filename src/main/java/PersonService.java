import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PersonService {
    private final Logger logger;

    public Person createPerson(String name, String[] phoneNumbers,String postalCode, int age, String favoriteToy) throws Exception{
        try{
            PersonValidator validator = new PersonValidator();
            Person person = age < 14 ?
                    new Kid(name, phoneNumbers, postalCode, favoriteToy) :
                    new Adult(name, phoneNumbers, postalCode);

            List<String> result = validator.validate(person);

            if(result.size() > 0){
                throw new UnableToCreatePersonException(result.stream().toArray(String[]::new));
            }
            this.storePerson(person);

            return person;
        }
        catch(Exception ex){
            logger.log(ex.getMessage());
            throw ex;
        }
    }

    private void storePerson(Person person) throws Exception {
        if(person == null){
            throw new Exception();
        }
    }
}