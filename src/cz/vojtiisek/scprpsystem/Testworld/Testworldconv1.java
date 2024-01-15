package cz.vojtiisek.scprpsystem.Testworld;

import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;

public class Testworldconv1 extends ValidatingPrompt {

	private static SCPRP main;
	private String player;
	
	static LuckPerms api = LuckPermsProvider.get();

	public Testworldconv1(SCPRP main, String player) {
		this.main = main;
		this.player = player;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§8[§9§l?§8] §e§oState the Technic, who you want to teleport to the Test World. §6§oType §c§lexit§6§o to cancel the process.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext conv, String str) {
		API.teleportTechnicToTestworld(player, str);
		conv.getForWhom().sendRawMessage("§8[§a§l!§8] §a§oSuccessfully teleported technic §6§o" + str + "§a§o with you.");
		return Prompt.END_OF_CONVERSATION;
	}

	@Override
	protected boolean isInputValid(ConversationContext conv, String str) {

		if (Bukkit.getPlayerExact(str) == null || !Bukkit.getPlayerExact(str).isOnline()) {
			conv.getForWhom().sendRawMessage(
					"§8[§c§l!§8] §c§oThe stated nickname must be 1:1 with the technic's nick. §8(Upper & lowercase letters must stay in upper/lowercase, etc.)");
			return false;
		}

		if (!api.getUserManager().getUser(str).getPrimaryGroup().equalsIgnoreCase("technic")) {
			conv.getForWhom().sendRawMessage("§8[§c§l!§8] §c§oThe stated player is not a technic!");
			return false;
		}
		
		return true;
	}
}