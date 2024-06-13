package model;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users_accounts")
public class UserAccount {
    @Id
    private String accountNumber;
    private String username;
    private String password;
    private String fullName;
    private String role;
    private BigDecimal balance;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userAccount")
    private Set<Transaction> transactions;

    public UserAccount() {
    }

    public UserAccount(String accountNumber, String username, String password, String fullName, String role,
            BigDecimal balance, Set<Transaction> transactions) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
        this.balance = balance;
        this.transactions = transactions;
    }

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(Set<Transaction> transactions) {
		this.transactions = transactions;
	}

    
}
