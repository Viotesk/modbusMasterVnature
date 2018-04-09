package ru.trein.factory.work.demo;

import net.wimpi.modbus.ModbusException;
import net.wimpi.modbus.msg.ReadCoilsResponse;
import net.wimpi.modbus.msg.ReadMultipleRegistersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import ru.trein.factory.ui.connect.ModbusConnect;
import ru.trein.factory.work.util.ModbusMapUltima;
import ru.trein.factory.work.util.RegisterType;

public class RegisterProvider {

    @Autowired
    ModbusConnect modbusConnect;

    public Object read(ModbusMapUltima register) throws ModbusException {
        Object valueToReturn;
        if (register.getType() == RegisterType.REGISTER) {
            ReadMultipleRegistersResponse response = (ReadMultipleRegistersResponse) modbusConnect.request(RegisterFactory.getRequest(register, false, null));
            if (register.getCount() > 1) {
                valueToReturn = RequestsUtils.createDouble(
                        (short) response.getRegister(0).getValue(),
                        (short) response.getRegister(1).getValue());
            } else {
                valueToReturn = response.getRegister(0).getValue();
            }
        } else {
            ReadCoilsResponse response = (ReadCoilsResponse) modbusConnect.request(RegisterFactory.getRequest(register, false, null));
            valueToReturn = response.getCoilStatus(0);
        }

        return valueToReturn;
    }

    public void write(ModbusMapUltima register, Object value) throws ModbusException {
        modbusConnect.request(RegisterFactory.getRequest(register, true, value));
    }
}
