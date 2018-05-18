package app.sportmates_backend.class_interface;
/**
 * Ez az osztály egy felhasználónak az azonosítását szolgálja a felhasználónév vagy e-mail és jelszó párral.
 * @author szendrei
 * @author polozgai
 */
public class AuthUser {
    private String identifier;
    private String password;
    /**
     * Visszadja a felhasználó valamelyik azonosítóját az e-mail vagy felhasználónév közül.
     * @return Felhasználó azonosítója.
     */
    public String getIdentifier() {
        return identifier;
    }
    /**
     * Visszaadja a felhasználó jelszavát.
     * @return Felhasználó jelszava.
     */
    public String getPassword() {
        return password;
    }
}