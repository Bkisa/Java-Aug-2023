package org.csystem.app.io.file.input;

import org.csystem.util.converter.BitConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.csystem.util.console.CommandLineUtil.checkLengthEquals;
import static org.csystem.util.console.Console.writeErrLine;
import static org.csystem.util.console.Console.writeLine;

public class ReadStringsUTF16App {
    private static void readFile(String path)
    {
        try (FileInputStream fis = new FileInputStream(path)) {
            byte [] bufLen = new byte[Integer.BYTES];
            int n;

            while (true) {
                n = fis.read(bufLen);
                if (n < 0)
                    break;

                if (n != 4)
                    throw new IOException("Invalid file format!...");

                byte [] bufStr = new byte[BitConverter.toInt(bufLen)];

                n = fis.read(bufStr);

                if (n != bufStr.length)
                    throw new IOException("Invalid file format!...");

                String str = BitConverter.getString(bufStr, StandardCharsets.UTF_16);

                writeLine(str);
            }
        }
        catch (FileNotFoundException ignore) {
            writeErrLine("%s not found", path);
        }
        catch (IOException ex) {
            writeErrLine("IO error occurred:%s",ex.getMessage());
        }
    }

    public static void run(String[] args)
    {
        checkLengthEquals(1, args.length, "wrong number of arguments");

        try  {
            readFile(args[0]);
        }
        catch (NumberFormatException ignore) {
            writeErrLine("Invalid block size value!...");
        }
        catch (Throwable ex) {
            writeErrLine("Problem occurred:%s", ex.getMessage());
        }
    }

    public static void main(String[] args)
    {
        run(args);
    }
}