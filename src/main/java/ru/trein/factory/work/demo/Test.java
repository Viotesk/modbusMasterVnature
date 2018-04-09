package ru.trein.factory.work.demo;

import net.wimpi.modbus.ModbusException;
import ru.trein.factory.work.util.ModbusMapUltima;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {
    public void start() throws IOException, ModbusException {
        RegisterProvider registerProvider = new RegisterProvider();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter the command");

        String s = "";

        while (!(s =reader.readLine()).equalsIgnoreCase("exit")) {
            switch (s) {
                case "1":
                    System.err.println(registerProvider.read(ModbusMapUltima.FirmwareVersion));
                    break;
                case "2":
                    registerProvider.write(ModbusMapUltima.FirmwareVersion, 100);
                    break;
                case "3":
                    System.err.println(registerProvider.read(ModbusMapUltima.BoostVoltage));
                    break;
                case "4":
                    registerProvider.write(ModbusMapUltima.BoostVoltage, 100);
                    break;
                case "5":
                    System.err.println(registerProvider.read(ModbusMapUltima.RLCStart));
                    break;
                case "6":
                    registerProvider.write(ModbusMapUltima.RLCStart, true);
                    break;
            }
        }
        reader.close();
    }
}
