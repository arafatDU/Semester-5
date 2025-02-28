package factory;

import model.Device;
import model.SmartPhone;

public class SmartphoneFactory extends DeviceFactory{
    @Override
    public Device createDevice(Object... smartphnParam) {
        return new SmartPhone(smartphnParam);
    }
}
