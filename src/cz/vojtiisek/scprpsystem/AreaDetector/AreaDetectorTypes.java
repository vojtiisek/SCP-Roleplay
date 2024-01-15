package cz.vojtiisek.scprpsystem.AreaDetector;

public enum AreaDetectorTypes {
	MINE,
	DETECTOR,
	ERROR;
	
	public static AreaDetectorTypes getTypeByName(String type) {
		type = type.toLowerCase();
		AreaDetectorTypes returnType = ERROR;
		if(type.equals("mine")) {
			returnType = MINE;
		} else if(type.equals("detector")) {
			returnType = DETECTOR;
		} else;
		
	return returnType;
	}
}
