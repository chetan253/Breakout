
package commands;

import java.util.ArrayList;
import java.util.Collections;

import entity.Clock;

public class MacroCommand implements Command {

	private ArrayList<Command> commands;

	public MacroCommand() {
		this.commands = new ArrayList<>();
	}

	@Override
	public void execute() {
		for (Command c : commands) {
			c.execute();
		}
	}

	@Override
	public void undo() {
		Collections.reverse(commands);
		for (Command c : commands) {
			c.undo();
		}
	}

	public void add(Command command) {
		commands.add(command);
	}
}
