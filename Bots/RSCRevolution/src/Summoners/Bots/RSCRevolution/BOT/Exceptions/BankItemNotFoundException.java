package Summoners.Bots.RSCRevolution.BOT.Exceptions;

public class BankItemNotFoundException extends Exception {
    int id;

    public BankItemNotFoundException(int id) {
        this.id = id;
    }

    public int getId() { return id; }
}