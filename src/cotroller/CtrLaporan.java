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
import javax.swing.table.DefaultTableModel;
import model.Laporan;
import model.MySQLConn;
import model.MyTableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author fanny r
 */
public class CtrLaporan extends Laporan {

    MySQLConn conn;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlLaporan;

    public DefaultTableModel getLaporanStok() {
        try {
            conn = new MySQLConn();
            mdlLaporan = new MyTableModel();
            pstm = conn.connect(
                    "SELECT\n"
                    + "  d.Kd_Barang,\n"
                    + "  d.Merk_Barang,\n"
                    + "  d.UOM,\n"
                    + "  SUM(IFNULL(Jumlah,0)) Jumlah\n"
                    + "FROM\n"
                    + "(SELECT\n"
                    + "  Kd_Barang,\n"
                    + "  Jumlah\n"
                    + "FROM transaksi_beli\n"
                    + "WHERE Tgl_Trans<=?\n"
                    + "  AND Kd_Barang LIKE ?\n"
                    + "UNION\n"
                    + "SELECT\n"
                    + "  Kd_Barang,\n"
                    + "  -1*Jumlah AS Jumlah\n"
                    + "FROM transaksi_jual\n"
                    + "WHERE Tgl_Trans<=?\n"
                    + "  AND Kd_Barang LIKE ?) t\n"
                    + "RIGHT JOIN daftar_barang d\n"
                    + "ON t.Kd_Barang=CONCAT(d.Kd_Barang,d.Kd_Barang_Detail)\n"
                    + "WHERE d.Merk_Barang LIKE ?\n"
                    + "GROUP BY t.Kd_Barang\n"
                    + "ORDER BY d.Merk_Barang ASC"
            );
            pstm.setDate(1, new java.sql.Date(getTanggal().getTime()));
            pstm.setString(2, "%" + getKodeBarang() + "%");
            pstm.setDate(3, new java.sql.Date(getTanggal().getTime()));
            pstm.setString(4, "%" + getKodeBarang() + "%");
            pstm.setString(5, "%" + getMerkBarang() + "%");

            rs = pstm.executeQuery();

            mdlLaporan.addColumn("Kode Barang");
            mdlLaporan.addColumn("Merk Barang");
            mdlLaporan.addColumn("Jumlah");
            mdlLaporan.addColumn("Satuan");

            Object[] o;
            while (rs.next()) {
                o = new Object[4];
                o[0] = rs.getString("Kd_Barang");
                o[1] = rs.getString("Merk_Barang");
                o[2] = rs.getInt("Jumlah");
                o[3] = rs.getString("UOM");
                mdlLaporan.addRow(o);
            }

            return mdlLaporan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public void cetakLaporanStok() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/TransaksiDetail.jrxml");
            String jrxmlFile = "/view/LaporanStokBarang.jrxml";

            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(jrxmlFile));
            HashMap param = new HashMap();
            param.put("Par_Tanggal", new java.sql.Date(getTanggal().getTime()));
            param.put("Par_Kode_Barang", getKodeBarang());
            param.put("Par_Merk_Barang", getMerkBarang());

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

    public DefaultTableModel getLaporanTransaksiPerBarang() {
        try {
            conn = new MySQLConn();
            mdlLaporan = new MyTableModel();
            pstm = conn.connect(
                    "SELECT \n"
                    + "  t.TYPE,\n"
                    + "  t.Tgl_Trans,\n"
                    + "  t.Kd_Trans,\n"
                    + "  t.Nomor_Nota,\n"
                    + "  t.Kd_Barang,\n"
                    + "  d.Merk_Barang,\n"
                    + "  d.UOM,\n"
                    + "  t.Jumlah*t.Harga AS Harga,\n"
                    + "  t.Jumlah,\n"
                    + "  t.Distributor AS Referensi\n"
                    + "FROM \n"
                    + "(SELECT\n"
                    + "  'Beli' AS TYPE,\n"
                    + "  Tgl_Trans,\n"
                    + "  Kd_Trans,\n"
                    + "  Nomor_Nota,\n"
                    + "  Kd_Barang,\n"
                    + "  Harga,\n"
                    + "  Jumlah,\n"
                    + "  d.Nama AS Distributor\n"
                    + "FROM transaksi_beli AS t\n"
                    + "LEFT JOIN distributor AS d\n"
                    + "ON t.Distributor=d.Kd_Dist\n"
                    + "WHERE t.Tgl_Trans>=? AND t.Tgl_Trans<=?\n"
                    + "  AND t.Kd_Barang LIKE ?\n"
                    + "UNION \n"
                    + "SELECT\n"
                    + "  'Jual' AS TYPE,\n"
                    + "  Tgl_Trans,\n"
                    + "  Kd_Trans,\n"
                    + "  Nomor_Nota,\n"
                    + "  Kd_Barang,\n"
                    + "  Harga,\n"
                    + "  Jumlah,\n"
                    + "  k.Nama AS Penjual\n"
                    + "FROM transaksi_jual AS j\n"
                    + "LEFT JOIN karyawan AS k\n"
                    + "ON j.Penjual=k.username\n"
                    + "WHERE j.Tgl_Trans>=? AND j.Tgl_Trans<=?\n"
                    + "  AND j.Kd_Barang LIKE ?) AS t\n"
                    + "LEFT JOIN daftar_barang AS d\n"
                    + "ON t.Kd_Barang=CONCAT(d.Kd_Barang,d.Kd_Barang_Detail)\n"
                    + "WHERE d.Merk_Barang LIKE ?\n"
                    + "ORDER BY t.Kd_Barang ASC,\n"
                    + "  t.Type ASC,\n"
                    + "  t.Tgl_Trans ASC"
            );
            pstm.setDate(1, new java.sql.Date(getTanggal().getTime()));
            pstm.setDate(2, new java.sql.Date(getTanggal2().getTime()));
            pstm.setString(3, "%" + getKodeBarang() + "%");
            pstm.setDate(4, new java.sql.Date(getTanggal().getTime()));
            pstm.setDate(5, new java.sql.Date(getTanggal2().getTime()));
            pstm.setString(6, "%" + getKodeBarang() + "%");
            pstm.setString(7, "%" + getMerkBarang() + "%");

            rs = pstm.executeQuery();

            mdlLaporan.addColumn("Kode Barang");
            mdlLaporan.addColumn("Merk Barang");
            mdlLaporan.addColumn("Type");
            mdlLaporan.addColumn("Tanggal");
            mdlLaporan.addColumn("Kode Trans");
            mdlLaporan.addColumn("Nomor Nota");
            mdlLaporan.addColumn("Jumlah");
            mdlLaporan.addColumn("Satuan");
            mdlLaporan.addColumn("Harga");
            mdlLaporan.addColumn("Referensi");

            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getString("Kd_Barang");
                o[1] = rs.getString("Merk_Barang");
                o[2] = rs.getString("TYPE");
                o[3] = rs.getDate("Tgl_Trans");
                o[4] = rs.getString("Kd_Trans");
                o[5] = rs.getString("Nomor_Nota");
                o[6] = rs.getInt("Jumlah");
                o[7] = rs.getString("UOM");
                o[8] = rs.getDouble("Harga");
                o[9] = rs.getString("Referensi");
                mdlLaporan.addRow(o);
            }

            return mdlLaporan;
        } catch (SQLException ex) {
            Logger.getLogger(CtrTransaksi.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public void cetakLaporanTransaksiPerBarang() {
        try {
            conn = new MySQLConn();
//            File file = new File("./src/view/LaporanTransaksiPerBarang.jrxml");
            String jrxmlFile = "/view/LaporanTransaksiPerBarang.jrxml";

            JasperDesign jasperDesign = JRXmlLoader.load(getClass().getResourceAsStream(jrxmlFile));
            HashMap param = new HashMap();
            param.put("Par_TglDari", new java.sql.Date(getTanggal().getTime()));
            param.put("Par_TglSampai", new java.sql.Date(getTanggal2().getTime()));
            param.put("Par_Kode_Barang", getKodeBarang());
            param.put("Par_Merk_Barang", getMerkBarang());

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

}
