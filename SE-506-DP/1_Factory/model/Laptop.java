package model;

public class Laptop implements Device{
    private String os;
    private int ram;
    private int ssd;

    public Laptop(Object... laptopParams) {
        this.os = (String) laptopParams[0];
        this.ram = (int) laptopParams[1];
        this.ssd = (int) laptopParams[2];
    }

    @Override
    public void powerOn() {
        System.out.println("Laptop's power is on.");
    }

    @Override
    public void powerOff() {
        System.out.println("Laptop's power is off.");
    }

    @Override
    public void aboutDevice() {
        System.out.println("This is a Laptop.");
    }

    @Override
    public String toString() {
        return "Smartphone [ OS = " + os + ", RAM = " + ram + " GB" +
                ", SSD =" + ssd + " GB]";
    }
}
