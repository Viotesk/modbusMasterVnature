package ru.trein.factory.work.util;

public enum ModbusMapUltima {

    FirmwareVersion(RegisterType.REGISTER, 116, 1),
    BoostVoltage(RegisterType.REGISTER, 120, 2),
    RLCStart(RegisterType.COIL, 41, 1);



    RegisterType type;
    int ref;
    int count;

    ModbusMapUltima(RegisterType type, int ref, int count) {
        this.type = type;
        this.ref = ref;
        this.count = count;
    }

    public RegisterType getType() {
        return type;
    }

    public int getRef() {
        return ref;
    }

    public int getCount() {
        return count;
    }
}
