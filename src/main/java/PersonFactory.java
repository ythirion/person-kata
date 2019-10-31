public class PersonFactory {
    public static Person create(String name, String[] phoneNumbers,String postalCode, int age, String favoriteToy){
        return age < Kid.MAX_AGE_FOR_KID ?
                new Kid(name, phoneNumbers, postalCode, favoriteToy) :
                new Adult(name, phoneNumbers, postalCode);
    }
}