package data;

public class Moive {
	private int id = 0;
	private String moiveName="";
	private String moiveDir="";
	private String moiveDuration = "";
	private String moiveDescription = "";
	
	public void setId(int i) {
		id = i;
	}
	public int getId() {
		return id;
	}
	
	public void setName(String s) {
		moiveName = s;
	}
	public String getName() {
		return moiveName;
	}
	
	public void setDir(String s) {
		moiveDir = s;
	}
	public String getDir() {
		return moiveDir;
	}
	
	public void setDuration(String s){
		moiveDuration = s;
	}
	public String getDuration() {
		return moiveDuration;
	}
	
	public void setDescription(String s) {
		moiveDescription = s;
	}
	public String getDescription() {
		return moiveDescription;
	}
}
