// ExitCommand.java
package shell.commands;

import shell.CommandInterface;
import java.util.List;

public class ExitCommand implements CommandInterface {
    @Override
    public boolean execute(List<String> args) {
        if (args.size() == 1) {
            try {
                int exitCode = Integer.parseInt(args.get(0));
                System.exit(exitCode);
            } catch (NumberFormatException e) {
                System.err.println("exit: invalid status code");
            }
        } else {
            System.err.println("exit: invalid number of arguments");
        }
        return true;
    }
}
