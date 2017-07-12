/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotroller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import model.ComboBoxItem;
import model.Karyawan;
import model.MySQLConn;
import model.MyTableModel;

/**
 *
 * @author arif
 */
public class CtrKaryawan extends Karyawan {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlKaryawan;

    public CtrKaryawan() {
    }

    public MyTableModel getModelDaftarKaryawan() {
        try {
            conn = new MySQLConn();
            mdlKaryawan = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Username,\n"
                    + "  Password,\n"
                    + "  Nama,\n"
                    + "  Jabatan\n"
                    + "FROM karyawan;");
            rs = pstm.executeQuery();

            mdlKaryawan.addColumn("Username");
            mdlKaryawan.addColumn("Password");
            mdlKaryawan.addColumn("Nama");
            mdlKaryawan.addColumn("Jabatan");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Username");
                o[1] = rs.getString("Password");
                o[2] = rs.getString("Nama");
                o[3] = rs.getString("Jabatan");
                mdlKaryawan.addRow(o);
            }

            return mdlKaryawan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelCariKaryawan() {
        try {
            conn = new MySQLConn();
            mdlKaryawan = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Username,\n"
                    + "  Password,\n"
                    + "  Nama,\n"
                    + "  Jabatan\n"
                    + "FROM karyawan\n"
                    + "WHERE Nama like ?;");
            pstm.setString(1, "%" + getNama() + "%");
            rs = pstm.executeQuery();

            mdlKaryawan.addColumn("Username");
            mdlKaryawan.addColumn("Password");
            mdlKaryawan.addColumn("Nama");
            mdlKaryawan.addColumn("Jabatan");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Username");
                o[1] = rs.getString("Password");
                o[2] = rs.getString("Nama");
                o[3] = rs.getString("Jabatan");
                mdlKaryawan.addRow(o);
            }

            return mdlKaryawan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean tambahKaryawan() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO karyawan\n"
                    + "            (Username,\n"
                    + "             Password,\n"
                    + "             Nama,\n"
                    + "             Jabatan)\n"
                    + "VALUES (?,?,?,?);");
            pstm.setString(1, getUsername());
            pstm.setString(2, getPassword());
            pstm.setString(3, getNama());
            pstm.setString(4, getJabatan());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahKaryawan() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "UPDATE karyawan\n"
                    + "SET \n"
                    + "  Password = ?,\n"
                    + "  Nama = ?,\n"
                    + "  Jabatan = ?\n"
                    + "WHERE Username = ?");
            pstm.setString(1, getPassword());
            pstm.setString(2, getNama());
            pstm.setString(3, getJabatan());
            pstm.setString(4, getUsername());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean hapusKaryawan() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "DELETE\n"
                    + "FROM karyawan\n"
                    + "WHERE Username = ?");
            pstm.setString(1, getUsername());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Karyawan checkLogin() {
        try {
            Karyawan karyawan = null;
            conn = new MySQLConn();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Username,\n"
                    + "  Nama,\n"
                    + "  Jabatan\n"
                    + "FROM karyawan\n"
                    + "WHERE Username = ?\n"
                    + "   and Password = ?");
            pstm.setString(1, getUsername());
            pstm.setString(2, getPassword());
            rs = pstm.executeQuery();

            if (rs.next()) {
                karyawan = new Karyawan();
                karyawan.setUsername(rs.getString("Username"));
                karyawan.setNama(rs.getString("Nama"));
                karyawan.setJabatan(rs.getString("Jabatan"));
            }

            return karyawan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public ComboBoxModel getModelComboBoxKaryawan() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Username,\n"
                    + "  Nama\n"
                    + "FROM karyawan;");
            rs = pstm.executeQuery();

            DefaultComboBoxModel<ComboBoxItem> isiCmbKaryawan = new DefaultComboBoxModel<>();

            while (rs.next()) {
                isiCmbKaryawan.addElement(new ComboBoxItem(rs.getString(2), rs.getString(1)));
            }

            return isiCmbKaryawan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrKaryawan.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
