package Summoners.Bots.RSCRevolution.BOT.Exceptions;

public class BankSlotNotFoundException extends Exception {
    int slot;

    public BankSlotNotFoundException(int slot) {
        this.slot = slot;
    }

    public int getSlot() { return slot; }
}