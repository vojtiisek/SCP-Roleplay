package cz.vojtiisek.scprpsystem.Constructions;

import java.util.List;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.SCPRP;

public class KickConv1 extends ValidatingPrompt {
	private String creator;
	private static SCPRP main;
	private Inventory inv;
	
	private List<String> members;
	
	public KickConv1(SCPRP main, String creator, List<String> members)  {
		this.main = main;
		this.creator = creator;
		this.inv = inv;
		this.members = members;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§e§oState the Technic, who you want to kick out of the construction:";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext conv, String str) {
		ConstructionsMenu.kickTechnic(str);
		conv.getForWhom().sendRawMessage("§8[§a§l!§8] §a§oSuccessfully kicked technic §6§o" + str + "§a§o.");
		return Prompt.END_OF_CONVERSATION;
	}

	@Override
	protected boolean isInputValid(ConversationContext conv, String str) {
		if(!members.contains(str)) {
			conv.getForWhom().sendRawMessage("§c§oThe stated nickname must be 1:1 with the technic's nick. §8(Upper & lowercase letters must stay in upper/lowercase, etc.)");
			return false;
		}
		return true;
	}	
}