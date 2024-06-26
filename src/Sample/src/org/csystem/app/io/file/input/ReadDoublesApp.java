package org.csystem.app.io.file.input;

import org.csystem.util.converter.BitConverter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.csystem.util.console.CommandLineUtil.checkLengthEquals;
import static org.csystem.util.console.Console.*;

public class ReadDoublesApp {
    private static void readFile(String path)
    {
        try (FileInputStream fis = new FileInputStream(path)) {
            byte [] data = new byte[Double.BYTES];
            int n;

            while ((n = fis.read(data)) > 0) {
                if (n != Double.BYTES)
                    throw new IOException("Invalid file format!...");

                double val = BitConverter.toDouble(data);

                write("%f ", val);
            }

            writeLine();
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