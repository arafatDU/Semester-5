package client;

import factory.DeviceFactory;
import factory.LaptopFactory;
import factory.SmartphoneFactory;
import factory.TabletFactory;
import model.Device;

public class Main {
    public static void main(String[] args) {
        DeviceFactory smartphnFactory = new SmartphoneFactory();
        Device smartphn = smartphnFactory.createDevice("Samsung", 3, 8);
        smartphn.powerOff();
        System.out.println(smartphn);

        DeviceFactory tabletFactory = new TabletFactory();
        Device tablet = tabletFactory.createDevice("iPad", 10.7, 256);
        tablet.powerOff();
        System.out.println(tablet);

        DeviceFactory laptopFactory =  new LaptopFactory();
        Device laptop = laptopFactory.createDevice("MacOS", 8, 256);
        laptop.aboutDevice();
        System.out.println(laptop);


    }
}
