package code.accounts;

import java.util.UUID;

public class NetbankingAccount extends Account {
    private final String netbankingId;
    private final String password;

    public NetbankingAccount(UUID id, UUID userId, String netbankingId, String password, int balance) {
        super(id, userId, AccountType.NET_BANKING, balance);
        this.netbankingId = netbankingId;
        this.password = password;
    }

    public String getNetbankingId() {
        return netbankingId;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean validate() {
        return netbankingId != null && !netbankingId.isBlank()
                && password != null && !password.isBlank();
    }
}
