package factory;

import model.Device;

public abstract class DeviceFactory {
    public abstract Device createDevice(Object... params);
}
