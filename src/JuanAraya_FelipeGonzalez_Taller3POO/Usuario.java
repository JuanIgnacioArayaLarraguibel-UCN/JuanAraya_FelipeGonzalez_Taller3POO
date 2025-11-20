package JuanAraya_FelipeGonzalez_Taller3POO;

public class Usuario {
	private String username;
	private String password;
	private String rol;
	/**
	 * 
	 * @param username : indica el name del user
	 * @param password : indica la contra del user
	 * @param rol : indica el rol que cumple el user
	 */
	public Usuario(String username, String password, String rol) {
		this.username = username;
		this.password = password;
		this.rol = rol;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getRol() {
		return rol;
	}
}
