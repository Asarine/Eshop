package fr.adaming.service;

public class GenerationMotDePasse {
	
	public static String genererMotDePasse(){
		
		String Ato9 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder mdp=new StringBuilder();
		
		for (int i = 0; i < 8; i++) {
			
			int rnd=(int) Math.floor(Math.random()*Ato9.length());
			mdp=mdp.append(Ato9.charAt(rnd));
			
		}
		String password=mdp.toString();
		return password;
	}

}
