package Tienda_jdbc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Tienda_jdbc {

	private JFrame frame;
	private DefaultTableModel modeloCatalogo;
	private DefaultTableModel modeloTienda;
	private DefaultTableModel modelocatalogo_tienda;
	private JTextField tfIdcat;
	private JTextField tfNombrecat;
	private JTextField tfDescripcion;
	private JTextField tfGerente;
	private JTextField tfNombreTienda;
	private JTextField tfIDTIenda;
	private JSpinner spprecio;
	private JSpinner spNumempleados;
	private JTextField tfcatalogo;
	private JTextField tftienda;

	/**
	 * Launch the application.
	 */

	private void limpiar() {
		tfIdcat.setText("");
		tfNombrecat.setText("");
		tfDescripcion.setText("");
		spprecio.setValue(1);
	}

	public void insertarCat(String nombre, String descripcion, int precio) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("insert into catalogo (nombre,descripcion,precio) values (?,?,?)");
			ins_pstmt.setString(1, nombre);
			ins_pstmt.setString(2, descripcion);
			ins_pstmt.setInt(3, precio);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Catalogo añadido");
			mostrarCatalogo(modeloCatalogo);
			limpiar();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void actualizarcatalogo(int id, String nombre, String descripcion, int precio) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("update catalogo set nombre=?,descripcion=? ,precio=? where id=?");
			ins_pstmt.setString(1, nombre);
			ins_pstmt.setString(2, descripcion);
			ins_pstmt.setInt(3, precio);
			ins_pstmt.setInt(4, id);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Catalogo actualizado");
			mostrarCatalogo(modeloCatalogo);
			limpiar();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void borrarCat(int id) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con.prepareStatement("delete from catalogo where id=?");
			ins_pstmt.setInt(1, id);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Catalogo borrado correctamente");
			mostrarCatalogo(modeloCatalogo);
			limpiar();
			limpiarmuchos();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrarCatalogo(DefaultTableModel model) {
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM catalogo ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Object[] row = new Object[4];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("Nombre");
				row[2] = rs.getString("descripcion");
				row[3] = rs.getInt("precio");

				model.addRow(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException ex) { // Caso erróneo
			JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos/n" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarTienda() {
		tfIDTIenda.setText("");
		tfNombreTienda.setText("");
		tfGerente.setText("");
		spNumempleados.setValue(2);
	}

	public void insertarTienda(String nombre, String gerente, int numEmpleados) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("insert into tienda (nombre,gerente,numEmpleados) values (?,?,?)");
			ins_pstmt.setString(1, nombre);
			ins_pstmt.setString(2, gerente);
			ins_pstmt.setInt(3, numEmpleados);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Tienda añadido");
			mostrartienda(modeloTienda);
			limpiarTienda();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void actualizartienda(int id, String nombre, String gerente, int numEmpleados) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("update tienda set nombre=?,gerente=? ,numEmpleados=? where id=?");
			ins_pstmt.setString(1, nombre);
			ins_pstmt.setString(2, gerente);
			ins_pstmt.setInt(3, numEmpleados);
			ins_pstmt.setInt(4, id);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "tienda actualizado");
			mostrartienda(modeloTienda);
			limpiarTienda();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void borrarTienda(int id) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con.prepareStatement("delete from tienda where id=?");
			ins_pstmt.setInt(1, id);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "tienda borrado correctamente");
			mostrartienda(modeloTienda);
			limpiarTienda();
			limpiarmuchos();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrartienda(DefaultTableModel model) {
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM tienda ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Object[] row = new Object[4];
				row[0] = rs.getInt("id");
				row[1] = rs.getString("Nombre");
				row[2] = rs.getString("gerente");
				row[3] = rs.getInt("numEmpleados");

				model.addRow(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException ex) { // Caso erróneo
			JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos/n" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void limpiarmuchos() {
		tfcatalogo.setText("");
		tftienda.setText("");

	}

	public void insertarmuchos(int idtienda, int idcatalogo) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("insert into catalogo_tienda (idtienda,idcatalogo) values (?,?)");
			ins_pstmt.setInt(1, idtienda);
			ins_pstmt.setInt(2, idcatalogo);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "catalogo añadido a la tienda");
			mostrarmuchos(modelocatalogo_tienda);
			limpiarmuchos();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void borrarmuchos(int idtienda, int idcatalogo) {
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement ins_pstmt = con
					.prepareStatement("delete from catalogo_tienda where idCatalogo=? and idTienda=?");
			ins_pstmt.setInt(1, idcatalogo);
			ins_pstmt.setInt(2, idtienda);
			ins_pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "catalogo_tienda borrado correctamente");
			mostrarmuchos(modelocatalogo_tienda);
			limpiarmuchos();
			ins_pstmt.close();
			con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	public void mostrarmuchos(DefaultTableModel model) {
		if (model.getRowCount() > 0) {
			model.setRowCount(0);
		}
		try {
			Connection con = ConnectionSingleton.getConnection();
			PreparedStatement stmt = con.prepareStatement("SELECT * FROM catalogo_tienda ");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Object[] row = new Object[2];
				row[0] = rs.getInt("idCatalogo");
				row[1] = rs.getString("idTienda");

				model.addRow(row);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException ex) { // Caso erróneo
			JOptionPane.showMessageDialog(null, "No se ha podido cargar los datos/n" + ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tienda_jdbc window = new Tienda_jdbc();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tienda_jdbc() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 797, 669);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		modeloCatalogo = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloCatalogo.addColumn("ID");
		modeloCatalogo.addColumn("Nombre");
		modeloCatalogo.addColumn("Descripcion");
		modeloCatalogo.addColumn("Precio");

		JTable tablaCatalogo = new JTable(modeloCatalogo);
		tablaCatalogo.setBounds(172, 185, 527, 0);
		tablaCatalogo.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sptablaCatalogo = new JScrollPane(tablaCatalogo);
		sptablaCatalogo.setBounds(29, 12, 372, 250);
		sptablaCatalogo.setEnabled(false);
		tablaCatalogo.getTableHeader().setReorderingAllowed(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(sptablaCatalogo);
		mostrarCatalogo(modeloCatalogo);

		modeloTienda = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modeloTienda.addColumn("ID");
		modeloTienda.addColumn("Nombre");
		modeloTienda.addColumn("Gerente");
		modeloTienda.addColumn("Nº Empleados");

		JTable tablaTienda = new JTable(modeloTienda);
		tablaTienda.setBounds(172, 185, 527, 0);
		tablaTienda.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sptablaTienda = new JScrollPane(tablaTienda);
		sptablaTienda.setBounds(415, 12, 372, 250);
		sptablaTienda.setEnabled(false);
		tablaTienda.getTableHeader().setReorderingAllowed(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(sptablaTienda);
		mostrartienda(modeloTienda);

		modelocatalogo_tienda = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		modelocatalogo_tienda.addColumn("idCatalogo");
		modelocatalogo_tienda.addColumn("idTienda");

		JTable tablacatalogo_tienda = new JTable(modelocatalogo_tienda);
		tablacatalogo_tienda.setBounds(172, 185, 527, 0);
		tablacatalogo_tienda.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		JScrollPane sptablacatalogo_tienda = new JScrollPane(tablacatalogo_tienda);
		sptablacatalogo_tienda.setBounds(25, 397, 372, 250);
		sptablacatalogo_tienda.setEnabled(false);
		tablacatalogo_tienda.getTableHeader().setReorderingAllowed(false);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(sptablacatalogo_tienda);
		mostrarmuchos(modelocatalogo_tienda);
		JLabel lblIdcat = new JLabel("ID:");
		lblIdcat.setBounds(29, 274, 70, 15);
		frame.getContentPane().add(lblIdcat);

		tfIdcat = new JTextField();
		tfIdcat.setEditable(false);
		tfIdcat.setBounds(163, 274, 114, 19);
		frame.getContentPane().add(tfIdcat);
		tfIdcat.setColumns(10);

		tfNombrecat = new JTextField();
		tfNombrecat.setColumns(10);
		tfNombrecat.setBounds(163, 303, 114, 19);
		frame.getContentPane().add(tfNombrecat);

		JLabel lblNombrecat = new JLabel("Nombre");
		lblNombrecat.setBounds(29, 303, 70, 15);
		frame.getContentPane().add(lblNombrecat);

		tfDescripcion = new JTextField();
		tfDescripcion.setColumns(10);
		tfDescripcion.setBounds(163, 337, 114, 19);
		frame.getContentPane().add(tfDescripcion);

		JLabel lblDescripcion = new JLabel("Descripcion:");
		lblDescripcion.setBounds(29, 337, 94, 15);
		frame.getContentPane().add(lblDescripcion);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(29, 370, 70, 15);
		frame.getContentPane().add(lblPrecio);

		spprecio = new JSpinner();
		spprecio.setBounds(163, 368, 114, 20);
		frame.getContentPane().add(spprecio);
		spprecio.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), Integer.valueOf(100),
				Integer.valueOf(1)));

		spNumempleados = new JSpinner();
		spNumempleados.setBounds(518, 368, 114, 20);
		frame.getContentPane().add(spNumempleados);
		spNumempleados.setModel(new SpinnerNumberModel(Integer.valueOf(2), Integer.valueOf(2), Integer.valueOf(10),
				Integer.valueOf(1)));

		JLabel lblNumEmpleados = new JLabel("Nº Empleados");
		lblNumEmpleados.setBounds(413, 370, 103, 15);
		frame.getContentPane().add(lblNumEmpleados);

		JLabel lblGerente = new JLabel("Gerente:");
		lblGerente.setBounds(413, 337, 70, 15);
		frame.getContentPane().add(lblGerente);

		tfGerente = new JTextField();
		tfGerente.setColumns(10);
		tfGerente.setBounds(518, 337, 114, 19);
		frame.getContentPane().add(tfGerente);

		JLabel lblNombreTienda = new JLabel("Nombre:");
		lblNombreTienda.setBounds(413, 303, 70, 15);
		frame.getContentPane().add(lblNombreTienda);

		tfNombreTienda = new JTextField();
		tfNombreTienda.setColumns(10);
		tfNombreTienda.setBounds(518, 303, 114, 19);
		frame.getContentPane().add(tfNombreTienda);

		tfIDTIenda = new JTextField();
		tfIDTIenda.setEditable(false);
		tfIDTIenda.setColumns(10);
		tfIDTIenda.setBounds(518, 274, 114, 19);
		frame.getContentPane().add(tfIDTIenda);

		JLabel lblIdtienda = new JLabel("ID:");
		lblIdtienda.setBounds(413, 274, 70, 15);
		frame.getContentPane().add(lblIdtienda);

		JButton btnAnyadircat = new JButton("Anyadir");

		btnAnyadircat.setBounds(286, 271, 117, 25);
		frame.getContentPane().add(btnAnyadircat);

		JButton btnActualizarcat = new JButton("Actualizar");

		btnActualizarcat.setBounds(286, 300, 117, 25);
		frame.getContentPane().add(btnActualizarcat);

		JButton btnBorrarcat = new JButton("Borrar");

		btnBorrarcat.setBounds(286, 334, 117, 25);
		frame.getContentPane().add(btnBorrarcat);

		JButton btnLimpiarCat = new JButton("Limpiar");

		btnLimpiarCat.setBounds(286, 365, 117, 25);
		frame.getContentPane().add(btnLimpiarCat);

		JButton btnLimpiarTien = new JButton("Limpiar");
		btnLimpiarTien.setBounds(633, 360, 117, 25);
		frame.getContentPane().add(btnLimpiarTien);

		JButton btnBorrartien = new JButton("Borrar");

		btnBorrartien.setBounds(633, 329, 117, 25);
		frame.getContentPane().add(btnBorrartien);

		JButton btnActualizarTien = new JButton("Actualizar");

		btnActualizarTien.setBounds(633, 295, 117, 25);
		frame.getContentPane().add(btnActualizarTien);

		JButton btnAnyadirTien = new JButton("Anyadir");

		btnAnyadirTien.setBounds(633, 266, 117, 25);
		frame.getContentPane().add(btnAnyadirTien);

		JButton btnLimpiarmucho = new JButton("Limpiar");
		btnLimpiarmucho.setBounds(633, 462, 117, 25);
		frame.getContentPane().add(btnLimpiarmucho);

		JButton btnBorrarmucho = new JButton("Borrar");

		btnBorrarmucho.setBounds(635, 425, 117, 25);
		frame.getContentPane().add(btnBorrarmucho);

		JButton btnAnyadirmucho = new JButton("Anyadir");

		btnAnyadirmucho.setBounds(635, 396, 117, 25);
		frame.getContentPane().add(btnAnyadirmucho);

		JLabel lblIdtiendaMucho = new JLabel("Tienda:");
		lblIdtiendaMucho.setBounds(415, 404, 70, 15);
		frame.getContentPane().add(lblIdtiendaMucho);

		JLabel lblCatalogo = new JLabel("Catalogo:");
		lblCatalogo.setBounds(415, 433, 70, 15);
		frame.getContentPane().add(lblCatalogo);

		tfcatalogo = new JTextField();
		tfcatalogo.setEditable(false);
		tfcatalogo.setColumns(10);
		tfcatalogo.setBounds(520, 433, 114, 19);
		frame.getContentPane().add(tfcatalogo);

		tftienda = new JTextField();
		tftienda.setEditable(false);
		tftienda.setColumns(10);
		tftienda.setBounds(520, 404, 114, 19);
		frame.getContentPane().add(tftienda);

		tablaCatalogo.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaCatalogo.getSelectedRow();
				TableModel model = tablaCatalogo.getModel();
				tfIdcat.setText(model.getValueAt(index, 0).toString());
				tfcatalogo.setText(model.getValueAt(index, 0).toString());
				tfNombrecat.setText(model.getValueAt(index, 1).toString());
				tfDescripcion.setText(model.getValueAt(index, 2).toString());
				spprecio.setValue(Integer.valueOf(model.getValueAt(index, 3).toString().toString()));

			}
		});
		tablaTienda.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablaTienda.getSelectedRow();
				TableModel model = tablaTienda.getModel();
				tfIDTIenda.setText(model.getValueAt(index, 0).toString());
				tftienda.setText(model.getValueAt(index, 0).toString());
				tfNombreTienda.setText(model.getValueAt(index, 1).toString());
				tfGerente.setText(model.getValueAt(index, 2).toString());
				spNumempleados.setValue(Integer.valueOf(model.getValueAt(index, 3).toString().toString()));

			}
		});
		tablacatalogo_tienda.addMouseListener((MouseListener) new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int index = tablacatalogo_tienda.getSelectedRow();
				TableModel model = tablacatalogo_tienda.getModel();
				tfcatalogo.setText(model.getValueAt(index, 0).toString());
				tftienda.setText(model.getValueAt(index, 1).toString());

			}
		});

		btnAnyadircat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfIdcat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Dale al boron de limpiar campos para dejar el campo id libre ",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else if (tfNombrecat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el nombre vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfDescripcion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar la descripcion vacia ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					insertarCat(tfNombrecat.getText(), tfDescripcion.getText(),
							Integer.valueOf(spprecio.getValue().toString()));
				}
			}
		});
		btnActualizarcat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIdcat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un catalogo ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfNombrecat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el nombre vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfDescripcion.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar la descripcion vacia ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					actualizarcatalogo(Integer.valueOf(tfIdcat.getText()), tfNombrecat.getText(),
							tfDescripcion.getText(), Integer.valueOf(spprecio.getValue().toString()));
				}

			}
		});
		btnBorrarcat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIdcat.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un catalogo ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					borrarCat(Integer.valueOf(tfIdcat.getText()));
				}
			}
		});
		btnLimpiarCat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		btnAnyadirTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!tfIDTIenda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Dale al boron de limpiar campos para dejar el campo id libre ",
							"ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
				} else if (tfNombreTienda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el nombre vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfGerente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el gerente vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					insertarTienda(tfNombreTienda.getText(), tfGerente.getText(),
							Integer.valueOf(spNumempleados.getValue().toString()));
				}
			}
		});
		btnActualizarTien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIDTIenda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione una tienda ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfNombreTienda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el nombre vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfGerente.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "No puedes dejar el gerente vacio ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					actualizartienda(Integer.valueOf(tfIDTIenda.getText()), tfNombreTienda.getText(),
							tfGerente.getText(), Integer.valueOf(spNumempleados.getValue().toString()));

				}
			}
		});
		btnBorrartien.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tfIDTIenda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione una tienda ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					borrarTienda(Integer.valueOf(tfIDTIenda.getText()));
				}
			}
		});
		btnAnyadirmucho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tftienda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione una tienda ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfcatalogo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un catalogo ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					insertarmuchos(Integer.valueOf(tftienda.getText()), Integer.valueOf(tfcatalogo.getText()));
				}
			}
		});
		btnBorrarmucho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tftienda.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione una tienda ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else if (tfcatalogo.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Seleccione un catalogo ", "ADVERTENCIA",
							JOptionPane.WARNING_MESSAGE);
				} else {
					borrarmuchos(Integer.valueOf(tftienda.getText()), Integer.valueOf(tfcatalogo.getText()));
				}
			}
		});
	}
}
