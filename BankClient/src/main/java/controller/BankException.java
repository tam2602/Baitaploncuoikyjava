package controller;

public class BankException extends Exception {

    public BankException(String message) {
        super(message);
    }

    public synchronized static void checkAccountNumber(String accountNumber) throws BankException {
        // Sử dụng regex để kiểm tra số tài khoản có đúng 7 chữ số hay không
        if (!accountNumber.matches("^\\d{7}$")) {
            throw new BankException("Số tài khoản phải gồm 7 chữ số và chỉ bao gồm các chữ số!");
        }
    }
}
