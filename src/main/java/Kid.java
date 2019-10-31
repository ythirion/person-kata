import lombok.Getter;

@Getter
public class Kid extends Person {
    public static final int MAX_AGE_FOR_KID = 14;
    private final String favoriteToy;

    public Kid(String name, String[] phoneNumbers, String postalCode, String favoriteToy) {
        super(name, phoneNumbers, postalCode);
        this.favoriteToy = favoriteToy;
    }
}
