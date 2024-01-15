package cz.vojtiisek.scprpsystem.Kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Kits {
	CLASSD, MEDIC, TECHNIC, RESEARCHER1, RESEARCHER2, RESEARCHER3, RESEARCHER4, SECOFF1, SECOFF2, SECOFF3, SECOFF4,
	MTFNU71, MTFNU72, MTFNU73, MTFNU74, MTFNU7CMD, HEADMEDIC, HEADRES, HEADSECOFF, SITEDIR, O51, O52, O53, O54, O55,
	O56, THEADMIN, STORAGELEADER, JUDGE, O57;

	public static List<String> getRoleKit(Kits role) {
		List<String> items = new ArrayList<String>();
		switch (role) {
		case CLASSD:
			String[] classd = { "SCPSARMOR20_CLASSDBODY", "SCPSARMOR20_CLASSDLEGS", "LEATHER_BOOTS" }; 
			items = Arrays.asList(classd);
			break;
		case MEDIC:
			String[] medic = { "SCP_LEVEL2CARD", "SCPSARMOR20_MEDICARMORBODY", "SCPSARMOR20_MEDICARMORLEGS",
					"LEATHER_BOOTS" }; 
			items = Arrays.asList(medic);
			break;
		case TECHNIC:
			String[] tech = { "SCP_LEVEL2CARD", "TECHGUNS_T1_MINER_HELMET", "TECHGUNS_T1_MINER_CHESTPLATE",
					"TECHGUNS_T1_MINER_LEGGINGS", "TECHGUNS_T1_MINER_BOOTS" }; 
			items = Arrays.asList(tech);
			break;
		case STORAGELEADER:
			String[] storageleader = { "SCP_LEVEL3CARD", "TECHGUNS_T1_MINER_HELMET", "TECHGUNS_T1_MINER_CHESTPLATE",
					"TECHGUNS_T1_MINER_LEGGINGS", "TECHGUNS_T1_MINER_BOOTS" }; 
			items = Arrays.asList(storageleader);
			break;
		case JUDGE:
			String[] judge = {"SCP_LEVEL3CARD"}; 
			items = Arrays.asList(judge);
			break;
		case RESEARCHER1:
			String[] res1 = { "SCP_LEVEL2CARD", "SCPSARMOR20_SCIENTISTSARMORBODY", "SCPSARMOR20_SCIENTISTSARMORLEGS",
					"LEATHER_BOOTS" }; 
			items = Arrays.asList(res1);
			break;
		case RESEARCHER2:
			String[] res2 = { "SCP_LEVEL2CARD", "SCPSARMOR20_SCIENTISTSARMORBODY", "SCPSARMOR20_SCIENTISTSARMORLEGS",
					"LEATHER_BOOTS" }; 
			items = Arrays.asList(res2);
			break;
		case RESEARCHER3:
			String[] res3 = { "SCP_LEVEL3CARD", "SCPSARMOR20_SCIENTISTSARMORBODY", "SCPSARMOR20_SCIENTISTSARMORLEGS",
					"LEATHER_BOOTS" }; 
			items = Arrays.asList(res3); 
			break;
		case RESEARCHER4:
			String[] res4 = { "SCP_LEVEL4CARD", "SCPSARMOR20_SCIENTISTSARMORBODY", "SCPSARMOR20_SCIENTISTSARMORLEGS",
					"LEATHER_BOOTS" }; 
			items = Arrays.asList(res4);
			break;
		case SECOFF1:
			String[] sect = { "SCP_LEVEL2CARD", "TECHGUNS_T2_COMMANDO_HELMET", "TECHGUNS_T2_COMMANDO_CHESTPLATE",
					"TECHGUNS_T2_COMMANDO_LEGGINGS", "TECHGUNS_T2_COMMANDO_BOOTS", "TECHGUNS_PISTOL", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(sect);
			break;
		case SECOFF2:
			String[] sec2 = { "SCP_LEVEL2CARD", "TECHGUNS_T2_COMMANDO_HELMET", "TECHGUNS_T2_COMMANDO_CHESTPLATE",
					"TECHGUNS_T2_COMMANDO_LEGGINGS", "TECHGUNS_T2_COMMANDO_BOOTS", "TECHGUNS_PISTOL", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(sec2);
			break;
		case SECOFF3:
			String[] sec3 = { "SCP_LEVEL3CARD", "TECHGUNS_T2_COMMANDO_HELMET", "TECHGUNS_T2_COMMANDO_CHESTPLATE",
					"TECHGUNS_T2_COMMANDO_LEGGINGS", "TECHGUNS_T2_COMMANDO_BOOTS", "TECHGUNS_MAC10", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(sec3);
			break;
		case SECOFF4:
			String[] sec4 = { "SCP_LEVEL4CARD", "TECHGUNS_T2_COMMANDO_HELMET", "TECHGUNS_T2_COMMANDO_CHESTPLATE",
					"TECHGUNS_T2_COMMANDO_LEGGINGS", "TECHGUNS_T2_COMMANDO_BOOTS", "TECHGUNS_MAC10",
					"TECHGUNS_COMBATSHOTGUN", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(sec4);
			break;
		case MTFNU71:
			String[] mtft = { "SCP_LEVEL2CARD", "TECHGUNS_T3_COMBAT_HELMET", "TECHGUNS_T3_COMBAT_CHESTPLATE",
					"TECHGUNS_T3_COMBAT_LEGGINGS", "TECHGUNS_T3_COMBAT_BOOTS", "TECHGUNS_MAC10",
					"TECHGUNS_COMBATKNIFE", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(mtft);
			break;
		case MTFNU72:
			String[] mtf2 = { "SCP_LEVEL3CARD", "TECHGUNS_T3_COMBAT_HELMET", "TECHGUNS_T3_COMBAT_CHESTPLATE",
					"TECHGUNS_T3_COMBAT_LEGGINGS", "TECHGUNS_T3_COMBAT_BOOTS", "TECHGUNS_M4_INFILTRATOR",
					"TECHGUNS_PISTOL", "TECHGUNS_COMBATKNIFE", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(mtf2);
			break;
		case MTFNU73:
			String[] mtf3 = { "SCP_LEVEL3CARD", "TECHGUNS_T3_COMBAT_HELMET", "TECHGUNS_T3_COMBAT_CHESTPLATE",
					"TECHGUNS_T3_COMBAT_LEGGINGS", "TECHGUNS_T3_COMBAT_BOOTS", "TECHGUNS_AUG", "TECHGUNS_COMBATSHOTGUN",
					"TECHGUNS_COMBATKNIFE", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(mtf3);
			break;
		case MTFNU74:
			String[] mtf4 = { "SCP_LEVEL4CARD", "TECHGUNS_T3_COMBAT_HELMET", "TECHGUNS_T3_COMBAT_CHESTPLATE",
					"TECHGUNS_T3_COMBAT_LEGGINGS", "TECHGUNS_T3_COMBAT_BOOTS", "TECHGUNS_LMG", "TECHGUNS_VECTOR",
					"TECHGUNS_COMBATKNIFE", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(mtf4);
			break;
		case MTFNU7CMD:
			String[] mtfcmd = { "SCP_LEVEL4CARD", "TECHGUNS_T3_MINER_HELMET", "TECHGUNS_T3_MINER_CHESTPLATE",
					"TECHGUNS_T3_MINER_LEGGINGS", "TECHGUNS_T3_MINER_BOOTS", "TECHGUNS_LMG", "TECHGUNS_TESLAGUN",
					"TECHGUNS_COMBATKNIFE", "SECURITYCRAFT_TASER_POWERED"};
			items = Arrays.asList(mtfcmd);
			break;
		case HEADMEDIC:
			String[] hmedic = { "SCP_LEVEL4CARD", "SCPSARMOR20_MEDICARMORBODY", "SCPSARMOR20_MEDICARMORLEGS",
					"LEATHER_BOOTS"}; 
			items = Arrays.asList(hmedic);
			break;
		case HEADRES:
			String[] hres = { "SCP_LEVEL4CARD", "SCPSARMOR20_SCIENTISTSARMORBODY", "SCPSARMOR20_SCIENTISTSARMORLEGS",
					"LEATHER_BOOTS"}; 
			items = Arrays.asList(hres);
			break;
		case HEADSECOFF:
			String[] hsec = { "SCP_LEVEL4CARD", "TECHGUNS_T2_BERET", "TECHGUNS_T2_COMMANDO_CHESTPLATE",
					"TECHGUNS_T2_COMMANDO_LEGGINGS", "TECHGUNS_T2_COMMANDO_BOOTS", "TECHGUNS_M4_INFILTRATOR",
					"TECHGUNS_PISTOL"};
			items = Arrays.asList(hsec);
			break;
		case SITEDIR:
			String[] sd = { "SCP_LEVEL5CARD", "SCPSARMOR20_ADMINISTRATORBODY", "SCPSARMOR20_ADMINISTRATORLEGS", "LEATHER_BOOTS" }; 
			items = Arrays.asList(sd);
			break;
		case O51:
			String[] o51 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o51);
			break;
		case O52:
			String[] o52 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o52);
			break;
		case O53:
			String[] o53 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o53);
			break;
		case O54:
			String[] o54 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o54);
			break;
		case O55:
			String[] o55 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o55);
			break;
		case O56:
			String[] o56 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o56);
			break;
		case O57:
			String[] o57 = { "SCP_LEVEL5CARD", "SCPSARMOR20_COUNCILBODY", "SCPSARMOR20_COUNCILLEGS", "LEATHER_BOOTS", "SECURITYCRAFT_TASER_POWERED"}; 
			items = Arrays.asList(o57);
			break;
		case THEADMIN:
			String[] ta = { "SCP_LEVEL5CARD","TECHGUNS_GOLDENREVOLVER"};
			items = Arrays.asList(ta);
			break;
		default:
			String[] emptyerr = { "EMPTYERR"};
			items = Arrays.asList(emptyerr);
			break;
		}
		return items;
	}
}