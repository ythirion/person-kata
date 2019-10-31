import lombok.Getter;

import java.util.List;

@Getter
public class Kid extends Person {
    private final String favoriteToy;

    public Kid(String name, List<String> phoneNumbers, String postalCode, String favoriteToy) {
        super(name, phoneNumbers, postalCode);
        this.favoriteToy = favoriteToy;
    }
}
