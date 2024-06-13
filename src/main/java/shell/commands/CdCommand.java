// src/shell/commands/CdCommand.java
package shell.commands;

import shell.CommandInterface;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CdCommand implements CommandInterface {
    @Override
    public boolean execute(List<String> args) {
        if (args.size() != 1) {
            System.err.println("cd: invalid number of arguments");
            return true;
        }

        String path = args.get(0);
        Path targetPath;

        if (path.equals("~")) {
            targetPath = Paths.get(System.getProperty("user.home"));
        } else {
            targetPath = Paths.get(path);
            if (!targetPath.isAbsolute()) {
                targetPath = Paths.get(System.getProperty("user.dir")).resolve(targetPath).normalize();
            }
        }

        File dir = targetPath.toFile();

        if (dir.exists() && dir.isDirectory()) {
            System.setProperty("user.dir", dir.getAbsolutePath());
        } else {
            System.err.println("cd: " + path + ": No such file or directory");
        }
        return true;
    }
}
