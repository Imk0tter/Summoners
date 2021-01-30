package Summoners.Bots.RSCRevolution.BOT.Windows;

import Summoners.Bots.RSCRevolution.BOT.Exceptions.BankItemNotFoundException;
import Summoners.Bots.RSCRevolution.BOT.Exceptions.BankSlotNotFoundException;
import client.Class44;
import client.Class79;

import java.util.ArrayList;

public class BankWindow {
    public Class44 bankWindow;

    public static BankWindow GetBankWindow(Class44 bankWindow) {
        return new BankWindow(bankWindow);
    }
    public BankWindow(Class44 bankWindow) {
        this.bankWindow = bankWindow;
    }

    public void closeWindow() {
        this.bankWindow.method623();
    }

    public ArrayList<BankItem> getBankItems() {
        ArrayList<BankItem> bankItems = new ArrayList<BankItem>();

        for (Class79 bankItem : this.bankWindow.anArrayList727) {
            bankItems.add(BankItem.GetBankItem(this, bankItem));
        }
        return bankItems;
    }
    public void clearBankItems() {
        this.bankWindow.method649();
    }

    public void addBankItem(int position, int itemId, int itemCount) {
       this.bankWindow.method636(position, itemId, itemCount);
    }

    public BankItem getBankItemBySlot(int slot) throws BankSlotNotFoundException {
        ArrayList<BankItem> bankItems = getBankItems();
        for (int i = 0; i < bankItems.size(); ++i) {
            BankItem b = (BankItem)bankItems.get(i);
            if (b.position() == slot) return b;
        }
        throw new BankSlotNotFoundException(slot);
    }
    public BankItem getBankItemById(int id) throws BankItemNotFoundException {
        ArrayList<BankItem> bankItems = getBankItems();
        for (int i = 0; i < bankItems.size(); ++i) {
            BankItem b = (BankItem)bankItems.get(i);
            if (b.itemId() == id) return b;
        }
        throw new BankItemNotFoundException(id);
    }

}
