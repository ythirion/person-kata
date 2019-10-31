import Commands.CreatePerson;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PersonService {
    private final Logger logger;

    public Person createPerson(CreatePerson command) throws Exception{
        try{
            CommandValidator.validate(command);

            Person person = PersonFactory.create(
                    command.getName(),
                    command.getPhoneNumbers(),
                    command.getPostalCode(),
                    command.getAge(),
                    command.getFavoriteToy()
            );
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