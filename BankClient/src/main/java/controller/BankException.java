package controller;

public class BankException extends Exception {

	public BankException(String message) {
		super(message);
	}
	public synchronized static void checkAccountNumber(String accountnumber) throws BankException {
		if(accountnumber.length()!=7) {
			throw new BankException("Số tài khoản phải gồm 7 chữ số!");
		}
		try {
			int check= Integer.parseInt(accountnumber);
		} catch (Exception e) {
			throw new BankException("Số tài khoản chỉ gồm các chữ số!");
		}
	}
}
