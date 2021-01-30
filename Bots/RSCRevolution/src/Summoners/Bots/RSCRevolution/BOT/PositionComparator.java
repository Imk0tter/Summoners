package Summoners.Bots.RSCRevolution.BOT;

import Summoners.Bots.RSCRevolution.BOT.Objects.BaseObject;

import java.util.Comparator;

public class PositionComparator implements Comparator<BaseObject> {
    int x;
    int y;

    mudclient client;

    public PositionComparator(mudclient client, int x, int y) {

        this.client = client;
        this.x = x;
        this.y = y;

    }

    @Override
    public int compare(BaseObject o1, BaseObject o2) {
        int tempX = o1.getX() + this.client.clientGetRegionX();
        int tempY = o1.getY() + this.client.clientGetRegionY();

        int tempX2 = o2.getX() + this.client.clientGetRegionX();
        int tempY2 = o2.getY() + this.client.clientGetRegionY();

        double tempa = Math.sqrt(Math.pow(Math.abs(tempX - this.x), 2) + Math.pow(Math.abs(tempY - this.y), 2));

        double tempb = Math.sqrt(Math.pow(Math.abs(tempX2 - this.x), 2) + Math.pow(Math.abs(tempY2 - this.y), 2));

        return (int) (tempa - tempb);
    }
}
