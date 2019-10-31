import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class PersonServiceTests{

    @Test
    public void a_person_without_phone_number_should_not_be_created(){
        Logger logger = Mockito.mock(Logger.class);

        PersonService personService = new PersonService(logger);
        Person person = personService.createPerson("Homer Simpson", null, "SP42", 20, null);

        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
        verify(logger).log(argument.capture());
        Assert.assertEquals(argument.getValue(), "Person is null");

        Assert.assertNull(person);
    }

    @Test
    public void a_person_with_an_age_under_14_should_create_a_kid(){
        PersonService personService = new PersonService(new Logger());
        Person person = personService.createPerson("Homer Simpson", new String[]{"0454646464"}, "SP42", 12, "ValidToy");

        Assert.assertNotNull(person);
        Assert.assertTrue(person instanceof Kid);
    }
}