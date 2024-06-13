// shell/commands/TypeCommand.java
package shell.commands;

import shell.CommandInterface;
import java.io.File;
import java.util.List;
import java.util.Set;

public class TypeCommand implements CommandInterface {
    private final Set<String> builtins;

    public TypeCommand(Set<String> builtins) {
        this.builtins = builtins;
    }

    @Override
    public boolean execute(List<String> args) {
        if (args.size() != 1) {
            System.err.println("type: invalid number of arguments");
            return true;
        }

        String commandName = args.get(0);
        if (builtins.contains(commandName)) {
            System.out.println(commandName + " is a shell builtin");
            return true;
        }

        String path = System.getenv("PATH");
        if (path == null) {
            System.err.println("type: PATH environment variable not set");
            return true;
        }

        for (String dir : path.split(":")) {
            File file = new File(dir, commandName);
            if (file.exists() && file.canExecute()) {
                System.out.println(commandName + " is " + file.getAbsolutePath());
                return true;
            }
        }

        System.out.println(commandName + ": not found");
        return true;
    }
}
