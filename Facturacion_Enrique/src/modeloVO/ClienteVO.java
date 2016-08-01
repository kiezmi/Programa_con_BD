package modeloVO;

/**
 * @author Kiezmi
 */

public class ClienteVO {

	private String NIF_Cliente;
	private String Nombre_Cliente;
	private String Direccion_Cliente;

	public ClienteVO() {

	}

	public ClienteVO(String NIF_Cliente, String Nombre_Cliente, String Direccion_Cliente) {
		super();
		this.NIF_Cliente = NIF_Cliente;
		this.Nombre_Cliente = Nombre_Cliente;
		this.Direccion_Cliente = Direccion_Cliente;
	}

	public String getNIF_Cliente() {
		return NIF_Cliente;
	}

	public String getNombre_Cliente() {
		return Nombre_Cliente;
	}

	public String getDireccion_Cliente() {
		return Direccion_Cliente;
	}

	public void setNIF_Cliente(String NIF_Cliente) {
		this.NIF_Cliente = NIF_Cliente;
	}

	public void setNombre_Cliente(String Nombre_Cliente) {
		this.Nombre_Cliente = Nombre_Cliente;
	}

	public void setDireccion_Cliente(String Direccion_Cliente) {
		this.Direccion_Cliente = Direccion_Cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ClienteVO [NIF=" + NIF_Cliente + ", Nombre=" + Nombre_Cliente + ", Direccion=" + Direccion_Cliente
				+ "]";
	}

}
