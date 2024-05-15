package hu.nye.vpe.nyeprogenv.user.exceptions;

/**
 * Class UserNotFoundException.
 */
public class UsernameNotFoundException extends Throwable {

    /**
     * UsernameNotFoundException.
     *
     * @param message is an exception message.
     */
    public UsernameNotFoundException (String message) {
        super(message);
    }
}