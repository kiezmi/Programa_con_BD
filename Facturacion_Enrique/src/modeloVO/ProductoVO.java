package modeloVO;

import java.math.BigDecimal;

/**
 * CLase VO con los atributos del campo empleado
 * 
 * @author Kiezmi
 *
 */

public class ProductoVO {

	private int ID_Producto;
	private String Nombre_Producto;
	private BigDecimal Precio_Producto;

	/**
	 * @param iD_Producto
	 * @param nombre_Producto
	 * @param precio_Producto
	 */

	public ProductoVO() {

	}

	public ProductoVO(int ID_Producto, String Nombre_Producto, BigDecimal Precio_Producto) {
		super();
		this.ID_Producto = ID_Producto;
		this.Nombre_Producto = Nombre_Producto;
		this.Precio_Producto = Precio_Producto;
	}

	/**
	 * @return the iD_Producto
	 */
	public int getID_Producto() {
		return ID_Producto;
	}

	/**
	 * @return the nombre_Producto
	 */
	public String getNombre_Producto() {
		return Nombre_Producto;
	}

	/**
	 * @return the precio_Producto
	 */
	public BigDecimal getPrecio_Producto() {
		return Precio_Producto;
	}

	/**
	 * @param iD_Producto
	 *            the iD_Producto to set
	 */
	public void setID_Producto(int ID_Producto) {
		this.ID_Producto = ID_Producto;
	}

	/**
	 * @param nombre_Producto
	 *            the nombre_Producto to set
	 */
	public void setNombre_Producto(String Nombre_Producto) {
		this.Nombre_Producto = Nombre_Producto;
	}

	/**
	 * @param precio_Producto
	 *            the precio_Producto to set
	 */
	public void setPrecio_Producto(BigDecimal Precio_Producto) {
		this.Precio_Producto = Precio_Producto;
	}

	/*
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductoVO [ID=" + ID_Producto + ", Nombre=" + Nombre_Producto + ", Precio=" + Precio_Producto + "]";
	}

}