import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PersonServiceTests{

    @Test
    public void a_person_without_valid_phone_number_should_not_be_created() throws Exception{
        Logger logger = Mockito.mock(Logger.class);
        PersonService personService = new PersonService(logger);

        try{
            personService.createPerson("Homer Simpson", new String[]{"064678090988"}, "SP42", 20, null);
        } catch(UnableToCreatePersonException exception){
            Assert.assertEquals("Error - Invalid phone number\nError - Validation errors have been found", exception.getMessage());
            ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);
            verify(logger).log(argument.capture());
            Assert.assertEquals(argument.getValue(), exception.getMessage());
        }
    }

    @Test
    public void a_person_with_an_age_under_14_should_create_a_kid() throws Exception{
        Logger logger = Mockito.mock(Logger.class);
        PersonService personService = new PersonService(logger);
        Person person = personService.createPerson("Homer Simpson", new String[]{"0454646464"}, "SP42", 12, "ValidToy");

        Assert.assertNotNull(person);
        Assert.assertTrue(person instanceof Kid);
        Assert.assertEquals("Homer Simpson", person.getName());
        Assert.assertEquals("SP42", person.getPostalCode());
        Assert.assertEquals(1, person.getPhoneNumbers().length);
        Assert.assertEquals("0454646464", person.getPhoneNumbers()[0]);
        Assert.assertEquals("ValidToy", ((Kid) person).getFavoriteToy());
        verify(logger, times(0)).log(any());
    }
}