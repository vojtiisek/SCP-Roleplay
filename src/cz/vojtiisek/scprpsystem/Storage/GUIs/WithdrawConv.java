package cz.vojtiisek.scprpsystem.Storage.GUIs;

import org.bukkit.Bukkit;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;

public class WithdrawConv extends ValidatingPrompt {
	private static SCPRP main;
	
	private String item;
	private String maxAmount;
	private String dept;
	private String playerName;
	
	public WithdrawConv(SCPRP main, String dept, String item, String maxAmount, String playerName)  {
		this.main = main;
		this.item = item;
		this.maxAmount = maxAmount;
		this.dept = dept;
		this.playerName = playerName;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§e§oState the amount you would want to withdraw. Selected item: §f§l" + item + "§e§o. Amount in stock: §c§l" + maxAmount + "§e§o. Type §c§lcancel§e§o to cancel the process.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext conv, String str) {
		StorageDatabase.withdrawItem(dept, item, str, Bukkit.getPlayerExact(playerName));
		API.sendConsoleCommand("discord broadcast #856854333328064512 Withdraw: Player " + playerName + " withdrawn " + item + ": " + str + ".");
		conv.getForWhom().sendRawMessage("§8[§6§lE&Ts§8] §a§oSuccessfully withdrawed §f§l" + item + "§a§o: §e§l" + str + "§a§o.");
		return Prompt.END_OF_CONVERSATION;
	}

	@Override
	protected boolean isInputValid(ConversationContext conv, String str) {
		if(!API.isInteger(str)) {
			conv.getForWhom().sendRawMessage("§c§oAmount must be a number!");
			return false;
		} else {
			int amount = API.parse(str);
			if(amount > API.parse(maxAmount)) {
				conv.getForWhom().sendRawMessage("§c§oThe amount of this in stock is §4§l" + maxAmount + "§c§o. You can not withdraw more than that.");
				return false;
			}
		}
		return true;
	}	
}