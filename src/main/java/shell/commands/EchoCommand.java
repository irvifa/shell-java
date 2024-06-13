// EchoCommand.java
package shell.commands;

import shell.CommandInterface;
import java.util.List;

public class EchoCommand implements CommandInterface {
    @Override
    public boolean execute(List<String> args) {
        boolean omitNewline = false;
        if (!args.isEmpty() && args.get(0).equals("-n")) {
            omitNewline = true;
            args = args.subList(1, args.size());
        }

        String output = String.join(" ", args);
        System.out.print(output);
        if (!omitNewline) {
            System.out.println();
        }
        return true;
    }
}
