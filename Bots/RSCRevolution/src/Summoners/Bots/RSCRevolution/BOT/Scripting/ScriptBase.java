package Summoners.Bots.RSCRevolution.BOT.Scripting;

import java.util.ArrayList;
import java.util.Random;

import Summoners.Bots.RSCRevolution.BOT.Exceptions.*;
import Summoners.Bots.RSCRevolution.BOT.Objects.*;
import Summoners.Bots.RSCRevolution.BOT.Definitions.*;

public abstract class ScriptBase {
    IScriptMethods scriptMethods;

    public ScriptBase(IScriptMethods scriptMethods, String scriptName) {
        this.scriptMethods = scriptMethods;
        this.scriptName = scriptName;
        SetRunning(false);
    }
    private String scriptName;
    public String scriptName() {
        return scriptName;
    }

    @Override
    public String toString() {
        return scriptName;
    }

    /*** Bank Commands ***/ //TODO: Open Bank
    public boolean IsBankOpen() { return scriptMethods.isBankOpen(); }
    public void DepositInventory() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositInventory(); }
    public void SaveInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.saveInventoryLoadSlot(slotNumber); }
    public void LoadInventoryLoadSlot(int slotNumber) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.loadInventoryLoadSlot(slotNumber); }
    public void ToggleAsNotes(boolean asNotes) throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.toggleAsNotes(asNotes);}
    public void DepositEquipment() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositEquipment(); }
    public void SwapInInventory(int inventorySlotA, int inventorySlotB, boolean swap) throws BankNotOpenException, InventorySlotEmptyException, NotLoggedInException, ScriptNotRunningException { scriptMethods.swapInInventory(inventorySlotA, inventorySlotB, swap); }
    public void SwapInBankById(int itemIdA, int itemIdB) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.swapInBankById(itemIdA, itemIdB); }
    public void swapInBankById(int itemIdA, int itemIdB, boolean swap) throws BankNotOpenException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.swapInBankById(itemIdA, itemIdB, swap); }
    public void SwapInBankBySlot(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {scriptMethods.swapInBankBySlot(bankSlotA, bankSlotB) ;}
    public void SwapInBankBySlot(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException {scriptMethods.swapInBankBySlot(bankSlotA, bankSlotB, swap); }
    public void SwapInBank(int bankSlotA, int bankSlotB) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.swapInBank(bankSlotA, bankSlotB); }
    public void SwapInBank(int bankSlotA, int bankSlotB, boolean swap) throws BankNotOpenException, BankSlotNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.swapInBank(bankSlotA, bankSlotB, swap); }
    public void CloseBank() throws BankNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.closeBank(); }
    public void DepositItemByInventorySlot(int inventorySlot, int count) throws
            BankNotOpenException, InventorySlotEmptyException, InventoryItemCountBelowSpecifiedException, InventoryItemNotFoundException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositItemByInventorySlot(inventorySlot, count); }
    public void DepositItemByBankSlot(int bankSlot, int count) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositItemByBankSlot(bankSlot, count); }
    public void DepositItembyId(int itemId, int itemCount) throws
            BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositItembyId(itemId, itemCount); }
    public void DepositItem(int itemId, int itemCount) throws BankNotOpenException, InventoryItemNotFoundException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException { scriptMethods.depositItem(itemId, itemCount); }
    public void WithdrawItemByInventorySlot(int inventorySlot, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException { scriptMethods.withdrawItemByInventorySlot(inventorySlot, itemCount); }
    public void WithdrawItemByBankSlot(int bankSlot, int itemCount) throws
            BankNotOpenException, BankSlotNotFoundException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.withdrawItemByBankSlot(bankSlot, itemCount); }
    public void WithdrawItemById(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.withdrawItemById(itemId, itemCount); }
    public void WithdrawItem(int itemId, int itemCount) throws
            BankNotOpenException, BankItemCountBelowSpecifiedException, BankItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.withdrawItem(itemId, itemCount); }
    /*** End Bank Functions.methods - Finished***/


    /*** Inventory Functions ***/
    public void EquipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemCountBelowSpecifiedException, NotLoggedInException, ScriptNotRunningException { scriptMethods.equipInventoryItemById(id); }
    public void EquipInventoryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException { scriptMethods.equipInventoryItemBySlot(slot); }

    public void UnequipInventoryItemById(int id) throws InventoryNotEquippableException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException { scriptMethods.unequipInventoryItemById(id); }
    public void UnequipInvenotryItemBySlot(int slot) throws InventoryItemCountBelowSpecifiedException, InventoryNotEquippableException, NotLoggedInException, ScriptNotRunningException { scriptMethods.unequipInvenotryItemBySlot(slot); }

    public void UseInventoryItemOnItem(int slotA, int slotB) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException { scriptMethods.useInventoryItemOnItem(slotA, slotB); }
    public void DropInventoryItemById(int itemId) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException { scriptMethods.dropInventoryItemById(itemId); }
    public void DropInventoryItemById(int itemId, int count) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemCountBelowSpecifiedException, ScriptNotRunningException { scriptMethods.dropInventoryItemById(itemId, count); }

    public void CommandInventoryItemById(int itemId) throws NotLoggedInException, InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException { scriptMethods.commandInventoryItembyId(itemId); }
    public void CommandInventoryItemBySlot(int slot) throws NotLoggedInException, InventoryItemSlotSpecifiedBeyondFoundException, ScriptNotRunningException { scriptMethods.commandInventoryItemBySlot(slot); }

    public int GetInventoryEmptySlotCount() throws NotLoggedInException, ScriptNotRunningException {
        return scriptMethods.getInventoryEmptySlotCount();
    }

    public int GetInventoryItemSlotPosition(int itemId) throws InventoryItemNotFoundException, InventoryItemSlotSpecifiedBeyondFoundException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getInventoryItemSlotPosition(itemId); }
    public int GetInventoryItemCountById(int itemId) throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getInventoryItemCountById(itemId); }
    public int GetInventoryItemIdFromSlot(int slot) throws InventorySlotEmptyException, SlotEmptyException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getInventoryItemIdFromSlot(slot); }
    public int GetInventoryItemSlotPosition(int itemId, int numberToFind) throws InventoryItemSlotSpecifiedBeyondFoundException, InventoryItemNotFoundException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getInventoryItemSlotPosition(itemId, numberToFind); }
    /*** End Inventory Fuhnctions ***/

    /*** Ground Item Functions ***/
    public void PickupGroundItem(ItemObject item) throws NotLoggedInException, ScriptNotRunningException { scriptMethods.pickupGroundItem(item);}
    public ArrayList<ItemObject> GetGroundItemsByLocation(int x, int y, int... ids) throws ItemNotFoundAtPositionException, ItemsNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemsByLocation(x, y, ids); }
    public ArrayList<ItemObject> GetGroundItemsByLocation(int x, int y, int id) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemsByLocation(x,y,id); }
    public ArrayList<ItemObject> GetGroundItemsByLocation(int x, int y) throws ItemNotFoundAtPositionException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemsByLocation(x,y); }
    public ArrayList<ItemObject> GetGroundItemsById(int id) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemsById(id); }
    public ArrayList<ItemObject> GetGroundItemsById(int id, int maxDistance) throws ItemNotFoundInRange, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemsById(id, maxDistance); }

    public ItemObject GetGroundItemById(int id) throws ItemNotFoundOnGroundException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getGroundItemById(id); }
    /*** End Ground Item Functions ***/

    /*** End Inventory Methods ***/

    /*** NPC Functions ***/
    public ArrayList<NpcObject> GetNpcsInRange(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNpcsInRange(inCombat); }
    public ArrayList<NpcObject> GetNpcsInRange(double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNpcsInRange(maxRange, inCombat); }
    public ArrayList<NpcObject> GetNpcsById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNpcsById(id, inCombat); }
    public ArrayList<NpcObject> GetNpcsById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNpcsById(id, maxRange, inCombat); }

    public NpcObject GetNearestNpcById(int id, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestNpcById(id, inCombat ); }
    public NpcObject GetNearestNpcById(int id, double maxRange, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestNpcById(id, maxRange, inCombat); }
    public NpcObject GetNearestNpc(boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestNpc(inCombat); }
    public NpcObject GetNearestNpc(double range, boolean inCombat) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestNpc(range, inCombat); }

    /*** NPC Methods ***/
    public void ChooseNpcChatMenuOption(int option) throws NPCChatMenuNotOpenException, NotLoggedInException, ScriptNotRunningException { scriptMethods.chooseNpcChatMenuOption(option); }
    public boolean IsNpcChatMenuOpen() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.isNpcChatMenuOpen(); }

    public void AttackNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException { scriptMethods.attackNpc(npc); }
    public void AttackNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { scriptMethods.attackNpc(id); }

    public void TalkToNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException { scriptMethods.talkToNpc(npc); }
    public void TalkToNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { scriptMethods.talkToNpc(id); }

    public void CmdNpc(NpcObject npc) throws NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdNpc(npc); }
    public void CmdNpc(int id) throws NPCNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdNpc(id); }

    public boolean IsInCombat(MobObject character) throws CharacterIsNullException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.isInCombat(character); }

    /*** Object Functions ***/
    public void CommandObject(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdObject(object); }
    public void CommandObject(ObjectObject object, int times) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdObject(object, times); }
    public void CommandObject2(ObjectObject object) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdObject2(object); }
    public void CommandObject2(ObjectObject object, int times) throws ObjectNullException, NotLoggedInException, ScriptNotRunningException { scriptMethods.cmdObject2(object, times); }

    public ArrayList<ObjectObject> GetObjectsById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getObjectsById(id); }
    public ArrayList<ObjectObject> GetObjectsById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getObjectsById(id, maxRange); }

    public ObjectObject GetNearestObjectById(int id) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestObjectById(id); }
    public ObjectObject GetNearestObjectById(int id, double maxRange) throws ObjectNotFoundInRangeException, NotLoggedInException, ScriptNotRunningException { return scriptMethods.getNearestObjectById(id, maxRange); }
    /*** Door Object Functions ***/

    /*** Player Functions ***/
    public PlayerObject GetOurPlayer() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getOurPlayer(); }

    public int GetX() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getX(); }
    public int GetY() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getY(); }

    public int GetRegionX() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getRegionX(); }
    public int GetRegionY() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getRegionY(); }

    public int GetFatigue() throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getFatigue(); }
    public int GetBaseStat(int id) throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getBaseStat(id); }
    public int GetCurrentStat(int id) throws NotLoggedInException, ScriptNotRunningException { return scriptMethods.getCurrentStat(id); }

    /*** Walk Commands ***/
    public void WalkToInCombat(int x, int y) throws NotLoggedInException, ScriptNotRunningException {
        scriptMethods.walkToInCombat(x,y);
    }
    public void WalkToInCombatWait(int x, int y) throws NotLoggedInException, ScriptNotRunningException {
        long lastWalkTo = System.currentTimeMillis();
        WalkTo(x, y);
        while (GetX() != x || GetY() != y) {
            if (System.currentTimeMillis() - lastWalkTo > 1500) {
                WalkToInCombat(x, y);
                lastWalkTo = System.currentTimeMillis();
            }
            Wait(200,300);
        }
    }
    public void WalkTo(int x, int y) throws NotLoggedInException, ScriptNotRunningException { scriptMethods.walkTo(x,y); }
    public void WalkToWait(int x1, int x2, int y1, int y2) throws NotLoggedInException, ScriptNotRunningException {
        long lastWalkTo = System.currentTimeMillis();

        while (!(GetX() >= x1) || !(GetX() <= x2) || !(GetY() >= y1) || !(GetY() <= y2)) {
            if (System.currentTimeMillis() - lastWalkTo > 1500) {

                try {
                    if (IsInCombat(GetOurPlayer()))
                        WalkToInCombat(Random(x1, x2), Random(y1, y2));
                    else
                        WalkTo(Random(x1, x2), Random(y1, y2));
                } catch (CharacterIsNullException e) {
                    Debug("Player object couldn't be obtained.");
                }
                lastWalkTo = System.currentTimeMillis();
            }
            Wait(200,300);
        }
    }
    public void WalkToWait(int x, int y) throws NotLoggedInException, ScriptNotRunningException {
        long lastWalkTo = System.currentTimeMillis();
        WalkTo(x, y);
        while (GetX() != x || GetY() != y) {
            if (System.currentTimeMillis() - lastWalkTo > 1500) {

                try {
                    if (IsInCombat(GetOurPlayer()))
                        WalkToInCombat(x, y);
                    else
                        WalkTo(x, y);
                } catch (CharacterIsNullException e) {
                    Debug("Player object couldn't be obtained.");
                }
                lastWalkTo = System.currentTimeMillis();
            }
            Wait(200,300);
        }
    }
    
    /*** Callbacks from HandleIncomingPacket ***/
    public Random random = new Random(1337);

    public int Random(int min, int max) { return (Math.abs(random.nextInt()) % (max - min)) + min; }

    public void Debug(String text) { scriptMethods.writeln("Debug: " + text);}
    public void Log(String text) { scriptMethods.writeln(scriptName + ": " + text); }
    public void Error(String text) { scriptMethods.writeln("Error: " + text); }

    public boolean IsLoggedIn() { return scriptMethods.isLoggedIn(); }

    public void Wait(long ms) {
        try
        {
            Thread.sleep(ms);
        }
        catch(Exception e)
        {
        }
    }
    public void Wait(int x, int y) {
        this.Wait(Random(x,y));
    }

    public String TimeToString(long millis) {
        String ret = "";

        long seconds = millis / 1000;
        millis = millis % 1000;
        if (millis > 0)
            ret = millis + "ms, " + ret;

        long minutes = seconds / 60;
        seconds = seconds % 60;
        if (seconds > 0)
            ret = seconds + "s, " + ret;

        long hours = minutes / 60;
        minutes = minutes % 60;
        if (minutes > 0)
            ret = minutes + "mins, " + ret;


        long days = hours / 24;
        hours = hours % 24;
        if (hours > 0)
            ret = hours + "hrs, " + ret;

        if (days > 0)
            ret = days + "days, " + ret;

        if (ret.length() > 2) return ret.substring(0, ret.length() - 2);
        else return ret;
    }

    public void SetRunning(boolean bool) {
        scriptMethods.setIsScriptRunning(bool);
    }

    public boolean Running() {
        return scriptMethods.IsScriptRunning();
    }
}
