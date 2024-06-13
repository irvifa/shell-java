// CdCommand.java
package shell.commands;

import shell.CommandInterface;
import java.io.File;
import java.util.List;

public class CdCommand implements CommandInterface {
    @Override
    public boolean execute(List<String> args) {
        if (args.size() != 1) {
            System.err.println("cd: invalid number of arguments");
            return true;
        }

        String path = args.get(0);
        if (path.equals("~")) {
            path = System.getProperty("user.home");
        }

        File dir = new File(path);
        if (dir.exists() && dir.isDirectory()) {
            System.setProperty("user.dir", dir.getAbsolutePath());
        } else {
            System.err.println("cd: " + path + ": No such file or directory");
        }
        return true;
    }
}
