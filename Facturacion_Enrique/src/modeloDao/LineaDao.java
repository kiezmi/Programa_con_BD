package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.DbConnection;
import modeloVO.ClienteVO;
import modeloVO.LineaVO;
import modeloVO.ProductoVO;
import modeloVO.FacturaVO;
import com.mysql.jdbc.DatabaseMetaData;

public class LineaDao {

	public void registrarLinea(LineaVO linea) {

		DbConnection conex = new DbConnection();
		try {
			PreparedStatement estatuto = conex.getConnection().prepareStatement("INSERT INTO linea VALUES (?,?,?,?,?)");

			estatuto.setInt(1, linea.getID_Linea());
			estatuto.setInt(2, linea.getCantidad_Linea());
			estatuto.setBigDecimal(3,linea.getPrecio_Linea());
			estatuto.setString(3, linea.getFactura().getNum_Factura());
			estatuto.setInt(4,linea.getProducto().getID_Producto());
			estatuto.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se registro la factura");
		}
	}

	public void borrarLinea(LineaVO linea) {

		DbConnection conex = new DbConnection();
		try {
			PreparedStatement estatuto = conex.getConnection().prepareStatement(
					 " DELETE FROM linea WHERE ID_Linea = ?");

					estatuto.setInt(1, linea.getID_Linea());
					estatuto.executeUpdate();
				
					JOptionPane.showMessageDialog(null, "Se ha Borrado Exitosamente", "Información",
							JOptionPane.INFORMATION_MESSAGE);
					estatuto.close();
					conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Borrado la persona");
		}
	}

	public ArrayList<LineaVO> listaDeLinea(FacturaVO i) {
		ArrayList<LineaVO> miLinea = new ArrayList<LineaVO>();
		DbConnection conex = new DbConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM cliente");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				LineaVO linea = new LineaVO();
		
				linea.setID_Linea((res.getInt("ID_Linea")));
				linea.setCantidad_Linea(res.getInt("Cantidad_Linea"));
				linea.setPrecio_Linea((res.getBigDecimal("Precio_Linea")));
				
				int fac = res.getInt("IDR_Factura");
				FacturaDao fDAO = new FacturaDao();
				FacturaVO fVO = fDAO.consultarFactura(fac);
				linea.setFactura(fVO);
				
				String pro = res.getString("IDR_Producto");
				ProductoDao pDAO = new ProductoDao();
				ProductoVO pVO = pDAO.consultarProducto(pro);
				linea.setProducto(pVO);
				
				miLinea.add(linea);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la linea" + e);
		}
		return miLinea;
	}
}
