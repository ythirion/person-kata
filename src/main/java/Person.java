import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Person {
    private final String name;
    private final String[] phoneNumbers;
    private final String postalCode;
}
