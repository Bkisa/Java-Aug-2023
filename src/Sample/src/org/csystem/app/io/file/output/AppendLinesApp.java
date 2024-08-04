package org.csystem.app.io.file.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.csystem.util.console.CommandLineUtil.checkLengthEquals;
import static org.csystem.util.console.Console.readString;
import static org.csystem.util.console.Console.writeErrLine;

public class AppendLinesApp {
    private static void writeFile(String path)
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, StandardCharsets.UTF_8, true))) {
            while (true) {
                String str = readString("Input string:");

                if ("exit".equals(str))
                    break;

                bw.write(str);
                bw.newLine();
            }
        }
        catch (IOException ex) {
            writeErrLine("IO error occurred:%s",ex.getMessage());
        }
    }

    public static void run(String[] args)
    {
        checkLengthEquals(1, args.length, "wrong number of arguments");

        try {
            writeFile(args[0]);
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