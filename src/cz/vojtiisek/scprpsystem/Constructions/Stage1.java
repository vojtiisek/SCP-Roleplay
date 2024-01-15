package cz.vojtiisek.scprpsystem.Constructions;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;

public class Stage1 extends StringPrompt {
	private String creator;
	private static SCPRP main;
	private Inventory inv;
	
	public Stage1(SCPRP main, String creator, Inventory inv)  {
		this.main = main;
		this.creator = creator;
		this.inv = inv;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§6Welcome to the Construction Creator Wizard. Please, state the name of the construction. §8§oExample: §e§oSCP-123§8§o, or §eNew bathroom in ScD Dept§8§o. §c§o(Type §4§lexit§c§o to end the wizard)";
	}

	@Override
	public Prompt acceptInput(ConversationContext conv, String str) {
		conv.setSessionData("Name", str);
		return new Stage2(SCPRP.getInstance(), creator, str, inv);
	}	
}