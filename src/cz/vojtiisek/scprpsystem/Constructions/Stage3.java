package cz.vojtiisek.scprpsystem.Constructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.inventory.Inventory;

import cz.vojtiisek.scprpsystem.SCPRP;

public class Stage3 extends StringPrompt {
	private static SCPRP main;
	private String creator;
	private String name;
	private String level;
	private Inventory inv;
	
	public Stage3(SCPRP main, String creator, String name, String level, Inventory inv)  {
		this.main = main;
		this.creator = creator;
		this.name = name;
		this.level = level;
		this.inv = inv;
	}

	@Override
	public String getPromptText(ConversationContext conv) {
		return "§6And the last step - describe the construction in a short sentence. §8§oExample: §e§oThe bathroom looks so old, it needs a new look.";
	}
	@Override
	public Prompt acceptInput(ConversationContext conv, String str) {
		conv.setSessionData("Description", str);
		List<String> list = new ArrayList<String>();
		list = Arrays.asList(name, level, str);
		if(ConstructionsMenu.getConstructions().containsKey(creator)) {
			ConstructionsMenu.getConstructions().get(creator).add(list);
		} else {
			List<List<String>> listOfLists = new ArrayList<List<String>>();
			listOfLists.add(list);
			ConstructionsMenu.getConstructions().put(creator, listOfLists);
		}

		if(ConstructionsMenu.getConstructions().containsKey(creator)) {
			ConstructionsMenu.completeCreation(creator, name, inv);
		}
		return Prompt.END_OF_CONVERSATION;
	}
}
