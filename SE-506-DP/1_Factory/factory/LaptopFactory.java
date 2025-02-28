package factory;

import model.Device;
import model.Laptop;

public class LaptopFactory extends DeviceFactory{
    @Override
    public Device createDevice(Object... laptopParams) {
        return new Laptop(laptopParams);
    }
}
