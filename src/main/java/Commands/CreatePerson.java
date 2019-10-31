package Commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreatePerson {
    private final String name;
    private final String[] phoneNumbers;
    private final String postalCode;
    
    private final int age;
    private final String favoriteToy;
}
