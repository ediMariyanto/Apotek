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
import model.Distributor;
import model.MySQLConn;
import model.MyTableModel;

/**
 *
 * @author arif
 */
public class CtrDistributor extends Distributor {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlDistributor;

    public CtrDistributor() {
    }

    public MyTableModel getModelDaftarDistributor() {
        try {
            conn = new MySQLConn();
            mdlDistributor = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Kd_Dist,\n"
                    + "  Nama,\n"
                    + "  Alamat,\n"
                    + "  Telp\n"
                    + "FROM distributor;");
            rs = pstm.executeQuery();

            mdlDistributor.addColumn("Kode");
            mdlDistributor.addColumn("Nama");
            mdlDistributor.addColumn("Alamat");
            mdlDistributor.addColumn("Telp");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Kd_Dist");
                o[1] = rs.getString("Nama");
                o[2] = rs.getString("Alamat");
                o[3] = rs.getString("Telp");
                mdlDistributor.addRow(o);
            }

            return mdlDistributor;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelCariDistributor() {
        try {
            conn = new MySQLConn();
            mdlDistributor = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Kd_Dist,\n"
                    + "  Nama,\n"
                    + "  Alamat,\n"
                    + "  Telp\n"
                    + "FROM distributor\n"
                    + "WHERE Nama like ?;");
            pstm.setString(1, "%" + getNama() + "%");
            rs = pstm.executeQuery();

            mdlDistributor.addColumn("Kode");
            mdlDistributor.addColumn("Nama");
            mdlDistributor.addColumn("Alamat");
            mdlDistributor.addColumn("Telp");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Kd_Dist");
                o[1] = rs.getString("Nama");
                o[2] = rs.getString("Alamat");
                o[3] = rs.getString("Telp");
                mdlDistributor.addRow(o);
            }

            return mdlDistributor;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean tambahDistributor() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO distributor\n"
                    + "            (Kd_Dist,\n"
                    + "             Nama,\n"
                    + "             Alamat,\n"
                    + "             Telp)\n"
                    + "VALUES (?,?,?,?);");
            pstm.setString(1, getKode());
            pstm.setString(2, getNama());
            pstm.setString(3, getAlamat());
            pstm.setString(4, getTelp());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahDistributor() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "UPDATE distributor\n"
                    + "SET \n"
                    + "  Nama = ?,\n"
                    + "  Alamat = ?,\n"
                    + "  Telp = ?\n"
                    + "WHERE Kd_Dist = ?");
            pstm.setString(1, getNama());
            pstm.setString(2, getAlamat());
            pstm.setString(3, getTelp());
            pstm.setString(4, getKode());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean hapusDistributor() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "DELETE\n"
                    + "FROM distributor\n"
                    + "WHERE Kd_Dist = ?");
            pstm.setString(1, getKode());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public ComboBoxModel getModelComboBoxDistributor() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  Kd_Dist,\n"
                    + "  Nama\n"
                    + "FROM distributor;");
            rs = pstm.executeQuery();

            DefaultComboBoxModel<ComboBoxItem> isiCmbDistributor = new DefaultComboBoxModel<>();

            while (rs.next()) {
                isiCmbDistributor.addElement(new ComboBoxItem(rs.getString(1), rs.getString(2)));
            }

            return isiCmbDistributor;
        } catch (SQLException ex) {
            Logger.getLogger(CtrDistributor.class.getName())
                    .log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
