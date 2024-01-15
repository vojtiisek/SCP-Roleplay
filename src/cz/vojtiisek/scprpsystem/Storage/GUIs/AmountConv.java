package cz.vojtiisek.scprpsystem.Storage.GUIs;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;

import cz.vojtiisek.scprpsystem.API;
import cz.vojtiisek.scprpsystem.SCPRP;

public class AmountConv extends ValidatingPrompt {
	private static SCPRP main;
	
	private String item;
	private String maxAmount;
	private String dept;
	private String playerName;
	
	public AmountConv(SCPRP main, String dept, String item, String maxAmount, String playerName)  {
		this.main = main;
		this.item = item;
		this.maxAmount = maxAmount;
		this.dept = dept;
		this.playerName = playerName;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§e§oState the amount you would want to order. Selected item: §f§l" + item + "§e§o. Maximum amount: §c§l" + maxAmount + "§e§o. Type §c§lcancel§e§o to cancel the order.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext conv, String str) {
		StorageDatabase.orderItem(dept, item, str);
		API.sendConsoleCommand("discord broadcast #856854333328064512 Order: Player " + playerName + " ordered " + item + ": " + str + ".");
		conv.getForWhom().sendRawMessage("§8[§6§lE&Ts§8] §a§oSuccessfully ordered §f§l" + item + "§a§o: §e§l" + str + "§a§o.");
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
				conv.getForWhom().sendRawMessage("§c§oThe maximum allowed amount for this item is §4§l" + maxAmount + "§c§o!");
				return false;
			}
		}
		return true;
	}	
}