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
import com.mysql.jdbc.DatabaseMetaData;

public class ClienteDao {

	public void registrarCliente(ClienteVO cliente) {

		DbConnection conex = new DbConnection();
		try {
			Statement estatuto = conex.getConnection().createStatement();
			estatuto.executeUpdate("INSERT INTO cliente VALUES ('" + cliente.getNIF_Cliente() + "', '"
					+ cliente.getNombre_Cliente() + "', '" + cliente.getDireccion_Cliente() + "')");
			JOptionPane.showMessageDialog(null, "Se ha registrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Registro el cliente");
		}
	}

	public void modificarCliente(ClienteVO cliente) {
		DbConnection conex = new DbConnection();
		try {

			PreparedStatement pstmt = conex.getConnection().prepareStatement(
					"UPDATE cliente SET  Nombre_Cliente = ?,  Direccion_Cliente = ? WHERE NIF_Cliente = ?");
			
			pstmt.setString(1, cliente.getNombre_Cliente());
			pstmt.setString(2, cliente.getDireccion_Cliente());
			pstmt.setString(3, cliente.getNIF_Cliente());
			pstmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha modificado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			pstmt.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se modificado el cliente");
		}

	}

	public void borrarCliente(ClienteVO cliente) {

		DbConnection conex = new DbConnection();
		try {
			PreparedStatement estatuto = conex.getConnection().prepareStatement(
					 " DELETE FROM cliente WHERE NIF_Cliente = ?");
			
			estatuto.setString(1, cliente.getNIF_Cliente());
			estatuto.executeUpdate();

			JOptionPane.showMessageDialog(null, "Se ha Borrado Exitosamente", "Información",
					JOptionPane.INFORMATION_MESSAGE);
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "No se Borrado el cliente");
		}
	}

	
	public ClienteVO consultarCliente(String documento) {
		//ArrayList<ClienteVO> miCliente = new ArrayList<ClienteVO>();
		DbConnection conex = new DbConnection();
		ClienteVO persona = new ClienteVO();
		
		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM cliente where NIF_Cliente = ? ");
			consulta.setString(1, documento);
			ResultSet res = consulta.executeQuery();

			if (res.next()) {
				//ClienteVO persona = new ClienteVO();
				persona.setNIF_Cliente((res.getString("NIF_Cliente")));
				persona.setNombre_Cliente(res.getString("Nombre_Cliente"));
				persona.setDireccion_Cliente((res.getString("Direccion_Cliente")));
				//miCliente.add(persona);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
		}
		return persona;
	}

	public ArrayList<ClienteVO> listaDeCliente() {
		ArrayList<ClienteVO> miCliente = new ArrayList<ClienteVO>();
		DbConnection conex = new DbConnection();

		try {
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM cliente");
			ResultSet res = consulta.executeQuery();
			while (res.next()) {
				ClienteVO persona = new ClienteVO();
				persona.setNIF_Cliente((res.getString("NIF_Cliente")));
				persona.setNombre_Cliente(res.getString("Nombre_Cliente"));
				persona.setDireccion_Cliente((res.getString("Direccion_Cliente")));
				miCliente.add(persona);
			}
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
		}
		return miCliente;
	}
}
