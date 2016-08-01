package modeloVO;

import java.sql.Date;

/**
 * @author Kiezmi
 */

public class FacturaVO {

	private String Num_Factura;
	private Date Fecha_Factura;
	ClienteVO cliente;

	/**
	 * @param num_Factura
	 * @param fecha_Factura
	 * @param cliente
	 */
	public FacturaVO() {
	}

	public FacturaVO(String Num_Factura, Date Fecha_Factura, ClienteVO cliente) {
		super();
		this.Num_Factura = Num_Factura;
		this.Fecha_Factura = Fecha_Factura;
		this.cliente = cliente;
	}

	/**
	 * @return the num_Factura
	 */
	public String getNum_Factura() {
		return Num_Factura;
	}

	/**
	 * @return the fecha_Factura
	 */
	public Date getFecha_Factura() {
		return Fecha_Factura;
	}

	/**
	 * @return the cliente
	 */
	public ClienteVO getCliente() {
		return cliente;
	}

	/**
	 * @param num_Factura
	 *            the num_Factura to set
	 */
	public void setNum_Factura(String Num_Factura) {
		this.Num_Factura = Num_Factura;
	}

	/**
	 * @param fecha_Factura
	 *            the fecha_Factura to set
	 */
	public void setFecha_Factura(Date Fecha_Factura) {
		this.Fecha_Factura = Fecha_Factura;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FacturaVO [Num=" + Num_Factura + ", Fecha=" + Fecha_Factura + ", cliente=" + cliente + "]";
	}

}