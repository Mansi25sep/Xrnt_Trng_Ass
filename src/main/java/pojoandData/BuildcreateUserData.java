package pojoandData;


public class BuildcreateUserData {
	
	public CreateUserPOJO createUserData(String name, String job) {
		
		CreateUserPOJO crtUser = new CreateUserPOJO(name, job);
		
		crtUser.setName(name);
		crtUser.setJob(job);
		
		return crtUser;
		
	}
	
	

}
