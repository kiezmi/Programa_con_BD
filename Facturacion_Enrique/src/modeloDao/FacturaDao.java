package modeloDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelo.DbConnection;
import modeloVO.ClienteVO;
import modeloVO.FacturaVO;

public class FacturaDao {

	public void registrarFactura(FacturaVO factura) {

		DbConnection conex = new DbConnection();
		try {
			PreparedStatement pstmt = conex.getConnection()
					.prepareStatement("INSERT INTO factura ( Fecha_Factura, IDR_Cliente) VALUES (?,?)");

			pstmt.setDate(1, factura.getFecha_Factura());
			pstmt.setString(2, factura.getCliente().getNIF_Cliente());
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			pstmt.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se registro la factura");
		}
	}

	public FacturaVO consultarFactura(int documento) {
		// ArrayList<FacturaVO> miFactura = new ArrayList<FacturaVO>();
		DbConnection conex = new DbConnection();
		FacturaVO factura = new FacturaVO();

		try {
			PreparedStatement consulta = conex.getConnection()
					.prepareStatement("SELECT * FROM factura where Num_Factura = ? ");
			consulta.setInt(1, documento);
			ResultSet res = consulta.executeQuery();

			if (res.next()) {
				// FacturaVO factura = new FacturaVO();
				factura.setNum_Factura((res.getString("Num_Factura")));
				factura.setFecha_Factura(res.getDate("Fecha_Factura"));
				factura.setCliente((ClienteVO) res.getObject(1));
				// miFactura.add(factura);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar el producto\n" + e);
		}
		return factura;
	}

	public int obtenerNum_Factura() {
		DbConnection conex = new DbConnection();

		int num = 0;

		try {
			
			PreparedStatement pstmt = conex.getConnection()
					.prepareStatement("SELECT Num_Factura FROM factura ORDER BY Num_Factura DESC");
			ResultSet res = pstmt.executeQuery();
			res.next();

			num = res.getInt(1);

			res.close();
			pstmt.close();
			conex.desconectar();

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return num+1;

	}

	public ArrayList<FacturaVO> listaDeFactura() {
		ArrayList<FacturaVO> miFactura = new ArrayList<FacturaVO>();
		DbConnection conex = new DbConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM factura");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {

				FacturaVO factura = new FacturaVO();
				factura.setNum_Factura((res.getString("Num_Factura")));
				factura.setFecha_Factura(res.getDate("Fecha_Factura"));

				String cli = res.getString("IDR_Cliente");
				ClienteDao cDAO = new ClienteDao();
				ClienteVO cVO = cDAO.consultarCliente(cli);
				factura.setCliente(cVO);

				miFactura.add(factura);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la factura" + e);
		}
		return miFactura;
	}
}
