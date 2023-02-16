/**
 * @author Odilov_Zafarjon
 * @link https://t.me/zafarzhon_odilov
 */
public interface EmailService {
    void sendPasswordResetEmail(String email, String code);
}
