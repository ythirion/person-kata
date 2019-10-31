import lombok.Getter;

@Getter
public class Kid extends Person {
    private final String favoriteToy;

    public Kid(String name, String[] phoneNumbers, String postalCode, String favoriteToy) {
        super(name, phoneNumbers, postalCode);
        this.favoriteToy = favoriteToy;
    }
}
