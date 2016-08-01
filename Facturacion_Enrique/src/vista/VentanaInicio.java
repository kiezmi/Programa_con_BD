package vista;

import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.DbConnection;
import modeloDao.ClienteDao;
import modeloDao.FacturaDao;
import modeloDao.LineaDao;
import modeloDao.ProductoDao;
import modeloVO.ClienteVO;
import modeloVO.FacturaVO;
import modeloVO.ProductoVO;
import javax.swing.SwingConstants;

public class VentanaInicio extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable tableClientes, tableProductos, tableFacturas;

	private JTextField NIF_Cliente, Nombre_Cliente, Direccion_Cliente;
	private JTextField ID_Producto, Nombre_Producto, Precio_Producto;
	private JTextField Nombre_Cliente_F, Direccion_Cliente_F, Numero_Factura, Fecha_Factura, CantidadF, PrecioF;

	private JLabel lblNIFCliente, lblNombreCliente, lblDireccionCliente;
	private JLabel lblID_Producto, lblNombre_Producto, lblPrecio_Producto;
	private JLabel lblNIF_Cliente, lblNombre_Cliente, lblDireccion_Cliente, lblNum_Factura, lblFecha_Factura,
			lblCantidad, lblPrecio, lblProducto;

	private Choice ChoProducto, ChoCliente;

	private JButton BotonMas, botonCrear;
	private JButton btnCliente, btnProducto, btnFacturas;
	private JButton BotonAñadir, BotonModificar, BotonBorrar;

	JInternalFrame frameInternoClientes, frameInternoProductos, frameInternoFacturas;
	JScrollPane scrollPaneClientes, scrollPaneProductos, scrollPaneFacturas;
	JMenuItem mntmCliente, mntmProducto, mntmFacturas, mntmSalir;
	DefaultTableModel modeloC, modeloP, modeloF;

	ClienteDao miClienteDao, miClienteDaoF;
	ProductoDao miProductoDao, miProductoDaoF;
	FacturaDao miFacturaDao;
	LineaDao miLineaDao;

	ClienteVO ClienteF;
	ProductoVO ProductoF;

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	private boolean cliente = false;
	private boolean producto = false;
	private boolean facturas = false;
	private int rowC, rowP, rowF;

	private String documentoC, nombreC, direccionC;

	private String ID, nombreP, precio;

	private String NumFactura, FechaFactura, PrecioFactura;

	ArrayList<ProductoVO> listaProductoVO;
	ArrayList<ProductoVO> vProductosLineas = new ArrayList<ProductoVO>();
	ArrayList<ClienteVO> listaClienteVO;
	ArrayList<ClienteVO> vClientesLineas = new ArrayList<ClienteVO>();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaInicio() throws Exception {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(VentanaInicio.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 923, 500);
		setLocation(200, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1400, 21);
		contentPane.add(menuBar);

		JMenu mnNewMenu1 = new JMenu("Menu");
		mnNewMenu1.setActionCommand("Menu");
		menuBar.add(mnNewMenu1);

		mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu1.add(mntmSalir);

		JMenu mnNewMenu = new JMenu("Altas / Bajas / Modificaciones");
		mnNewMenu.setActionCommand("ABM");
		menuBar.add(mnNewMenu);

		mntmCliente = new JMenuItem("Cliente");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaClientes();

			}
		});
		mnNewMenu.add(mntmCliente);

		mntmProducto = new JMenuItem("Productos");
		mntmProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProductos();

			}
		});
		mnNewMenu.add(mntmProducto);

		mntmFacturas = new JMenuItem("Facturas");
		mntmFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFacturas();
				FacturaDao miFacturaDao = new FacturaDao();
				Numero_Factura.setText(miFacturaDao.obtenerNum_Factura() + "");
			}
		});
		mnNewMenu.add(mntmFacturas);

		btnCliente = new JButton("Cliente");
		btnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VentanaClientes();
			}
		});
		btnCliente.setBounds(96, 46, 89, 23);
		contentPane.add(btnCliente);

		btnProducto = new JButton("Productos");
		btnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaProductos();
			}
		});
		btnProducto.setBounds(372, 46, 109, 23);
		contentPane.add(btnProducto);

		btnFacturas = new JButton("Facturas");
		btnFacturas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaFacturas();
				FacturaDao miFacturaDao = new FacturaDao();
				Numero_Factura.setText(miFacturaDao.obtenerNum_Factura() + "");
			}
		});
		btnFacturas.setBounds(691, 46, 89, 23);
		contentPane.add(btnFacturas);

		// Frame interno de clientes

		frameInternoClientes = new JInternalFrame("Cliente");
		frameInternoClientes.setBounds(200, 80, 460, 375);
		contentPane.add(frameInternoClientes);
		frameInternoClientes.setClosed(true);
		frameInternoClientes.setFrameIcon(
				new ImageIcon(VentanaInicio.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		frameInternoClientes.getContentPane().setLayout(null);

		scrollPaneClientes = new JScrollPane();
		scrollPaneClientes.setBounds(0, 0, 448, 175);
		frameInternoClientes.getContentPane().add(scrollPaneClientes);

		tableClientes = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}; // return false: Desabilitar edición de celdas.
		};
		scrollPaneClientes.setViewportView(tableClientes);
		tableClientes.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() >= 1) {
					rowC = tableClientes.getSelectedRow();

					DefaultTableModel modelotabla = (DefaultTableModel) tableClientes.getModel();
					if (tableClientes.getSelectedRow() != -1) {
						documentoC = modelotabla.getValueAt(rowC, 0).toString();
						nombreC = modelotabla.getValueAt(rowC, 1).toString();
						direccionC = modelotabla.getValueAt(rowC, 2).toString();
					}
					NIF_Cliente.setText(documentoC + "");
					Nombre_Cliente.setText(nombreC + "");
					Direccion_Cliente.setText(direccionC + "");
				}
			}
		});

		// Creacion de JButton Cliente

		BotonAñadir = new JButton("A\u00F1adir");
		BotonAñadir.setName("Añadir");
		BotonAñadir.addActionListener(this);
		BotonAñadir.setBounds(10, 275, 94, 23);
		frameInternoClientes.getContentPane().add(BotonAñadir);

		BotonModificar = new JButton("Modificar");
		BotonModificar.setName("Modificar");
		BotonModificar.setEnabled(true);
		BotonModificar.addActionListener(this);
		BotonModificar.setBounds(183, 275, 89, 23);
		frameInternoClientes.getContentPane().add(BotonModificar);

		BotonBorrar = new JButton("Borrar");
		BotonBorrar.setName("Borrar");
		BotonBorrar.addActionListener(this);
		BotonBorrar.setBounds(338, 275, 89, 23);
		frameInternoClientes.getContentPane().add(BotonBorrar);

		// Creacion de JLabel Cliente

		lblNIFCliente = new JLabel("NIF_Cliente :");
		lblNIFCliente.setBounds(70, 180, 95, 14);
		frameInternoClientes.getContentPane().add(lblNIFCliente);

		lblNombreCliente = new JLabel("Nombre_Cliente :");
		lblNombreCliente.setBounds(70, 205, 106, 14);
		frameInternoClientes.getContentPane().add(lblNombreCliente);

		lblDireccionCliente = new JLabel("Direccion_Cliente :");
		lblDireccionCliente.setBounds(70, 230, 106, 14);
		frameInternoClientes.getContentPane().add(lblDireccionCliente);

		// Creacion de JTextField Cliente

		NIF_Cliente = new JTextField();
		NIF_Cliente.setHorizontalAlignment(SwingConstants.CENTER);
		NIF_Cliente.setBounds(186, 180, 86, 20);
		frameInternoClientes.getContentPane().add(NIF_Cliente);
		NIF_Cliente.setColumns(10);

		Nombre_Cliente = new JTextField();
		Nombre_Cliente.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre_Cliente.setColumns(10);
		Nombre_Cliente.setBounds(186, 205, 86, 20);
		frameInternoClientes.getContentPane().add(Nombre_Cliente);

		Direccion_Cliente = new JTextField();
		Direccion_Cliente.setHorizontalAlignment(SwingConstants.CENTER);
		Direccion_Cliente.setColumns(10);
		Direccion_Cliente.setBounds(186, 230, 86, 20);
		frameInternoClientes.getContentPane().add(Direccion_Cliente);

		// Frame interno de Producto

		frameInternoProductos = new JInternalFrame("Producto");
		frameInternoProductos.setBounds(200, 80, 460, 375);
		contentPane.add(frameInternoProductos);
		frameInternoProductos.setFrameIcon(
				new ImageIcon(VentanaInicio.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		frameInternoProductos.setClosed(true);
		frameInternoProductos.getContentPane().setLayout(null);

		scrollPaneProductos = new JScrollPane();
		scrollPaneProductos.setBounds(0, 0, 448, 175);
		frameInternoProductos.getContentPane().add(scrollPaneProductos);

		tableProductos = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}; // return false: Desabilitar edición de celdas.
		};
		scrollPaneProductos.setViewportView(tableProductos);
		tableProductos.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() >= 1) {
					rowP = tableProductos.getSelectedRow();
					DefaultTableModel modelotabla = (DefaultTableModel) tableProductos.getModel();
					if (tableProductos.getSelectedRow() != -1) {
						ID = modelotabla.getValueAt(rowP, 0).toString();
						nombreP = modelotabla.getValueAt(rowP, 1).toString();
						precio = modelotabla.getValueAt(rowP, 2).toString();
					}
					ID_Producto.setText(ID + "");
					Nombre_Producto.setText(nombreP + "");
					Precio_Producto.setText(precio + "");
				}
			}
		});

		// Creacion de JButton Producto

		BotonAñadir = new JButton("A\u00F1adir");
		BotonAñadir.setName("Añadir");
		BotonAñadir.addActionListener(this);
		BotonAñadir.setBounds(10, 275, 94, 23);
		frameInternoProductos.getContentPane().add(BotonAñadir);

		BotonModificar = new JButton("Modificar");
		BotonModificar.setName("Modificar");
		BotonModificar.addActionListener(this);
		BotonModificar.setBounds(183, 275, 89, 23);
		frameInternoProductos.getContentPane().add(BotonModificar);

		BotonBorrar = new JButton("Borrar");
		BotonBorrar.setName("Borrar");
		BotonBorrar.addActionListener(this);
		BotonBorrar.setBounds(338, 275, 89, 23);
		frameInternoProductos.getContentPane().add(BotonBorrar);

		// Creacion de JLabel Producto

		lblID_Producto = new JLabel("ID_Producto :");
		lblID_Producto.setBounds(70, 180, 95, 14);
		frameInternoProductos.getContentPane().add(lblID_Producto);

		lblNombre_Producto = new JLabel("Nombre_Producto :");
		lblNombre_Producto.setBounds(70, 205, 106, 14);
		frameInternoProductos.getContentPane().add(lblNombre_Producto);

		lblPrecio_Producto = new JLabel("Precio_Producto :");
		lblPrecio_Producto.setBounds(70, 230, 106, 14);
		frameInternoProductos.getContentPane().add(lblPrecio_Producto);

		// Creacion de JTextField Producto

		ID_Producto = new JTextField();
		ID_Producto.setHorizontalAlignment(SwingConstants.CENTER);
		ID_Producto.setBounds(186, 180, 86, 20);
		frameInternoProductos.getContentPane().add(ID_Producto);
		ID_Producto.setColumns(10);

		Nombre_Producto = new JTextField();
		Nombre_Producto.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre_Producto.setColumns(10);
		Nombre_Producto.setBounds(186, 205, 86, 20);
		frameInternoProductos.getContentPane().add(Nombre_Producto);

		Precio_Producto = new JTextField();
		Precio_Producto.setHorizontalAlignment(SwingConstants.CENTER);
		Precio_Producto.setColumns(10);
		Precio_Producto.setBounds(186, 230, 86, 20);
		frameInternoProductos.getContentPane().add(Precio_Producto);

		// Frame interno de Facturas
		frameInternoFacturas = new JInternalFrame("Facturas");
		frameInternoFacturas.setFrameIcon(
				new ImageIcon(VentanaInicio.class.getResource("/javax/swing/plaf/metal/icons/Inform.gif")));
		frameInternoFacturas.setClosed(true);
		frameInternoFacturas.setBounds(15, 80, 880, 382);
		contentPane.add(frameInternoFacturas);
		frameInternoFacturas.getContentPane().setLayout(null);

		scrollPaneFacturas = new JScrollPane();
		scrollPaneFacturas.setBounds(0, 146, 864, 207);
		frameInternoFacturas.getContentPane().add(scrollPaneFacturas);

		tableFacturas = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}; // return false: Desabilitar edición de celdas.
		};
		scrollPaneFacturas.setViewportView(tableFacturas);
		tableFacturas.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent e) {
				if (e.getClickCount() >= 1) {
					rowF = tableFacturas.getSelectedRow();
					DefaultTableModel modelotabla = (DefaultTableModel) tableFacturas.getModel();
					if (tableFacturas.getSelectedRow() != -1) {
						NumFactura = modelotabla.getValueAt(rowF, 0).toString();
						FechaFactura = modelotabla.getValueAt(rowF, 1).toString();
						PrecioFactura = modelotabla.getValueAt(rowF, 2).toString();

					}
					Numero_Factura.setText(NumFactura + "");
					Fecha_Factura.setText(FechaFactura + "");
					PrecioF.setText(PrecioFactura + "");
				}
			}
		});

		// Creacion JBotones de factura

		BotonMas = new JButton("Agregar Linea");
		BotonMas.setName("BotonMas");
		BotonMas.addActionListener(this);
		BotonMas.setBounds(738, 107, 116, 25);
		frameInternoFacturas.getContentPane().add(BotonMas);

		botonCrear = new JButton("Crear Factura");
		botonCrear.setName("Crear Factura");
		botonCrear.addActionListener(this);
		botonCrear.setBounds(322, 24, 136, 25);
		frameInternoFacturas.getContentPane().add(botonCrear);

		// Creacion de JLavel Facturas

		lblNIF_Cliente = new JLabel("NIF_Cliente :");
		lblNIF_Cliente.setBounds(567, 26, 95, 14);
		frameInternoFacturas.getContentPane().add(lblNIF_Cliente);

		lblNombre_Cliente = new JLabel("Nombre_Cliente :");
		lblNombre_Cliente.setBounds(567, 51, 106, 14);
		frameInternoFacturas.getContentPane().add(lblNombre_Cliente);

		lblDireccion_Cliente = new JLabel("Direccion_Cliente :");
		lblDireccion_Cliente.setBounds(567, 74, 106, 14);
		frameInternoFacturas.getContentPane().add(lblDireccion_Cliente);

		lblNum_Factura = new JLabel("Num_Factura :");
		lblNum_Factura.setBounds(50, 26, 95, 14);
		frameInternoFacturas.getContentPane().add(lblNum_Factura);

		lblFecha_Factura = new JLabel("Fecha_Factura :");
		lblFecha_Factura.setBounds(50, 51, 106, 14);
		frameInternoFacturas.getContentPane().add(lblFecha_Factura);

		lblCantidad = new JLabel("Cantidad :");
		lblCantidad.setBounds(10, 110, 64, 19);
		frameInternoFacturas.getContentPane().add(lblCantidad);

		lblProducto = new JLabel("Producto :");
		lblProducto.setBounds(192, 108, 75, 23);
		frameInternoFacturas.getContentPane().add(lblProducto);

		lblPrecio = new JLabel("Precio :");
		lblPrecio.setBounds(579, 110, 53, 19);
		frameInternoFacturas.getContentPane().add(lblPrecio);

		// Creacion de JTextField Facturas

		Nombre_Cliente_F = new JTextField();
		Nombre_Cliente_F.setEditable(false);
		Nombre_Cliente_F.setHorizontalAlignment(SwingConstants.CENTER);
		Nombre_Cliente_F.setBounds(683, 45, 100, 20);
		frameInternoFacturas.getContentPane().add(Nombre_Cliente_F);
		Nombre_Cliente_F.setColumns(10);

		Direccion_Cliente_F = new JTextField();
		Direccion_Cliente_F.setEditable(false);
		Direccion_Cliente_F.setHorizontalAlignment(SwingConstants.CENTER);
		Direccion_Cliente_F.setColumns(10);
		Direccion_Cliente_F.setBounds(683, 70, 100, 20);
		frameInternoFacturas.getContentPane().add(Direccion_Cliente_F);

		Numero_Factura = new JTextField();
		Numero_Factura.setEditable(false);
		Numero_Factura.setHorizontalAlignment(SwingConstants.CENTER);
		Numero_Factura.setColumns(10);
		Numero_Factura.setBounds(155, 23, 100, 20);
		frameInternoFacturas.getContentPane().add(Numero_Factura);

		Fecha_Factura = new JTextField();
		Fecha_Factura.setHorizontalAlignment(SwingConstants.CENTER);
		Fecha_Factura.setColumns(10);
		Fecha_Factura.setBounds(155, 48, 100, 20);
		frameInternoFacturas.getContentPane().add(Fecha_Factura);

		CantidadF = new JTextField();
		CantidadF.setText("1");
		CantidadF.setHorizontalAlignment(SwingConstants.CENTER);
		CantidadF.setColumns(10);
		CantidadF.setBounds(84, 109, 86, 20);
		frameInternoFacturas.getContentPane().add(CantidadF);

		PrecioF = new JTextField();
		PrecioF.setHorizontalAlignment(SwingConstants.CENTER);
		PrecioF.setColumns(10);
		PrecioF.setBounds(642, 109, 86, 20);
		frameInternoFacturas.getContentPane().add(PrecioF);

	}

	public void actionPerformed(ActionEvent click) {
		JButton objeto = (JButton) click.getSource();

		if (frameInternoClientes.isVisible()) {

			if (objeto.getName().equals("Añadir")) {
				registrarCliente();
				limpiar();
				System.out.println("Cliente Añadido");
			}
			if (objeto.getName().equals("Modificar")) {
				modificarCliente();
				limpiar();
				System.out.println("Cliente Modificado");
			}
			if (objeto.getName().equals("Borrar")) {
				borrarCliente();
				limpiar();
				System.out.println("Cliente borrardo");
			}
		}

		if (frameInternoProductos.isVisible()) {

			if (objeto.getName().equals("Añadir")) {
				registrarProducto();
				limpiar();
				System.out.println("Producto Añadido");
			}
			if (objeto.getName().equals("Modificar")) {
				modificarProducto();
				limpiar();
				System.out.println("Producto Modificado");
			}
			if (objeto.getName().equals("Borrar")) {
				borrarProducto();
				limpiar();
				System.out.println("Producto borrado");
			}
		}

		if (frameInternoFacturas.isVisible()) {

			if (objeto.getName().equals("BotonMas")) {
				System.out.println("Linea Creada");
				registrarLinea();

			}

			if (objeto.getName().equals("Crear Factura")) {
				System.out.println("Facturar");
				registrarFactura();
				FacturaDao miFacturaDao = new FacturaDao();
				Numero_Factura.setText(miFacturaDao.obtenerNum_Factura() + "");
				limpiar();
			}
		}
	}

	// Mpstrar ventana cliente con tabla

	private void VentanaClientes() {
		if (cliente == false) {
			facturas = false;
			frameInternoFacturas.setVisible(false);
			producto = false;
			frameInternoProductos.setVisible(false);
			frameInternoClientes.setVisible(true);
			cliente = true;

			modeloC = new DefaultTableModel();
			tableClientes.setModel(modeloC);

			modeloC.addColumn("NIF");
			modeloC.addColumn("Nombre");
			modeloC.addColumn("Direccion");

			ClienteDao c = new ClienteDao();
			ArrayList<ClienteVO> v = c.listaDeCliente();

			for (int i = 0; i < v.size(); i++) {
				// es para obtener los datos y almacenar las filas
				Object[] fila = new Object[3];
				// para llenar cada columna con lo datos almacenados

				ClienteVO cVO = v.get(i);

				fila[0] = cVO.getNIF_Cliente();
				fila[1] = cVO.getNombre_Cliente();
				fila[2] = cVO.getDireccion_Cliente();
				// es para cargar los datos en filas a la tabla modelo
				modeloC.addRow(fila);
			}

		} else {
			frameInternoClientes.setVisible(false);
			cliente = false;
		}
	}

	// Muestra ventana Producto con tabla

	private void VentanaProductos() {
		if (producto == false) {
			facturas = false;
			frameInternoFacturas.setVisible(false);
			cliente = false;
			frameInternoClientes.setVisible(false);
			frameInternoProductos.setVisible(true);
			producto = true;

			modeloP = new DefaultTableModel();
			tableProductos.setModel(modeloP);

			modeloP.addColumn("ID");
			modeloP.addColumn("Nombre");
			modeloP.addColumn("Precio");

			ProductoDao c = new ProductoDao();
			ArrayList<ProductoVO> v = c.listaDeProducto();

			for (int i = 0; i < v.size(); i++) {
				// es para obtener los datos y almacenar las filas
				Object[] fila = new Object[3];
				// para llenar cada columna con lo datos almacenados

				ProductoVO pVO = v.get(i);

				fila[0] = pVO.getID_Producto();
				fila[1] = pVO.getNombre_Producto();
				fila[2] = pVO.getPrecio_Producto();
				// es para cargar los datos en filas a la tabla modelo
				modeloP.addRow(fila);

			}

		} else {
			frameInternoProductos.setVisible(false);
			producto = false;
		}
	}

	// Muestra vventana Factura con tabla

	private void VentanaFacturas() {
		if (facturas == false) {
			producto = false;
			frameInternoProductos.setVisible(false);
			cliente = false;
			frameInternoClientes.setVisible(false);
			frameInternoFacturas.setVisible(true);
			facturas = true;

			modeloF = new DefaultTableModel();
			tableFacturas.setModel(modeloF);

			modeloF.addColumn("Cantidad");
			modeloF.addColumn("Producto");
			modeloF.addColumn("Precio");

			// fecha actual para el campo de texto de la factura
			Calendar fecha = Calendar.getInstance();
			Fecha_Factura.setText(
					Integer.toString(fecha.get(Calendar.DATE)) + "/" + Integer.toString(fecha.get(Calendar.MONTH) + 1)
							+ "/" + Integer.toString(fecha.get(Calendar.YEAR)));

			if (ChoCliente == null) {
				ChoCliente = new Choice();

			}

			ChoCliente.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent arg0) {
					System.out.println(ChoCliente.getSelectedIndex());

					int indice = ChoCliente.getSelectedIndex() - 1;

					if (indice >= 0) {
						ClienteF = listaClienteVO.get(ChoCliente.getSelectedIndex() - 1);
						Nombre_Cliente_F.setText(ClienteF.getNombre_Cliente() + "");
						Direccion_Cliente_F.setText(ClienteF.getDireccion_Cliente() + "");
					}
				}
			});

			ChoCliente.setBounds(682, 20, 100, 20);
			frameInternoFacturas.getContentPane().add(ChoCliente);

			if (ChoProducto == null) {
				ChoProducto = new Choice();

			}
			ChoProducto.add("Selecciona");
			ChoProducto.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					System.out.println(ChoProducto.getSelectedIndex());

					int indice = ChoProducto.getSelectedIndex() - 1;

					if (indice >= 0) {
						ProductoF = listaProductoVO.get(ChoProducto.getSelectedIndex() - 1);
						Nombre_Producto.setText(ProductoF.getNombre_Producto() + "");
						PrecioF.setText(ProductoF.getPrecio_Producto() + "");
					}
				}
			});

			ChoProducto.setBounds(273, 107, 286, 20);
			frameInternoFacturas.getContentPane().add(ChoProducto);

			miClienteDaoF = new ClienteDao();
			listaClienteVO = miClienteDaoF.listaDeCliente();

			miProductoDaoF = new ProductoDao();
			listaProductoVO = miProductoDaoF.listaDeProducto();

			ChoCliente.removeAll();
			ChoCliente.add("Selecciona");
			ChoProducto.removeAll();
			ChoProducto.add("Selecciona");

			for (ClienteVO c : listaClienteVO) {
				ChoCliente.add(c.getNIF_Cliente());
			}

			for (int i = 0; i < listaProductoVO.size(); i++) {
				ProductoF = listaProductoVO.get(i);
				ChoProducto.add(ProductoF.getNombre_Producto());
			}

		} else {
			frameInternoFacturas.setVisible(false);
			facturas = false;
		}
	}

	// limpieza de los campos

	private void limpiar() {
		NIF_Cliente.setText("");
		Nombre_Cliente.setText("");
		Direccion_Cliente.setText("");
		ID_Producto.setText("");
		Nombre_Producto.setText("");
		Precio_Producto.setText("");

	}

	// Cliente

	private void registrarCliente() {
		miClienteDao = new ClienteDao();
		ClienteVO miClienteVO = new ClienteVO();

		miClienteVO.setNIF_Cliente(NIF_Cliente.getText());
		miClienteVO.setNombre_Cliente(Nombre_Cliente.getText());
		miClienteVO.setDireccion_Cliente(Direccion_Cliente.getText());

		miClienteDao.registrarCliente(miClienteVO);

		ArrayList<ClienteVO> v = miClienteDao.listaDeCliente();

		// es para obtener los datos y almacenar las filas
		Object[] fila = new Object[3];
		// para llenar cada columna con lo datos almacenados

		fila[0] = miClienteVO.getNIF_Cliente();
		fila[1] = miClienteVO.getNombre_Cliente();
		fila[2] = miClienteVO.getDireccion_Cliente();
		// es para cargar los datos en filas a la tabla modelo
		modeloC.addRow(fila);

	}

	private void modificarCliente() {
		miClienteDao = new ClienteDao();
		ClienteVO miClienteVO = new ClienteVO();

		miClienteVO.setNIF_Cliente(NIF_Cliente.getText());
		miClienteVO.setNombre_Cliente(Nombre_Cliente.getText());
		miClienteVO.setDireccion_Cliente(Direccion_Cliente.getText());

		miClienteDao.modificarCliente(miClienteVO);

		ArrayList<ClienteVO> v = miClienteDao.listaDeCliente();

		modeloC.setValueAt(miClienteVO.getNIF_Cliente(), tableClientes.getSelectedRow(), 0);
		modeloC.setValueAt(miClienteVO.getNombre_Cliente(), tableClientes.getSelectedRow(), 1);
		modeloC.setValueAt(miClienteVO.getDireccion_Cliente(), tableClientes.getSelectedRow(), 2);

	}

	private void borrarCliente() {

		miClienteDao = new ClienteDao();
		ClienteVO miClienteVO = new ClienteVO();

		miClienteVO.setNIF_Cliente(NIF_Cliente.getText());
		miClienteVO.setNombre_Cliente(Nombre_Cliente.getText());
		miClienteVO.setDireccion_Cliente(Direccion_Cliente.getText());

		miClienteDao.borrarCliente(miClienteVO);

		ArrayList<ClienteVO> v = miClienteDao.listaDeCliente();

		modeloC.removeRow(tableClientes.getSelectedRow());

	}

	// Producto

	private void registrarProducto() {

		miProductoDao = new ProductoDao();
		ProductoVO miProductoVO = new ProductoVO();

		miProductoVO.setID_Producto(new Integer(ID_Producto.getText()));
		miProductoVO.setNombre_Producto(Nombre_Producto.getText());
		miProductoVO.setPrecio_Producto(new BigDecimal(Precio_Producto.getText()));

		miProductoDao.registrarProducto(miProductoVO);

		ArrayList<ProductoVO> v = miProductoDao.listaDeProducto();

		// es para obtener los datos y almacenar las filas
		Object[] fila = new Object[3];
		// para llenar cada columna con lo datos almacenados

		fila[0] = miProductoVO.getID_Producto();
		fila[1] = miProductoVO.getNombre_Producto();
		fila[2] = miProductoVO.getPrecio_Producto();
		// es para cargar los datos en filas a la tabla modelo
		modeloP.addRow(fila);
	}

	private void modificarProducto() {
		miProductoDao = new ProductoDao();
		ProductoVO miProductoVO = new ProductoVO();

		miProductoVO.setID_Producto(new Integer(ID_Producto.getText()));
		miProductoVO.setNombre_Producto(Nombre_Producto.getText());
		miProductoVO.setPrecio_Producto(new BigDecimal(Precio_Producto.getText()));

		miProductoDao.modificarProducto(miProductoVO);

		ArrayList<ProductoVO> v = miProductoDao.listaDeProducto();

		modeloP.setValueAt(miProductoVO.getID_Producto(), tableProductos.getSelectedRow(), 0);
		modeloP.setValueAt(miProductoVO.getNombre_Producto(), tableProductos.getSelectedRow(), 1);
		modeloP.setValueAt(miProductoVO.getPrecio_Producto(), tableProductos.getSelectedRow(), 2);

	}

	private void borrarProducto() {
		miProductoDao = new ProductoDao();
		ProductoVO miProductoVO = new ProductoVO();

		miProductoVO.setID_Producto(new Integer(ID_Producto.getText()));
		miProductoVO.setNombre_Producto(Nombre_Producto.getText());
		miProductoVO.setPrecio_Producto(new BigDecimal(Precio_Producto.getText()));

		miProductoDao.borrarProducto(miProductoVO);

		ArrayList<ProductoVO> v = miProductoDao.listaDeProducto();

		modeloP.removeRow(tableProductos.getSelectedRow());
	}

	// Factura

	public void registrarLinea() {

		if (ChoProducto.getSelectedIndex() > 0) {
			vProductosLineas.add(listaProductoVO.get(ChoProducto.getSelectedIndex() - 1));

			System.out.println(vProductosLineas);

			// es para obtener los datos y almacenar las filas
			Object[] fila = new Object[3];

			// para llenar cada columna con lo datos almacenados

			fila[0] = CantidadF.getText();
			fila[1] = ChoProducto.getSelectedItem();
			fila[2] = PrecioF.getText();
			// es para cargar los datos en filas a la tabla modelo
			modeloF.addRow(fila);
		}

	}

	private void registrarFactura() {

		miFacturaDao = new FacturaDao();
		FacturaVO miFacturaVO = new FacturaVO();

		try {
			SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date utilStartDate = d.parse(Fecha_Factura.getText());
			java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
			miFacturaVO.setFecha_Factura(sqlStartDate);
		} catch (ParseException e) {

			e.printStackTrace();
		}

		if (ChoCliente.getSelectedIndex() > 0) {
			ClienteVO cVO = listaClienteVO.get(ChoCliente.getSelectedIndex() - 1);
			miFacturaVO.setCliente(cVO);

			miFacturaDao.registrarFactura(miFacturaVO);
		}

		for (int i = 0; i < tableFacturas.getRowCount(); i++) {
			modeloF.removeRow(i);
			i += -1;
		}
	}
}
