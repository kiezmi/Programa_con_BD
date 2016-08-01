package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DbConnection;
import modeloVO.ProductoVO;

import com.mysql.jdbc.DatabaseMetaData;

public class ProductoDao {

	public void registrarProducto(ProductoVO producto) {

		DbConnection conex = new DbConnection();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO producto VALUES ('" + producto.getID_Producto() + "', '"
					+ producto.getNombre_Producto() + "', '" + producto.getPrecio_Producto() + "')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro el producto");
		}
	}

	public void modificarProducto(ProductoVO producto) {
		DbConnection conex = new DbConnection();
		try {
			
			PreparedStatement pstmt = conex.getConnection().prepareStatement(
					"UPDATE producto SET  Nombre_Producto = ?,  Precio_Producto = ? WHERE ID_Producto = ?");
			
			pstmt.setString(1, producto.getNombre_Producto());
			pstmt.setBigDecimal(2, producto.getPrecio_Producto());
			pstmt.setInt(3, producto.getID_Producto());
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha modificado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			pstmt.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se modificado el producto");
		}

	}

	public void borrarProducto(ProductoVO producto) {

		DbConnection conex = new DbConnection();
		try {
			PreparedStatement estatuto = conex.getConnection().prepareStatement(
			 " DELETE FROM producto WHERE ID_Producto = ?");

			estatuto.setInt(1, producto.getID_Producto());
			estatuto.executeUpdate();
		
			JOptionPane.showMessageDialog(null, "Se ha Borrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Borrado el producto");
		}
	}

	public ProductoVO consultarProducto(String documento) {
		//ArrayList<ProductoVO> miProducto = new ArrayList<ProductoVO>();
		DbConnection conex = new DbConnection();
		ProductoVO producto = new ProductoVO();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto where ID_Producto = ? ");
			consulta.setString(1, documento);
			ResultSet res = consulta.executeQuery();

			if (res.next()) {
				//ProductoVO producto = new ProductoVO();
				producto.setID_Producto((res.getInt("ID_Producto")));
				producto.setNombre_Producto(res.getString("Nombre_Producto"));
				producto.setPrecio_Producto((res.getBigDecimal("Precio_Producto")));
				//miProducto.add(producto);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el producto\n" + e);
		}
		return producto;
	}

	public ArrayList<ProductoVO> listaDeProducto() {
		ArrayList<ProductoVO> miProducto = new ArrayList<ProductoVO>();
		DbConnection conex = new DbConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM producto");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				ProductoVO producto = new ProductoVO();
				producto.setID_Producto((res.getInt("ID_Producto")));
				producto.setNombre_Producto(res.getString("Nombre_Producto"));
				producto.setPrecio_Producto((res.getBigDecimal("Precio_Producto")));
				miProducto.add(producto);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el producto\n" + e);
		}
		return miProducto;
	}
	
}
