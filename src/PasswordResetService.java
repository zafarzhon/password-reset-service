/**
 * @author Odilov_Zafarjon
 * @link https://t.me/zafarzhon_odilov
 */
public final class PasswordResetService {

    AccountRepository accountRepository;

    AccountNotFoundByEmailHandler accountNotFoundByEmailHandler;

    AccountNotActivHandler accountNotActivHandler;

    EmailService emailService;
    VerificationCodeGenerator verificationCodeGenerator;

    public PasswordResetService(AccountRepository accountRepository,
                                AccountNotFoundByEmailHandler accountNotFoundByEmailHandler,
                                AccountNotActivHandler accountNotActivHandler, EmailService emailService,
                                VerificationCodeGenerator verificationCodeGenerator) {
        this.accountRepository = accountRepository;
        this.accountNotFoundByEmailHandler = accountNotFoundByEmailHandler;
        this.accountNotActivHandler = accountNotActivHandler;
        this.emailService = emailService;
        this.verificationCodeGenerator = verificationCodeGenerator;
    }

    public String reset(String email) {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            return accountNotFoundByEmailHandler.handle(email);
        } else if (account.isNotActive()) {
            String result = accountNotActivHandler.hanlde(account);
            if (result != null) {
                return result;
            }
        }
        String code = verificationCodeGenerator.generate();
        account.setCode(code);
        accountRepository.update(account);
        emailService.sendPasswordResetEmail(email, code);
        return "password_reset_success.html";

    }

}
