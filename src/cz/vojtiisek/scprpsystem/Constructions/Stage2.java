package cz.vojtiisek.scprpsystem.Constructions;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.FixedSetPrompt;
import org.bukkit.conversations.Prompt;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.SCPRP;

public class Stage2 extends FixedSetPrompt {
	private static SCPRP main;
	private String creator;
	private String name;
	private Inventory inv;
	
	public Stage2(SCPRP main, String creator, String name, Inventory inv)  {
		super("1", "2", "3", "4");
		this.main = main;
		this.creator = creator;
		this.name = name;
		this.inv = inv;
	}

	@Override
	public String getPromptText(ConversationContext text) {
		return "§6Alright. Now, enter the minimum required level for the Technic to join the construction §8§o(§e§o1§8§o up to §e§o4§8§o)§6. §c§oMore information about Technic levels in Site Director documents.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext conv, String str) {
		return new Stage3(SCPRP.getInstance(), creator, name, str, inv);
	}
}