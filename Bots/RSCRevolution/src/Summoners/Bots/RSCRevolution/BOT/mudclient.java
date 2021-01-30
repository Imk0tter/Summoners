package Summoners.Bots.RSCRevolution.BOT;

import Summoners.Bots.RSCRevolution.BOT.Definitions.ItemDefinition;
import Summoners.Bots.RSCRevolution.BOT.Exceptions.*;
import Summoners.Bots.RSCRevolution.BOT.Objects.*;
import Summoners.Bots.RSCRevolution.BOT.Scripting.*;
import Summoners.Bots.RSCRevolution.BOT.Windows.BankItem;
import Summoners.Bots.RSCRevolution.BOT.Windows.BankWindow;
import Summoners.Bots.RSCRevolution.util.logger.Logger;

import client.*;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class mudclient extends Class78_Sub1 implements IScriptMethods {

    Logger logger;
    public mudclient(Interface2 interface2, Logger logger) {
        super(interface2);
        this.logger = logger;
        writeln("Created a new instance of mudclient");
        LoadScripts();
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    public void writeln(String text) {
        write(text + "\n");
    }
    public void write(String text) {
        if (logger != null) {
            logger.print(text);
        }
        else {
            System.out.writeBytes(text.getBytes());
        }
    }


    /*********************************************************/
    /***              Client functions and variables       ***/
    /*********************************************************/
    /*** Bank Commands ***/ //TODO: Open Bank
    public void depositInventory() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(9);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void saveInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();

        if (this.isBankOpen()) {
            createPacket(22);
            putByte(8);
            putUnsignedInt(slotNumber);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void loadInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(7);
            putUnsignedInt(slotNumber);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void toggleAsNotes(boolean asNotes) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(6);
            putByte(asNotes ? 1 : 0);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void depositEquipment() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(5);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void swapInInventory(int inventorySlotA, int inventorySlotB, boolean swap) throws BankNotOpenException, InventorySlotEmptyException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {

            if (inventorySlotA > this.clientGetInventoryItemCount() || inventorySlotB > this.clientGetInventoryItemCount()) {
                throw new InventorySlotEmptyException();
            } else if (inventorySlotA != inventorySlotB) {
                createPacket(22);
                putByte(3);
                putByte(1);
                putByte(swap ? 1 : 2);
                putUnsignedInt(inventorySlotA);
                putUnsignedInt(inventorySlotB);
                sendPacket();
                this.method1288(0);
            }
        } else {
            throw new BankNotOpenException();
        }
    }
    public void swapInBankById(int itemIdA, int itemIdB) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        swapInBankById(itemIdA, itemIdB, true);
    }
    public void swapInBankById(int itemIdA, int itemIdB, boolean swap) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {

            BankWindow bankWindow = this.clientGetBankWindow();

            BankItem itemA = bankWindow.getBankItemById(itemIdA);
            BankItem itemB = bankWindow.getBankItemById(itemIdB);

            if (itemA != null && itemB != null) {
                createPacket(22);
                putByte(3);
                putByte(0);
                putByte(swap ? 1 : 2);
                putUnsignedInt(itemA.getItemPosition());
                putUnsignedInt(itemB.getItemPosition());
                sendPacket();
                this.method1288(0);
            } else {
            }
        } else {
            throw new BankNotOpenException();
        }
    }
    public void swapInBankBySlot(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {

            swapInBankBySlot(bankSlotA, bankSlotB, true);
        } else {
            throw new BankNotOpenException();
        }

    }
    public void swapInBankBySlot(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            swapInBank(bankSlotA, bankSlotB, swap);
        } else {
            throw new BankNotOpenException();
        }
    }
    public void swapInBank(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            swapInBank(bankSlotA, bankSlotB, true);
        } else {
            throw new BankNotOpenException();
        }
    }
    public void swapInBank(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {

            BankWindow bankWindow = this.clientGetBankWindow();

            if (bankWindow.getBankItemBySlot(bankSlotA) != null && bankWindow.getBankItemBySlot(bankSlotB) != null) {
                createPacket(22);
                putByte(3);
                putByte(0);
                putByte(swap ? 1 : 2);
                putUnsignedInt(bankSlotA);
                putUnsignedInt(bankSlotB);
                sendPacket();
                this.method1288(0);
            }
        } else {
            throw new BankNotOpenException();
        }
    }
    public void closeBank() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(2);
            sendPacket();
        } else {
            throw new BankNotOpenException();
        }
    }
    public void depositItemByInventorySlot(int inventorySlot, int count) throws
            BankNotOpenException, InventorySlotEmptyException, InventoryItemCountBelowSpecifiedException, InventoryItemNotFoundException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            int id = getInventoryItemIdFromSlot(inventorySlot);
            int inventoryCount = getInventoryItemCountById(id);
            if (inventoryCount < count) throw new InventoryItemCountBelowSpecifiedException(count, inventoryCount);
            else {
                depositItem(id, count);
            }
        } else {
            throw new BankNotOpenException();
        }
    }
    public void depositItemByBankSlot(int bankSlot, int count) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            BankWindow bankWindow = this.clientGetBankWindow();
            BankItem bankItem = bankWindow.getBankItemBySlot(bankSlot);

            if (bankItem.getItemCount() >= count) { // Should never be false
                depositItem(bankItem.getItemId(), count);
            } else {
                throw new BankItemCountBelowSpecifiedException(count, bankItem.getItemCount());
            }
        } else { // SHould be unreachable code
            throw new BankNotOpenException();
        }

    }
    public void depositItembyId(int itemId, int itemCount) throws
            BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        depositItem(itemId, itemCount);
    }
    public void depositItem(int itemId, int itemCount) throws BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            createPacket(22);
            putByte(1);
            putUnsignedShort(itemId);
            if (itemCount > getInventoryItemCountById(itemId)) {
                throw new InventoryItemCountBelowSpecifiedException(itemCount, getInventoryItemCountById(itemId));
            } else if (itemCount > 0) {
                putUnsignedInt(itemCount);
                sendPacket();
                this.method1288(0);
                this.method1284(0);
                this.method1279(0);
            } else {
                throw new InventoryItemNotFoundException(itemId);
            }
        } else {
            throw new BankNotOpenException();
        }
    }
    public void withdrawItemByInventorySlot(int inventorySlot, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int itemId = this.getInventoryItemIdFromSlot(inventorySlot);

        withdrawItemById(itemId, itemCount);


    }
    public boolean isBankOpen() {
        return clientGetIsBankOpen();
    }
    public boolean isLoggedIn() {
        if (this.anEnumerator4_1425 == Enumerator4.LOGIN || (this.aBoolean1454 && this.anInt1361 > 0)) return false;
        return true;
    }
    public void withdrawItemByBankSlot(int bankSlot, int itemCount) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            BankWindow bankWindow = this.clientGetBankWindow();

            BankItem bankItem = bankWindow.getBankItemBySlot(bankSlot);

            if (bankItem != null) {
                if (bankItem.getItemCount() < itemCount) {
                    throw new BankItemCountBelowSpecifiedException(itemCount, bankItem.getItemCount());
                } else {
                    withdrawItem(bankItem.getItemId(), itemCount);
                }
            } else {
                throw new BankSlotNotFoundException(bankSlot); //Should be unreachable
            }
        } else {
            throw new BankNotOpenException();
        }

    }
    public void withdrawItemById(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        withdrawItem(itemId, itemCount);

    }
    public void withdrawItem(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (this.isBankOpen()) {
            BankWindow bankWindow = this.clientGetBankWindow();

            BankItem var2 = bankWindow.getBankItemById(itemId);

            if (var2 != null) {
                if (var2.getItemCount() < itemCount)
                    throw new BankItemCountBelowSpecifiedException(itemCount, var2.getItemCount());
                else {
                    createPacket(22);
                    putByte(0);
                    putUnsignedShort(itemId);
                    putUnsignedInt(itemCount);
                    sendPacket();
                    bankWindow.bankWindow.aBoolean735 = false;
                    bankWindow.bankWindow.anInt725 = -1;
                    //this.method178(0); = method1288(0)
                    //this.method168(0); = method1284(0)
                    //this.method175(0); = method1279(0)
                    this.method1288(0);
                    this.method1284(0);
                    this.method1279(0);
                }
            } else {
                throw new BankItemNotFoundException(itemId); //Should be unreachable
            }
        } else {
            throw new BankNotOpenException();
        }

    }
    /*** End Bank Functions.methods - Finished***/


    /*** Inventory Functions ***/

    /*
          case 23: //ITEM_USE_ITEM
         createPacket(91);
         putUnsignedShort(var6);
         putUnsignedShort(var3);
         sendPacket();
         this.inventoryItemPosition = -1;
         if(var9 == null) {
            break;
         }
      case 24: //ITEM_REMOVE_EQUIPPED
         createPacket(170);
         putUnsignedShort(var6);
         sendPacket();
         if(var9 == null) {
            break;
         }
      case 25: //ITEM_EQUIP
         createPacket(169);
         putUnsignedShort(var6);
         sendPacket();
         if(var9 == null) {
            break;
         }
      case 26: //ITEM_COMMAND
         createPacket(90);
         putUnsignedShort(var6);
         sendPacket();
         if(var9 == null) {
            break;
         }
      case 27: //ITEM_USE
         label250: {
            this.inventoryItemPosition = var6;
            if(this.inventoryItemPosition > 30) {
               this.aString1176 = GameData.getItemDefinition(this.equipmentItemsType[this.inventoryItemPosition - 30]).getName();
               if(var9 == null) {
                  break label250;
               }
            }

            this.aString1176 = GameData.getItemDefinition(this.inventoryItemsType[this.inventoryItemPosition]).getName();
         }

         if(Config.method361()) {
            break;
         }

         this.anInt1251 = 0;
         if(var9 == null) {
            break;
         }
      case 28: //ITEM_DROP
         createPacket(246);
         putUnsignedShort(var6);
         var10 = 1;
         if(GameData.getItemDefinition(this.inventoryItemsType[var6]).isStackable()) {
            var10 = this.getInventoryItemCountTotal(this.inventoryItemsType[var6]);
         }

         putUnsignedInt(var10);
         sendPacket();
         if(!Config.method361()) {
            this.anInt1251 = 0;
         }

         this.inventoryItemPosition = -1;
         this.method1096(Enum_Sub2.INVENTORY, (String)null, (String)null, "Dropping " + GameData.getItemDefinition(this.inventoryItemsType[var6]).getName(), 0);
         if(var9 == null) {
            break;
         }
      case 29: //ITEM_DROP_X
         this.anInt1143 = var6;
         this.method1094(new String[]{"Enter number of items to drop and press enter"}, Enum_Sub6.DROP_X, true);
         this.aString1018 = "" + this.getInventoryItemCountTotal(this.inventoryItemsType[var6]);
         if(var9 == null) {
            break;
         }
      case 30: //ITEM_DROP_ALL
         createPacket(246);
         putUnsignedShort(var6);
         putUnsignedInt(this.getInventoryItemCountTotal(this.inventoryItemsType[var6]));
         sendPacket();
         if(var9 == null) {
            break;
         }
     */
    public void equipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (ItemDefinition.GetItemDefinition(id).isEquipable()) {
            for (int i = 0; i < this.clientGetInventoryItemCount(); ++i) {
                ArrayList<Integer> inventoryItemsTypes = this.clientGetInventoryItemsType();
                if (id == inventoryItemsTypes.get(i)) {
                    equipInventoryItemBySlot(i);
                    return;
                }
            }
        } else {
            throw new InventoryNotEquippableException();
        }
    }
    public void equipInventoryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (slot >= this.clientGetInventoryItemCount()) throw new InventoryItemCountBelowSpecifiedException(slot, this.clientGetInventoryItemCount());
        else {

            if (ItemDefinition.GetItemDefinition(this.clientGetInventoryItemsType().get(slot)).isEquipable()) {
                createPacket(169);
                putUnsignedShort(slot);
                sendPacket();
            }
            else {
                throw new InventoryNotEquippableException();
            }
        }

    }

    public void unequipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (ItemDefinition.GetItemDefinition(id).isEquipable()) {
            for (int i = 0; i < this.clientGetEquipmentItemsType().size(); ++i) {
                if (this.clientGetEquipmentItemsType().get(i) == id) {
                    createPacket(170);
                    putUnsignedShort(i);
                    sendPacket();
                    return;
                }
            }
            throw new InventoryItemNotFoundException(id);
        } else {
            throw new InventoryNotEquippableException();
        }
    }
    public void unequipInvenotryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<Integer> equipmentItemsType = this.clientGetEquipmentItemsType();

        if (equipmentItemsType.size() <= slot) {
            if (ItemDefinition.GetItemDefinition(equipmentItemsType.get(slot)).isEquipable()) {
                createPacket(170);
                putUnsignedShort(slot);
                sendPacket();
            }
            else {
                throw new InventoryNotEquippableException();
            }
        }
        else
        {
            throw new InventoryItemCountBelowSpecifiedException(slot, this.clientGetEquipmentItemsType().size());
        }
    }

    public void useInventoryItemOnItem(int slotA, int slotB) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (slotA != slotB) {
            if (slotA > this.clientGetInventoryItemsType().size())
                throw new InventoryItemSlotSpecifiedBeyondFoundException(slotA, this.clientGetInventoryItemsType().size());
            if (slotB > this.clientGetInventoryItemsType().size())
                throw new InventoryItemSlotSpecifiedBeyondFoundException(slotB, this.clientGetInventoryItemsType().size());
            createPacket(91);
            putUnsignedShort(slotA);
            putUnsignedShort(slotB);
            sendPacket();
        }

    }
    public void dropInventoryItemById(int itemId) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int slot = getInventoryItemSlotPosition(itemId);
        int var10 = 1;
        if (ItemDefinition.GetItemDefinition(itemId).isStackable()) {
            ArrayList<Integer> inventoryItemsStackCount = this.clientGetInventoryItemsStackCount();

            var10 = inventoryItemsStackCount.get(slot);
        }
        dropInventoryItemById(itemId, var10);
    }
    public void dropInventoryItemById(int itemId, int count) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int slot = getInventoryItemSlotPosition(itemId);
        int var10 = 1;

        ArrayList<Integer> inventoryItemsStackCount = clientGetInventoryItemsType();

        if (ItemDefinition.GetItemDefinition(inventoryItemsStackCount.get(slot)).isStackable()) {
            if (count > inventoryItemsStackCount.get(slot)) {
                throw new InventoryItemCountBelowSpecifiedException(count, inventoryItemsStackCount.get(slot));
            }
            var10 = count;
        }

        createPacket(246);
        putUnsignedShort(slot);
        putUnsignedInt(var10);
        sendPacket();

    }

    public void commandInventoryItembyId(int itemId) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int slot = getInventoryItemSlotPosition(itemId);

        commandInventoryItemBySlot(slot);
    }
    public void commandInventoryItemBySlot(int slot) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (slot > this.clientGetInventoryItemsType().size()) throw new InventoryItemSlotSpecifiedBeyondFoundException(slot, this.clientGetInventoryItemsType().size());
        createPacket(90);
        putUnsignedShort(slot);
        sendPacket();
    }

    public int getInventoryEmptySlotCount() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return 30 - this.clientGetInventoryItemCount();
    }
    public int getInventoryItemSlotPosition(int itemId) throws InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getInventoryItemSlotPosition(itemId, 0);
    }
    public int getInventoryItemCountById(int itemId) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        //int[] inventoryItemsTypes = this.getInventoryItemsTypes();
        //int[] inventoryItemsCount = this.getInventoryItemsStackCounts();

        ArrayList<Integer> inventoryItemsType = this.clientGetInventoryItemsType();
        ArrayList<Integer> inventoryItemsStackCount = this.clientGetInventoryItemsStackCount();

        int inventoryItemsTotal = 0;
        for (int i = 0; i < this.clientGetInventoryItemCount(); ++i) {
            if (inventoryItemsType.get(i) == itemId) {
                inventoryItemsTotal += inventoryItemsStackCount.get(i);
            }
        }
        return inventoryItemsTotal;
    }
    public int getInventoryItemIdFromSlot(int slot) throws InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();

        ArrayList<Integer> inventoryItemsTypes =  this.clientGetInventoryItemsType();
        if (slot > this.clientGetInventoryItemCount()) throw new SlotEmptyException(slot);
        else return inventoryItemsTypes.get(slot);
    }
    public int getInventoryItemSlotPosition(int itemId, int numberToFind) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int numberFound = 0;

        ArrayList<Integer> inventoryItemsTypes = this.clientGetInventoryItemsType();

        for (int i = 0; i < this.clientGetInventoryItemCount(); ++i) {
            if (inventoryItemsTypes.get(i) == itemId) {
                if (numberFound == numberToFind) return i;
                else numberFound++;
            }
        }
        if (numberFound < numberToFind) {
            throw new InventoryItemSlotSpecifiedBeyondFoundException(numberToFind, numberFound);
        } else if (numberFound == 0) {
            throw new InventoryItemNotFoundException(itemId);
        } else {
            return numberFound;
        }
    }
    /*** End Inventory Fuhnctions ***/

    /*** Ground Item Functions ***/
    public void pickupGroundItem(ItemObject item) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        this.method1162(this.clientGetLocalRegionX(), this.clientGetLocalRegionY(), item.getX(), item.getY());
        createPacket(247);
        putUnsignedShort(item.getX() + this.clientGetRegionX());
        putUnsignedShort(this.clientGetRegionY() + item.getY());
        putUnsignedShort(item.getId());
        sendPacket();
    }


    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y, int... ids) throws ItemNotFoundAtPositionException, ItemsNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<ItemObject> itemsAtLocation = getGroundItemsByLocation(x, y);
        ArrayList<ItemObject> returnArrayList = new ArrayList<ItemObject>();

        for (ItemObject i : itemsAtLocation) {
            for (int j : ids) {
                if (i.getId() == j) {
                    returnArrayList.add(i);
                }
            }
        }
        if (returnArrayList.size() > 0) {
            return returnArrayList;
        } else {
            throw new ItemsNotFoundAtPositionException(x, y, ids);
        }

    }
    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y, int id) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<ItemObject> groundItemsAtLocation = getGroundItemsByLocation(x, y);
        ArrayList<ItemObject> returnList = new ArrayList<ItemObject>();

        for (ItemObject i : groundItemsAtLocation) {
            if (i.getId() == id) {
                returnList.add(i);
            }
        }
        if (returnList.size() > 0) {
            return returnList;
        } else {
            throw new ItemNotFoundAtPositionException(x, y, id);
        }
    }
    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<ItemObject> ret = new ArrayList<ItemObject>();
        ArrayList<ItemObject> items = clientGetGroundItems();
        for (int i = 0; i < this.clientGetGroundItemsCount(); ++i) {
            ItemObject obj = items.get(i);

            if (obj.getX() + this.clientGetRegionX() == x && obj.getY() + this.clientGetRegionY() == y) {
                ret.add(obj);
            }
        }
        if (ret.size() <= 0) throw new ItemNotFoundAtPositionException(x, y);
        return ret;

    }

    public ArrayList<ItemObject> getGroundItemsById(int id) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getGroundItemsById(id, Integer.MAX_VALUE);
    }
    public ArrayList<ItemObject> getGroundItemsById(int id, int maxDistance) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<ItemObject> ret = new ArrayList<ItemObject>();

        ArrayList<ItemObject> items = this.clientGetGroundItems();

        for (int i = 0; i < this.clientGetGroundItemsCount(); ++i) {
            ItemObject obj = items.get(i);

            if (obj.getId() == id) {
                int itemX = obj.getX() + this.clientGetRegionX();
                int itemY = obj.getY() + this.clientGetRegionY();

                double distanceToObject = Math.sqrt(Math.pow(Math.abs(itemX - (this.clientGetLocalRegionX() + this.clientGetRegionX())), 2) + Math.pow(Math.abs(itemY - (this.clientGetLocalRegionY() + this.clientGetRegionY())), 2));

                if (distanceToObject <= maxDistance) {
                    ret.add(obj);
                }
            }
        }

        if (ret.size() > 0) {
            ret.sort(new PositionComparator(this, this.clientGetLocalRegionX() + this.clientGetRegionX(), this.clientGetLocalRegionY() + this.clientGetRegionY()));
            return ret;
        } else {
            throw new ItemNotFoundInRange(id, maxDistance);
        }
    }

    public ItemObject getGroundItemById(int id) throws ItemNotFoundOnGroundException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        double closestDistance = Double.MAX_VALUE;

        ItemObject ret = null;
        ArrayList<ItemObject> groundItems = this.clientGetGroundItems();
        for (int i = 0; i < this.clientGetGroundItemsCount(); ++i) {

            ItemObject temp = groundItems.get(i);
            if (temp.getId() == id) {
                int itemX = temp.getX() + this.clientGetRegionX();
                int itemY = temp.getY() + this.clientGetRegionY();

                double t = Math.sqrt(Math.pow(Math.abs(itemX - (this.clientGetLocalRegionX() + this.clientGetRegionX())), 2) + Math.pow(Math.abs(itemY - (this.clientGetRegionY() + this.clientGetLocalRegionY())), 2));
                if (t < closestDistance) {
                    closestDistance = t;
                    ret = temp;
                }
            }
        }

        if (ret == null) throw new ItemNotFoundOnGroundException(id);
        return ret;
    }
    /*** End Ground Item Functions ***/

    /*** End Inventory Methods ***/

    /*** NPC Functions ***/

    public ArrayList<NpcObject> getNpcsInRange(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getNpcsInRange(Double.MAX_VALUE, inCombat);
    }
    public ArrayList<NpcObject> getNpcsInRange(double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<NpcObject> returnObject = new ArrayList<NpcObject>();

        int myX = this.clientGetLocalRegionX() + this.clientGetRegionX();
        int myY = this.clientGetLocalRegionY() + this.clientGetRegionY();

        ArrayList<NpcObject> npcs = this.clientGetNpcs();
        for (int i = 0; i < this.clientGetNpcCount(); ++i) {
            NpcObject npc = npcs.get(i);
            int npcX = (npc.currentX() - 64) / 128 + this.clientGetRegionX();
            int npcY = (npc.currentY() - 64) / 128 + this.clientGetRegionY();

            double distance = Math.sqrt(Math.pow(Math.abs(npcX - myX), 2) + Math.pow(Math.abs(npcY - myY), 2));

            if (distance <= maxRange) {
                try {
                    if (isInCombat(npc) == inCombat) {
                        returnObject.add(npc);
                    }
                } catch (CharacterIsNullException e) {
                    throw new NPCNotFoundInRangeException(maxRange);
                }
            }
        }

        if (returnObject.size() > 0) {
            returnObject.sort(new PositionComparator(this, myX, myY));
            return returnObject;
        } else {
            throw new NPCNotFoundInRangeException(maxRange);
        }

    }

    public ArrayList<NpcObject> getNpcsById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { if (!IsScriptRunning()) throw new ScriptNotRunningException(); if (!this.isLoggedIn()) throw new NotLoggedInException(); return getNpcsById(id, Double.MAX_VALUE, inCombat); }
    public ArrayList<NpcObject> getNpcsById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<NpcObject> returnObject = new ArrayList<NpcObject>();

        int myX = this.clientGetLocalRegionX() + this.clientGetRegionX();
        int myY = this.clientGetLocalRegionY() + this.clientGetRegionY();
        ArrayList<NpcObject> npcs = this.clientGetNpcs();
        for (int i = 0; i < this.clientGetNpcCount(); ++i) {
            NpcObject npc = npcs.get(i);

            if (npc.getId() == id) {
                int npcX = (npc.currentX() - 64) / 128 + this.clientGetRegionX();
                int npcY = (npc.currentY() - 64) / 128 + this.clientGetRegionY();

                double distance = Math.sqrt(Math.pow(Math.abs(npcX - myX), 2) + Math.pow(Math.abs(npcY - myY), 2));

                if (distance <= maxRange) {
                    try {
                        if (isInCombat(npc) == inCombat) {
                            returnObject.add(npc);
                        }
                    } catch (CharacterIsNullException e) {
                        throw new NPCNotFoundInRangeException(id, maxRange);
                    }
                }
            }
        }

        if (returnObject.size() > 0) {
            returnObject.sort(new PositionComparator(this, myX, myY));
            return returnObject;
        } else {
            throw new NPCNotFoundInRangeException(id, maxRange);
        }

    }

    public NpcObject getNearestNpcById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getNearestNpcById(id, Double.MAX_VALUE, inCombat);
    }
    public NpcObject getNearestNpcById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        double nearestDistance = maxRange;

        NpcObject returnObject = null;

        int myX = this.clientGetLocalRegionX() + this.clientGetRegionX();
        int myY = this.clientGetLocalRegionY() + this.clientGetRegionY();
        ArrayList<NpcObject> npcs = clientGetNpcs();
        for (int i = 0; i < this.clientGetNpcCount(); ++i) {
            NpcObject npc = npcs.get(i);

            if (npc.getId() == id) {
                int npcX = (npc.currentX() - 64) / 128 + this.clientGetRegionX();
                int npcY = (npc.currentY() - 64) / 128 + this.clientGetRegionY();
                double distance = Math.sqrt(Math.pow(Math.abs(npcX - myX), 2) + Math.pow(Math.abs(npcY - myY), 2));

                if (distance < nearestDistance) {
                    try {
                        if (isInCombat(npc) == inCombat) {

                            //Debug("MYCOords(" + myX + ", " + myY + ") EnemyCoords: (" + npcX + ", " + npcY + ")");
                            returnObject = npc;
                            nearestDistance = distance;
                        }
                    } catch (CharacterIsNullException e) {
                        throw new NPCNotFoundInRangeException(id, maxRange);
                    }
                }
            }
        }

        if (returnObject == null) {
            throw new NPCNotFoundInRangeException(id, maxRange);
        } else {
            return returnObject;
        }
    }

    public NpcObject getNearestNpc(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getNpcsInRange(inCombat).get(0);

    }
    public NpcObject getNearestNpc(double range, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getNpcsInRange(range, inCombat).get(0);
    }

    public boolean isInCombat(MobObject character) throws CharacterIsNullException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (character == null) throw new CharacterIsNullException();
        if (character.getAnimationCurrent() == 8 || character.getAnimationCurrent() == 9 || character.getAttackingCharacter() != null) { return true; }
        return false;
    }
    /*** NPC Methods ***/
    public void chooseNpcChatMenuOption(int option) throws NPCChatMenuNotOpenException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (isNpcChatMenuOpen()) {
            createPacket(116);
            putByte(option);
            sendPacket();
            //this.npcChatMenu.setMenuOpen(false);
            this.aClass31_Sub13_1282.method400(false);
            this.anInt1761 = 0;
            this.aBoolean1433 = false;
        } else {
            throw new NPCChatMenuNotOpenException();
        }
    }
    public boolean isNpcChatMenuOpen() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
       // return this.npcChatMenu.getMenuOpen();
        return this.aClass31_Sub13_1282.method444();
    }

    public void attackNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int x = (npc.currentX() - 64) / 128;
        int y = (npc.currentY() - 64) / 128;

        //this.walkToActionSource(this.clientGetLocalRegionX(), this.clientGetLocalRegionY(), x, y, true);

        while (this.clientGetLocalRegionX() + this.clientGetRegionX() != x + this.clientGetRegionX() || this.clientGetLocalRegionY() + this.clientGetRegionY() != y + this.clientGetRegionY()) {
            try {
                this.walkTo(x + this.clientGetRegionX(), y + this.clientGetRegionY());
                Thread.sleep(666);

                y = (npc.currentY() - 64) / 128;
                x = (npc.currentX() - 64) / 128;
            } catch (Exception e) {}
        }

        createPacket(190);
        putUnsignedShort(npc.getServerIndex());
        sendPacket();

    }
    public void attackNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        attackNpc(getNearestNpcById(id, false));
    }

    public void talkToNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int x = (npc.currentX() - 64) / 128;
        int y = (npc.currentY() - 64) / 128;

        this.method1137(this.clientGetLocalRegionX(), this.clientGetLocalRegionY(), x, y, true); //TODO: UPDATE

        createPacket(153);
        putUnsignedShort(npc.getServerIndex());
        sendPacket();
    }
    public void talkToNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        talkToNpc(getNearestNpcById(id, false));
    }

    public void cmdNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        int x = (npc.currentX() - 64) / 128;
        int y = (npc.currentY() - 64) / 128;

        this.method1137(this.clientGetLocalRegionX(), this.clientGetLocalRegionY(), x, y, true); //TODO: UPDATE

        createPacket(202);
        putUnsignedShort(npc.getServerIndex());
        sendPacket();
    }
    public void cmdNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        cmdNpc(getNearestNpcById(id, false));
    }


    /*** Object Functions ***/
    public void cmdObject(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (object != null) {
            this.method1183(object.getX(), object.getY(), object.getUnknown1(), object.getId());
            createPacket(136);
            putUnsignedShort(this.clientGetRegionX() + object.getX());
            putUnsignedShort(object.getY() + this.clientGetRegionY());
            sendPacket();
        }
        else {
            throw new ObjectNullException();
        }
    }
    public void cmdObject(ObjectObject object, int times) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (object != null) {
            for (int i = 0; i < times; ++i) {
                this.method1183(object.getX(), object.getY(), object.getUnknown1(), object.getId());
                createPacket(136);
                putUnsignedShort(this.clientGetRegionX() + object.getX());
                putUnsignedShort(object.getY() + this.clientGetRegionY());
                sendPacket();
            }
        }
        else {
            throw new ObjectNullException();
        }
    }
    public void cmdObject2(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (object != null) {
            this.method1183(object.getX(), object.getY(), object.getUnknown1(), object.getId());
            createPacket(79);
            putUnsignedShort(this.clientGetRegionX() + object.getX());
            putUnsignedShort(object.getY() + this.clientGetRegionY());
            sendPacket();
        }
        else {
            throw new ObjectNullException();
        }
    }

    public void cmdObject2(ObjectObject object, int times) throws NotLoggedInException, ObjectNullException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        if (object != null) {

            for (int i = 0; i < times; ++i) {
                this.method1183(object.getX(), object.getY(), object.getUnknown1(), object.getId());
                createPacket(79);
                putUnsignedShort(this.clientGetRegionX() + object.getX());
                putUnsignedShort(object.getY() + this.clientGetRegionY());
                sendPacket();
            }
            //}
        }
        else {
            throw new ObjectNullException();
        }
    }

    public ArrayList<ObjectObject> getObjectsById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { if (!IsScriptRunning()) throw new ScriptNotRunningException(); if (!this.isLoggedIn()) throw new NotLoggedInException(); return getObjectsById(id, Double.MAX_VALUE); }
    public ArrayList<ObjectObject> getObjectsById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        ArrayList<ObjectObject> returnObject = new ArrayList<ObjectObject>();

        int myX = this.clientGetLocalRegionX() + this.clientGetRegionX();
        int myY = this.clientGetLocalRegionY() + this.clientGetRegionY();
        ArrayList<ObjectObject> objects = clientGetObjectObjects();
        for (int i = 0; i < this.clientGetObjectObjectCount(); ++i) {
            ObjectObject object = objects.get(i);

            if (object.getId() == id) {
                int objectX = object.getX() + this.clientGetRegionX();
                int objectY = object.getY() + this.clientGetRegionY();

                double distance = Math.sqrt(Math.pow(Math.abs(objectX - myX), 2) + Math.pow(Math.abs(objectY - myY), 2));

                if (distance <= maxRange) {
                    returnObject.add(object);
                }
            }
        }

        if (returnObject.size() > 0) {
            returnObject.sort(new PositionComparator(this, myX, myY));
            return returnObject;
        } else {
            throw new ObjectNotFoundInRangeException(id, maxRange);
        }

    }

    public ObjectObject getNearestObjectById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return getNearestObjectById(id, Double.MAX_VALUE);
    }
    public ObjectObject getNearestObjectById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        double nearestDistance = maxRange;

        ObjectObject returnObject = null;

        int myX = this.clientGetLocalRegionX() + this.clientGetRegionX();
        int myY = this.clientGetLocalRegionY() + this.clientGetRegionY();
        ArrayList<ObjectObject> objects = clientGetObjectObjects();
        for (int i = 0; i < this.clientGetObjectObjectCount(); ++i) {
            ObjectObject object = objects.get(i);

            if (object.getId() == id) {
                int npcX = object.getX() + this.clientGetRegionX();
                int npcY = object.getY() + this.clientGetRegionY();

                double distance = Math.sqrt(Math.pow(Math.abs(npcX - myX), 2) + Math.pow(Math.abs(npcY - myY), 2));

                if (distance < nearestDistance) {
                    returnObject = object;
                    nearestDistance = distance;
                }
            }
        }

        if (returnObject == null) {
            throw new ObjectNotFoundInRangeException(id, maxRange);
        } else {
            return returnObject;
        }
    }
    /*** Door Object Functions ***/

    /*** Player Functions ***/


    /*** Walk Commands ***/
    public void walkTo(int x, int y) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        this.method1162(this.clientGetLocalRegionX(), this.clientGetLocalRegionY(), x - this.clientGetRegionX(), y - this.clientGetRegionY());
    }
    public void walkToInCombat(int x, int y) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        this.createPacket(187);
        this.putUnsignedShort(x);
        this.putUnsignedShort(y);
        this.sendPacket();
    }
    public void walkToObjectForCmd(ObjectObject object) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        this.method1183(object.getX(), object.getY(), object.getUnknown1(), object.getId());
    }
    /*** Our Player Functions ***/

    public PlayerObject getOurPlayer() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetLocalPlayer();
    }
    public int getX() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetLocalRegionX() + this.clientGetRegionX();
    }
    public int getY() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetLocalRegionY() + this.clientGetRegionY();
    }
    public int getRegionX() throws ScriptNotRunningException, NotLoggedInException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetRegionX();
    }
    public int getRegionY() throws ScriptNotRunningException, NotLoggedInException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetRegionY();
    }

    public int getFatigue() throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetFatigue();
    }
    public int getBaseStat(int id) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetPlayerStatsBase()[id];
    }
    public int getCurrentStat(int id) throws NotLoggedInException, ScriptNotRunningException {
        if (!IsScriptRunning()) throw new ScriptNotRunningException();
        if (!this.isLoggedIn()) throw new NotLoggedInException();
        return this.clientGetPlayerStatsCurrent()[id];
    }

    private int clientGetObjectObjectCount() {
        return this.anInt1331;
    }
    private ArrayList<ObjectObject> clientGetObjectObjects() {
        return clientGetObjectObjectArray(this.aClass57_Sub2_Array1450);
    }
    private ArrayList<ObjectObject> clientGetObjectObjectArray(Class57_Sub2[] realObjects) {
        ArrayList<ObjectObject> objects = new ArrayList<ObjectObject>();

        for (Class57_Sub2 realObject : realObjects) {
            objects.add(clientGetObjectObject(realObject));
        }

        return objects;
    }
    private ObjectObject clientGetObjectObject(Class57_Sub2 realObject) {
        return ObjectObject.GetObjectObject(realObject);
    }
    private int clientGetInventoryItemPosition() {
        return this.anInt1594;
    }
    private ArrayList<Integer> clientGetEquipmentItemsType() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < this.anInt_Array1617.length; ++i) {
            ret.add(this.anInt_Array1617[i]);
        }
        return ret;
    }
    private ArrayList<Integer> clientGetEquipmentItemsCount() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < this.anInt_Array1359.length; ++i) {
            ret.add(this.anInt_Array1359[i]);
        }
        return ret;
    }
    private ArrayList<Integer> clientGetInventoryItemsStackCount() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < this.anInt_Array1311.length; ++i) {
            ret.add(this.anInt_Array1311[i]);
        }
        return ret;
    }
    private ArrayList<Integer> clientGetInventoryItemsType() {
        ArrayList<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < this.anInt_Array1659.length; ++i) {
            ret.add(this.anInt_Array1659[i]);
        }
        return ret;
    }

    private ArrayList<ItemObject>  clientGetGroundItems() {
        return clientGetItemArray(this.aClass57_Sub3_Array1445);
    }

    private ArrayList<ItemObject> clientGetItemArray(Class57_Sub3[] realItems) {
        ArrayList<ItemObject> items = new ArrayList<ItemObject>();

        for (Class57_Sub3 realItem : realItems) {
            items.add(clientGetItem(realItem));
        }
        return items;
    }
    private ItemObject clientGetItem(Class57_Sub3 realItem) {
        return ItemObject.GetItemObject(realItem);
    }


    private ArrayList<NpcObject> clientGetNpcsServer() {
        return clientGetNpcArray(this.aClass57_Sub4_Sub2_Array1557);
    }
    private ArrayList<NpcObject> clientGetNpcsCache() {
        return clientGetNpcArray(this.aClass57_Sub4_Sub2_Array1291);
    }
    private ArrayList<NpcObject> clientGetNpcs() {
        return clientGetNpcArray(this.aClass57_Sub4_Sub2_Array1489);
    }
    private ArrayList<NpcObject> clientGetNpcArray(Class57_Sub4_Sub2[] realNpcs) {
        ArrayList<NpcObject> npcs = new ArrayList<NpcObject>();
        writeln("NPCSCount: " + this.clientGetNpcCount());
        for (Class57_Sub4_Sub2 npc : realNpcs) {
            NpcObject newNpc = clientGetNpc(npc);
            //writeln("NPC: ID:" + newNpc.getId() + ", Server Index: " + newNpc.getServerIndex() + ", CurrentX: " + newNpc.currentX() + ", CurrentY: " + newNpc.currentY() + ", GetX: " + newNpc.getX() + ", GetY: " + newNpc.getY());
            npcs.add(newNpc);
        }

        return npcs;
    }
    private NpcObject clientGetNpc(Class57_Sub4_Sub2 realNpc) {
        return NpcObject.GetNpcObject(realNpc);
    }

    private int clientGetPlayerCount() {
        return this.anInt1656;
    }
    private ArrayList<PlayerObject> clientGetPlayersServer() {
        return clientGetPlayerArray(this.aClass57_Sub4_Sub1_Array1548);
    }
    private ArrayList<PlayerObject> clientGetKnownPlayers() {
        return clientGetPlayerArray(this.aClass57_Sub4_Sub1_Array1650);
    }
    private ArrayList<PlayerObject> clientGetPlayers() {
        return clientGetPlayerArray(this.aClass57_Sub4_Sub1_Array1587);
    }
    private ArrayList<PlayerObject> clientGetPlayerArray(Class57_Sub4_Sub1[] realPlayers) {
        ArrayList<PlayerObject> players = new ArrayList<PlayerObject>();

        for (Class57_Sub4_Sub1 player : realPlayers) {
            players.add(clientGetPlayer(player));
        }

        return players;
    }
    private PlayerObject clientGetPlayer(Class57_Sub4_Sub1 player) {
        return PlayerObject.GetPlayerObject(player);
    }
    private PlayerObject clientGetLocalPlayer() {
        return clientGetPlayer(this.aClass57_Sub4_Sub1_1280);
    }

    private void putBytes(byte[] bytes, int offset, int length) {
        this.ClientStream().method765(bytes, offset, length);
    }
    private void putStringTerminate(String s) {
        this.ClientStream().method770(s);
    }
    private void putString(String s) {
        this.ClientStream().method764(s);
    }
    private void putLong(long l) {
        this.ClientStream().method766(l);
    }
    private void putUnsignedInt(int i) {
        this.ClientStream().method778(i);
    }
    private void putUnsignedShort(int s) {
        this.ClientStream().method774(s);
    }
    private void putByte(int b) {
        this.ClientStream().method776(b);
    }
    private void sendPacket() {
        Random rand = new Random(System.currentTimeMillis());

        int randX = (Math.abs(rand.nextInt()) % (510 - 5)) + 5;
        int randY = (Math.abs(rand.nextInt()) % (330 - 5)) + 5;

        this.method1274((int) ((float) randX / floatX), (int) ((float) randY / this.floatY));
        //this.method1284(2);
        //this.method1279(this.method1283());
        //this.method1286(0);
        //this.method1165(this.method1283(), this.method1285(), this.method1297());

        this.method1274((int) ((float) randX / floatX), (int) ((float) (randY + 10) / this.floatY));
        //this.method1284(1);
        ///this.method1279(this.method1283());
        //this.method1286(0);
        this.method1165(this.method1283(), this.method1285(), this.method1297());

        this.ClientStream().method472();
    }
    float floatX = 513 / this.method1125().anInt1105;
    float floatY = 346 / this.method1125().anInt1113;

    private void createPacket(int id) {
        this.ClientStream().method475(id);
    }
    private Class54_Sub1 ClientStream() {
        return this.method1304();
    }
    public int clientGetLocalRegionY() {
        return this.anInt1542;
    }
    public int clientGetLocalRegionX() {
        return this.anInt1467;
    }
    public int clientGetRegionX() {
        return this.anInt1615;
    }
    public int clientGetRegionY() {
        return this.anInt1273;
    }
    private int clientGetFatigue() {
        return this.anInt1577;
    }
    private int[] clientGetPlayerStatsCurrent() {
        return this.anInt_Array1558;
    }
    private int[] clientGetPlayerStatsBase() {
        return this.anInt_Array1573;
    }

    private int clientGetNpcCacheCount() { return this.anInt1309; }    
    private int clientGetNpcCount() { return this.anInt1366; }

    private int clientGetGroundItemsCount() {
        return this.anInt1586;
    }
    private int clientGetInventoryItemCount() {
        return this.anInt1404;
    }

    private boolean clientGetIsBankOpen() {
        return this.method1112();
    }
    private BankWindow clientGetBankWindow() {
        return BankWindow.GetBankWindow(this.method1100());
    }
    private void clientSetBankOpen(boolean open) {
        this.method1215(open);
    }
    private int clientGetBankItemsCount() {
       return this.anInt1515;
    }


    /****************/
    /* Script Stuff */
    /****************/

    public Thread scriptThread;
    public boolean Running;
    public IScript script;

    public HashMap<String, IScript> scripts = new HashMap<String, IScript>();

    public boolean IsScriptRunning() { return Running; }
    public void setIsScriptRunning(boolean running) { Running = running; }

    public void InitializeScript(String scriptName, String... args) {
        String[] args2;
        if (args == null) args2 = new String[] { "" };
        else args2 = args;
        try {
            script = scripts.get(scriptName.toLowerCase());
            scriptThread = new Thread(new Runnable() {
                public void run() {
                    Running = true;
                    script.Main(args2);
                    Running = false;
                }
            });
            scriptThread.start();

        } catch (Exception e) {
            writeln("Could not start script: " + scriptName);
        }
    }

    public void LoadScripts() {
        scripts.clear();
        File f = new File(System.getProperty("user.dir") + "/Scripts");
        String[] files = f.list();
        int counter = 0;
        long timer = System.currentTimeMillis();
        try {
            URL url = f.toURI().toURL();
            URL[] urls = new URL[]{url};

            URLClassLoader cl = new URLClassLoader(urls);

            for (int i = 0; i < files.length; i++) {
                if (files[i].endsWith(".class") && files[i].indexOf("$") == -1) {
                    try {
                        String fileName = files[i].substring(0, files[i].length() - ".class".length());

                        Class clazz = cl.loadClass(fileName);

                        IScript macro = (IScript)clazz.getConstructor(IScriptMethods.class, String.class).newInstance(this, fileName);
                        fileName = fileName.toLowerCase();
                        scripts.put(fileName, macro);
                        counter++;
                        writeln("Found script: " + fileName);
                    } catch (Exception e_) {
                        writeln("Errorneous script: " + files[i] + " - " + e_.toString());
                        e_.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        long time = System.currentTimeMillis() - timer;
        writeln("Done loading: " + counter + " scripts in: " + time + "ms");
    }

    @Override
    public void method1145(Class54 var1, int var2, int var3) {
        if (var2 == 131) {
            int offset = var1.anInt918;
            int var5 = var1.method780();
            Enumerator3 var6 = Enumerator3.a(var1.method780());
            int var7 = var1.method780();
            String var8 = var1.method777();
            String var9 = null;
            String var10 = null;
            String var11 = null;
            if((var7 & 1) != 0) {
                var9 = var1.method777();
            }

            if((1 & var7) != 0) {
                var10 = var1.method777();
            }

            if((var7 & 2) != 0) {
                var11 = var1.method777();
            }
            if (var6 == Enumerator3.GAME) {
                if (script != null && IsScriptRunning())
                    synchronized(script) {
                        script.OnServerMessage(var8);
                    }
            }
            var1.anInt918 = offset;
        }
        super.method1145(var1, var2, var3);
    }
    
    @Override
    public void method1175(Class54 var1, int var2, int var3) {
        int index = var1.anInt918;
        if (var2 == 104) {
            int var40 = var1.method784();
            int var49 = 0;
            if (var40 > var49) {
                int var7 = var1.method784();
                int var9 = var1.method780();
                if (var9 == 1) {
                    int var10 = var1.method784();
                    String var57 = var1.method777();
                    if (this.aClass57_Sub4_Sub1_1280.method862() == var10) {
                        if (script != null && IsScriptRunning()) {
                            synchronized (this.script) {
                                script.OnNpcMessage(this.aClass57_Sub4_Sub2_Array1557[var7].method534().method91(), var57);
                            }
                        }
                    }
                    else {
                        writeln("DEBUGGING: " + var57);
                    }
                }
            }
        }
        var1.anInt918 = index;
        super.method1175(var1, var2, var3);
    }

    @Override
    public void method1068() {
        writeln("DEBUGGING: method1068() called");
        Class78.method1277();
        this.method1304().method475(33);
        this.method1304().method776(1);
        this.method1304().method472();
        this.aBoolean1482 = false;
        this.aLong1438 = this.method1227();
    }
}
