/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotroller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQLConn;
import model.MyTableModel;
import model.Transaksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author arif
 */
public class CtrTransaksi extends Transaksi {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlTransaksi;

    public CtrTransaksi() {
    }

    public boolean tambahTransaksiBeli() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO transaksi_beli\n"
                    + "            (Tgl_Trans,\n"
                    + "             Nomor_Nota,\n"
                    + "             Kd_Barang,\n"
                    + "             Harga,\n"
                    + "             Jumlah,\n"
                    + "             Distributor)\n"
                    + "VALUES (?,?,?,?,?,?);");
            pstm.setDate(1, new java.sql.Date(getTgl().getTime()));
            pstm.setString(2, getNomorNota());
            pstm.setString(3, getKodeBarang());
            pstm.setDouble(4, getHarga());
            pstm.setInt(5, getJumlah());
            pstm.setString(6, getReferensi());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean tambahTransaksiJual() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "INSERT INTO transaksi_jual\n"
                    + "            (Tgl_Trans,\n"
                    + "             Nomor_Nota,\n"
                    + "             Kd_Barang,\n"
                    + "             Harga,\n"
                    + "             Jumlah,\n"
                    + "             Penjual)\n"
                    + "VALUES (?,?,?,?,?,?);");
            pstm.setDate(1, new java.sql.Date(getTgl().getTime()));
            pstm.setString(2, getNomorNota());
            pstm.setString(3, getKodeBarang());
            pstm.setDouble(4, getHarga());
            pstm.setInt(5, getJumlah());
            pstm.setString(6, getReferensi());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public DefaultTableModel getModelSemuaTransaksiJual() {
        try {
            conn = new MySQLConn();
            mdlTransaksi = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  max(Tgl_Trans) Tgl_Trans,\n"
                    + "  Nomor_Nota,\n"
                    + "  sum(Harga*Jumlah) Total,\n"
                    + "  max(Penjual) Penjual\n"
                    + "FROM transaksi_jual\n"
                    + "  Group by Nomor_Nota"
            );
            rs = pstm.executeQuery();

            mdlTransaksi.addColumn("Tgl");
            mdlTransaksi.addColumn("Nomor Nota");
            mdlTransaksi.addColumn("Total");
            mdlTransaksi.addColumn("Penjual");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getDate("Tgl_Trans");
                o[1] = rs.getString("Nomor_Nota");
                o[2] = rs.getDouble("Total");
                o[3] = rs.getString("Penjual");
                mdlTransaksi.addRow(o);
            }

            return mdlTransaksi;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public DefaultTableModel getModelCariTransaksiJual() {
        try {
            conn = new MySQLConn();
            mdlTransaksi = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  max(Tgl_Trans) Tgl_Trans,\n"
                    + "  Nomor_Nota,\n"
                    + "  sum(Harga*Jumlah) Total,\n"
                    + "  max(Penjual) Penjual\n"
                    + "FROM transaksi_jual\n"
                    + "  Group by Nomor_Nota\n"
                    + "HAVING Nomor_Nota like ?"
            );
            pstm.setString(1, "%" + getNomorNota() + "%");
            rs = pstm.executeQuery();

            mdlTransaksi.addColumn("Tgl");
            mdlTransaksi.addColumn("Nomor Nota");
            mdlTransaksi.addColumn("Total");
            mdlTransaksi.addColumn("Penjual");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getDate("Tgl_Trans");
                o[1] = rs.getString("Nomor_Nota");
                o[2] = rs.getDouble("Total");
                o[3] = rs.getString("Penjual");
                mdlTransaksi.addRow(o);
            }

            return mdlTransaksi;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public DefaultTableModel getModelCariTransaksiBeli() {
        try {
            conn = new MySQLConn();
            mdlTransaksi = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  max(Tgl_Trans) Tgl_Trans,\n"
                    + "  Nomor_Nota,\n"
                    + "  sum(Harga*Jumlah) Total,\n"
                    + "  max(Distributor) Distributor\n"
                    + "FROM transaksi_beli\n"
                    + "  Group by Nomor_Nota\n"
                    + "HAVING Nomor_Nota like ?"
            );
            pstm.setString(1, "%" + getNomorNota() + "%");
            rs = pstm.executeQuery();

            mdlTransaksi.addColumn("Tgl");
            mdlTransaksi.addColumn("Nomor Nota");
            mdlTransaksi.addColumn("Total");
            mdlTransaksi.addColumn("Distributor");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getDate("Tgl_Trans");
                o[1] = rs.getString("Nomor_Nota");
                o[2] = rs.getDouble("Total");
                o[3] = rs.getString("Distributor");
                mdlTransaksi.addRow(o);
            }

            return mdlTransaksi;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public void cetakDaftarTransaksiJual() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/DaftarTransaksiPenjualan.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/view/DaftarTransaksiPenjualan.jrxml"));
            HashMap param = new HashMap();
            param.clear();

            JasperReport jasperReport
                    = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            jasperReport, param, conn.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    e.getMessage(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cetakDaftarTransaksiBeli() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/DaftarTransaksiPembelian.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/view/DaftarTransaksiPembelian.jrxml"));
            HashMap param = new HashMap();
            param.clear();

            JasperReport jasperReport
                    = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            jasperReport, param, conn.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    e.getMessage(),
                    e.getMessage(),
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void cetakDaftarTransaksiJual2() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/DaftarTransaksiPenjualan2.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/view/DaftarTransaksiPenjualan2.jrxml"));
            HashMap param = new HashMap();
            param.put("Par_Nomor_Nota", getNomorNota());

            JasperReport jasperReport
                    = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            jasperReport, param, conn.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cetakDaftarTransaksiBeli2() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/DaftarTransaksiPembelian2.jrxml");
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream("/view/DaftarTransaksiPembelian2.jrxml"));
            HashMap param = new HashMap();
            param.put("Par_Nomor_Nota", getNomorNota());

            JasperReport jasperReport
                    = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            jasperReport, param, conn.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cetakDetail(String type) {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/TransaksiDetail.jrxml");
            String jrxmlFile = "";
            if (type.equals("beli")) {
                jrxmlFile = "/view/TransaksiDetailBeli.jrxml";
            } else if (type.equals("jual")) {
                jrxmlFile = "/view/TransaksiDetailJual.jrxml";
            }
            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(jrxmlFile));
            HashMap param = new HashMap();
            param.put("Par_Nomor_Nota", getNomorNota());

            JasperReport jasperReport
                    = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint
                    = JasperFillManager.fillReport(
                            jasperReport, param, conn.getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DefaultTableModel getTransaksiDetail(String type) {
        try {
            conn = new MySQLConn();
            mdlTransaksi = new MyTableModel();
            String query = "";
            if (type.equals("jual")) {
                query = "SELECT\n"
                        + "  Tgl_Trans,\n"
                        + "  Kd_Trans,\n"
                        + "  Nomor_Nota,\n"
                        + "  j.Kd_Barang,\n"
                        + "  b.Merk_Barang,\n"
                        + "  j.Harga,\n"
                        + "  Jumlah,\n"
                        + "  j.Harga*Jumlah AS Total,\n"
                        + "  Penjual\n"
                        + "FROM transaksi_jual AS j\n"
                        + "LEFT JOIN daftar_barang AS b\n"
                        + "ON j.Kd_Barang=CONCAT(b.Kd_Barang,b.Kd_Barang_Detail)\n"
                        + "WHERE Nomor_Nota like ?";
            } else if (type.equals("beli")) {
                query = "SELECT\n"
                        + "  Tgl_Trans,\n"
                        + "  Kd_Trans,\n"
                        + "  Nomor_Nota,\n"
                        + "  j.Kd_Barang,\n"
                        + "  b.Merk_Barang,\n"
                        + "  j.Harga,\n"
                        + "  Jumlah,\n"
                        + "  j.Harga*Jumlah AS Total,\n"
                        + "  Distributor\n"
                        + "FROM transaksi_beli AS j\n"
                        + "LEFT JOIN daftar_barang AS b\n"
                        + "ON j.Kd_Barang=CONCAT(b.Kd_Barang,b.Kd_Barang_Detail)\n"
                        + "WHERE Nomor_Nota like ?";
            }
            pstm = conn.connect(query);
            pstm.setString(1, "%" + getNomorNota() + "%");
            rs = pstm.executeQuery();

            mdlTransaksi.addColumn("Tgl_Trans");
            mdlTransaksi.addColumn("Kd_Trans");
            mdlTransaksi.addColumn("Nomor_Nota");
            mdlTransaksi.addColumn("Kd_Barang");
            mdlTransaksi.addColumn("Merk_Barang");
            mdlTransaksi.addColumn("Harga");
            mdlTransaksi.addColumn("Jumlah");
            mdlTransaksi.addColumn("Total");
            if (type.equals("jual")) {
                mdlTransaksi.addColumn("Penjual");
            } else if (type.equals("beli")) {
                mdlTransaksi.addColumn("Distributor");
            }
            Object[] o;
            while (rs.next()) {
                o = new Object[9];
                o[0] = rs.getDate("Tgl_Trans");
                o[1] = rs.getDouble("Kd_Trans");
                o[2] = rs.getString("Nomor_Nota");
                o[3] = rs.getString("Kd_Barang");
                o[4] = rs.getString("Merk_Barang");
                o[5] = rs.getDouble("Harga");
                o[6] = rs.getInt("Jumlah");
                o[7] = rs.getDouble("Total");
                if (type.equals("jual")) {
                    o[8] = rs.getString("Penjual");
                } else if (type.equals("beli")) {
                    o[8] = rs.getString("Distributor");
                }
                mdlTransaksi.addRow(o);
            }

            return mdlTransaksi;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean hapusJual() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "DELETE\n"
                    + "FROM transaksi_jual\n"
                    + "WHERE Nomor_Nota = ?;");
            pstm.setString(1, getNomorNota());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean hapusBeli() {
        try {
            conn = new MySQLConn();
            pstm = conn.connect(
                    "DELETE\n"
                    + "FROM transaksi_beli\n"
                    + "WHERE Nomor_Nota = ?;");
            pstm.setString(1, getNomorNota());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName())
                    .log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
