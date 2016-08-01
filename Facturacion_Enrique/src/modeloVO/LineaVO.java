package modeloVO;

import java.math.BigDecimal;

/**
 * CLase VO con los atributos del campo empleado
 * 
 * @author Kiezmi
 *
 */

public class LineaVO {

	private int ID_Linea;
	private int Cantidad_Linea;
	private BigDecimal Precio_Linea;
	FacturaVO factura;
	ProductoVO producto;

	/**
	 * @param iD_Linea
	 * @param cantidad_Linea
	 * @param precio_Linea
	 * @param factura
	 * @param producto
	 */
	public LineaVO() {

	}

	public LineaVO(int ID_Linea, int Cantidad_Linea, BigDecimal Precio_Linea, FacturaVO factura, ProductoVO producto) {
		super();
		this.ID_Linea = ID_Linea;
		this.Cantidad_Linea = Cantidad_Linea;
		this.Precio_Linea = Precio_Linea;
		this.factura = factura;
		this.producto = producto;
	}

	/**
	 * @return the iD_Linea
	 */
	public int getID_Linea() {
		return ID_Linea;
	}

	/**
	 * @return the cantidad_Linea
	 */
	public int getCantidad_Linea() {
		return Cantidad_Linea;
	}

	/**
	 * @return the precio_Linea
	 */
	public BigDecimal getPrecio_Linea() {
		return Precio_Linea;
	}

	/**
	 * @return the factura
	 */
	public FacturaVO getFactura() {
		return factura;
	}

	/**
	 * @return the producto
	 */
	public ProductoVO getProducto() {
		return producto;
	}

	/**
	 * @param iD_Linea
	 *            the iD_Linea to set
	 */
	public void setID_Linea(int ID_Linea) {
		this.ID_Linea = ID_Linea;
	}

	/**
	 * @param cantidad_Linea
	 *            the cantidad_Linea to set
	 */
	public void setCantidad_Linea(int cantidad_Linea) {
		this.Cantidad_Linea = cantidad_Linea;
	}

	/**
	 * @param precio_Linea
	 *            the precio_Linea to set
	 */
	public void setPrecio_Linea(BigDecimal precio_Linea) {
		this.Precio_Linea = precio_Linea;
	}

	/**
	 * @param factura
	 *            the factura to set
	 */
	public void setFactura(FacturaVO factura) {
		this.factura = factura;
	}

	/**
	 * @param producto
	 *            the producto to set
	 */
	public void setProducto(ProductoVO producto) {
		this.producto = producto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LineaVO [ID=" + ID_Linea + ", Cantidad=" + Cantidad_Linea + ", Precio=" + Precio_Linea + ", factura="
				+ factura + ", producto=" + producto + "]";
	}

}
