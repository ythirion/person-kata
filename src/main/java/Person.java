import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public abstract class Person {
    private final String name;
    private final List<String> phoneNumbers;
    private final String postalCode;
}
