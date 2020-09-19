package pojoandData;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CreateUserPOJO {
	
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
