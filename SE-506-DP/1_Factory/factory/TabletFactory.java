package factory;

import model.Device;
import model.Tablet;

public class TabletFactory extends DeviceFactory{
    @Override
    public Device createDevice(Object... tabletParams) {
        return new Tablet(tabletParams);
    }
}
