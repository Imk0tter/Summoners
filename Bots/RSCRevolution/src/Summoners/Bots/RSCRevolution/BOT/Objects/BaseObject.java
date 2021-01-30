package Summoners.Bots.RSCRevolution.BOT.Objects;

import client.Class57;

public abstract class BaseObject {
    protected Class57 object;

    public BaseObject(Class57 baseObject) {
        this.object = baseObject;
    }

    public int getUnknown1() {
        return object.method860();
    }
    public int getId() { return object.method863(); }
    public void setId(int id) { object.method846(id); }

    public void setCoordinates(int x, int y) { object.method851(x,y); }
    public void setCurrentCoordinates(int currentX, int currentY) { object.method847(currentX, currentY); }

    public int getX() { return object.method861(); }
    public int getY() { return object.method860(); }

    public int currentX() { return object.method859(); }
    public int currentY() { return object.method855(); }

    public int getServerIndex() { return object.method862(); }
    public void setServerIndex(int serverIndex) { object.method853(serverIndex); }


}
