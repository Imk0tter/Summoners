package Summoners.Bots.RSCRevolution.BOT.Scripting;

import Summoners.Bots.RSCRevolution.BOT.Exceptions.*;
import Summoners.Bots.RSCRevolution.BOT.Objects.*;
import Summoners.Bots.RSCRevolution.util.logger.ILogger;

import java.util.ArrayList;

public interface IScriptMethods {
    /*** Bank Commands ***/
    public boolean isBankOpen();
    public void depositInventory() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void saveInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void loadInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void toggleAsNotes(boolean asNotes) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void depositEquipment() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void swapInInventory(int inventorySlotA, int inventorySlotB, boolean swap) throws BankNotOpenException, InventorySlotEmptyException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBankById(int itemIdA, int itemIdB) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBankById(int itemIdA, int itemIdB, boolean swap) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBankBySlot(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBankBySlot(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBank(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void swapInBank(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void closeBank() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public void depositItemByInventorySlot(int inventorySlot, int count) throws
            BankNotOpenException, InventorySlotEmptyException, InventoryItemCountBelowSpecifiedException, InventoryItemNotFoundException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException;
    public void depositItemByBankSlot(int bankSlot, int count) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException;
    public void depositItembyId(int itemId, int itemCount) throws
            BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException;
    public void depositItem(int itemId, int itemCount) throws BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException;
    public void withdrawItemByInventorySlot(int inventorySlot, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException;
    public void withdrawItemByBankSlot(int bankSlot, int itemCount) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void withdrawItemById(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void withdrawItem(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    /*** End Bank Functions.methods - Finished***/


    /*** Inventory Functions ***/
    public void equipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException;
    public void equipInventoryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException;

    public void unequipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    public void unequipInvenotryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException;

    public void useInventoryItemOnItem(int slotA, int slotB) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException;
    public void dropInventoryItemById(int itemId) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException;
    public void dropInventoryItemById(int itemId, int count) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException;

    public void commandInventoryItembyId(int itemId) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException;
    public void commandInventoryItemBySlot(int slot) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException;

    public int getInventoryEmptySlotCount() throws NotLoggedInException, ScriptNotRunningException;

    public int getInventoryItemSlotPosition(int itemId) throws InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, NotLoggedInException, ScriptNotRunningException;
    public int getInventoryItemCountById(int itemId) throws NotLoggedInException, ScriptNotRunningException;
    public int getInventoryItemIdFromSlot(int slot) throws InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException;
    public int getInventoryItemSlotPosition(int itemId, int numberToFind) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException;
    /*** End Inventory Functions ***/

    /*** Ground Item Functions ***/
    public void pickupGroundItem(ItemObject item) throws NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y, int... ids) throws ItemNotFoundAtPositionException, ItemsNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y, int id) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ItemObject> getGroundItemsByLocation(int x, int y) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ItemObject> getGroundItemsById(int id) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ItemObject> getGroundItemsById(int id, int maxDistance) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException;
    public ItemObject getGroundItemById(int id) throws ItemNotFoundOnGroundException, NotLoggedInException, ScriptNotRunningException;

    /*** NPC Functions ***/
    public ArrayList<NpcObject> getNpcsInRange(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<NpcObject> getNpcsInRange(double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<NpcObject> getNpcsById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<NpcObject> getNpcsById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public NpcObject getNearestNpcById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public NpcObject getNearestNpcById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public NpcObject getNearestNpc(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public NpcObject getNearestNpc(double range, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    /*** Walk Commands ***/
    public void walkTo(int x, int y) throws NotLoggedInException, ScriptNotRunningException;
    public void walkToInCombat(int x, int y) throws NotLoggedInException, ScriptNotRunningException;

    /*** NPC Methods ***/
    public void chooseNpcChatMenuOption(int option) throws NPCChatMenuNotOpenException, NotLoggedInException, ScriptNotRunningException;
    public boolean isNpcChatMenuOpen() throws NotLoggedInException, ScriptNotRunningException;

    public void attackNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException;
    public void attackNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public void talkToNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException;
    public void talkToNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public void cmdNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException;
    public void cmdNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public boolean isInCombat(MobObject character) throws CharacterIsNullException, NotLoggedInException, ScriptNotRunningException;
    public boolean isLoggedIn();

    /*** Our Player Commands ***/
    public PlayerObject getOurPlayer() throws NotLoggedInException, ScriptNotRunningException;
    public int getX() throws NotLoggedInException, ScriptNotRunningException;
    public int getY() throws NotLoggedInException, ScriptNotRunningException;

    public int getRegionX() throws ScriptNotRunningException, NotLoggedInException;
    public int getRegionY() throws ScriptNotRunningException, NotLoggedInException;

    public int getFatigue() throws NotLoggedInException, ScriptNotRunningException;
    public int getBaseStat(int id) throws NotLoggedInException, ScriptNotRunningException;
    public int getCurrentStat(int id) throws NotLoggedInException, ScriptNotRunningException;

    /*** Object Commands ***/
    public void cmdObject(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException;
    public void cmdObject(ObjectObject object, int times) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException;
    public void cmdObject2(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException;
    public void cmdObject2(ObjectObject object, int times) throws NotLoggedInException, ObjectNullException, ScriptNotRunningException;


    public ArrayList<ObjectObject> getObjectsById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public ArrayList<ObjectObject> getObjectsById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public ObjectObject getNearestObjectById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;
    public ObjectObject getNearestObjectById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException;

    public boolean IsScriptRunning();
    public void setIsScriptRunning(boolean running);

    /*** Output to logger ***/
    //public void Log(String s);
    //public void Debug(String s);
    //public void Error(String s);
    public void writeln(String s);
    public void write(String s);
    
    /*** hax ***/
    //public void sendPingPacket() throws NotLoggedInException;
}
