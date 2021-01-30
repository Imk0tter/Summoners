package Summoners.Bots.RSCRevolution.BOT.Windows;

import client.Class79;

public class BankItem {
    BankWindow bankWindow;
    Class79 bankItem;
    public static BankItem GetBankItem(BankWindow bankWindow, Class79 bankItem) {
        return new BankItem(bankWindow, bankItem);
    }
    public BankItem(BankWindow bankWindow, Class79 bankItem) {
        this.bankWindow = bankWindow;
        this.bankItem = bankItem;
    }
    public int getItemPosition() {
        return position();
    }

    public int getItemId() {
        return itemId();
    }
    public int getItemCount() {
        return itemCount();
    }
    public int position() {
        return bankItem.anInt1771;
    }
    public int itemId() {
        return bankItem.anInt1772;
    }
    public int itemCount() {
        return bankItem.anInt1774;
    }
}
