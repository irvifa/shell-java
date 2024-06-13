// src/shell/commands/PwdCommand.java
package shell.commands;

import shell.CommandInterface;
import java.util.List;

public class PwdCommand implements CommandInterface {
    @Override
    public boolean execute(List<String> args) {
        if (!args.isEmpty()) {
            System.err.println("pwd: too many arguments");
            return true;
        }

        String currentDir = System.getProperty("user.dir");
        System.out.println(currentDir);
        return true;
    }
}
