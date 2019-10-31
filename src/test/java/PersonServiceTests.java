import org.junit.Assert;
import org.junit.Test;

public class PersonServiceTests{

    @Test
    public void a_person_without_phone_number_should_not_be_created(){
        PersonService personService = new PersonService();
        Person person = personService.createPerson("Homer Simpson", null, "SP42");

        Assert.assertNull(person);
    }
}