package pojoandData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateUserPOJO {
	
	public CreateUserPOJO(String name, String job) {
		this.name = name;
		this.job = job;
	}
	
	private String name;
	private String job;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
		

}
