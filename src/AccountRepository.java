/**
 * @author Odilov_Zafarjon
 * @link https://t.me/zafarzhon_odilov
 */
public interface AccountRepository {
    public Account findByEmail(String email);

    public void update(Account account);
}
