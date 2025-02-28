package model;

public class Tablet implements Device{
    private String model;
    private double screenSize;
    private int storage;

    public Tablet(Object... params) {
        this.model = (String) params[0];
        this.screenSize = (double) params[1];
        this.storage = (int) params[2];
    }

    @Override
    public void powerOn() {
        System.out.println("Tablet's power is on");
    }

    @Override
    public void powerOff() {
        System.out.println("Tablet's power is off.");
    }

    @Override
    public void aboutDevice() {
        System.out.println("This is a Tablet.");
    }


    @Override
    public String toString() {
        return "Tablet [model =" + model + ", screen size =" + screenSize +
                ", Storage =" + storage + " GB]";
    }


}
