package model;

public class SmartPhone implements Device{
    private String model;
    private int noOfCamera;
    private int ram;

    public SmartPhone(Object param []) {
        this.model = (String) param[0];
        this.noOfCamera = (int) param[1];
        this.ram = (int) param[2];
    }

    @Override
    public void powerOn() {
        System.out.println("SmartPhone power is on.");
    }

    @Override
    public void powerOff() {
        System.out.println("SmartPhone power is off");
    }

    @Override
    public void aboutDevice() {
        System.out.println("This is a SmartPhone.");
    }

    @Override
    public String toString() {
        return "Smartphone [model =" + model + ", number of Camera =" + noOfCamera +
                ", RAM =" + ram + " GB]";
    }
}
