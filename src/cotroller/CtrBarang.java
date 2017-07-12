package cotroller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Barang;
import model.MySQLConn;
import model.MyTableModel;

public class CtrBarang extends Barang {

    MySQLConn con;
    Statement stm;
    PreparedStatement pstm;
    ResultSet rs;
    MyTableModel mdlBarang;

    public CtrBarang() {
        con = new MySQLConn();
        stm = con.connect();
        mdlBarang = new MyTableModel();
    }

    public MyTableModel getModelDaftarBarang() {
        try {
            rs = stm.executeQuery(
                    "SELECT\n"
                    + "  Kd_Barang,\n"
                    + "  Kd_Barang_Detail,\n"
                    + "  Merk_Barang,\n"
                    + "  UOM,\n"
                    + "  Harga,\n"
                    + "  b.Nm_Jns,\n"
                    + "  c.Nm_Gol,\n"
                    + "  d.Nm_Kel,\n"
                    + "  e.Nm_Bent,\n"
                    + "  f.Nm_Jns_Obat\n"
                    + "FROM daftar_barang AS a\n"
                    + "LEFT JOIN par_jns_brng AS b\n"
                    + "ON SUBSTRING(a.Kd_Barang,1,2)=b.Kd_Jns\n"
                    + "LEFT JOIN par_gol AS c\n"
                    + "ON SUBSTRING(a.Kd_Barang,3,2)=c.Kd_Gol\n"
                    + "LEFT JOIN par_kel_usia AS d\n"
                    + "ON SUBSTRING(a.Kd_Barang,5,2)=d.Kd_Kel\n"
                    + "LEFT JOIN par_bent AS e\n"
                    + "ON SUBSTRING(a.Kd_Barang,7,2)=e.Kd_Bent\n"
                    + "LEFT JOIN par_jns_obat AS f\n"
                    + "ON SUBSTRING(a.Kd_Barang,9,2)=f.Kd_Jns_Obat"
            );

            mdlBarang.addColumn("Kode");
            mdlBarang.addColumn("Kode Detail");
            mdlBarang.addColumn("Merk");
            mdlBarang.addColumn("UOM");
            mdlBarang.addColumn("Harga");
            mdlBarang.addColumn("Jenis");
            mdlBarang.addColumn("Gol");
            mdlBarang.addColumn("Kel Usia");
            mdlBarang.addColumn("Bentuk");
            mdlBarang.addColumn("Jenis Obat");
            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getString("Kd_Barang");
                o[1] = rs.getString("Kd_Barang_Detail");
                o[2] = rs.getString("Merk_Barang");
                o[3] = rs.getString("UOM");
                o[4] = rs.getDouble("Harga");
                o[5] = rs.getString("Nm_Jns");
                o[6] = rs.getString("Nm_Gol");
                o[7] = rs.getString("Nm_Kel");
                o[8] = rs.getString("Nm_Bent");
                o[9] = rs.getString("Nm_Jns_Obat");
                mdlBarang.addRow(o);
            }

            return mdlBarang;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelCariBarang() {
        try {
            pstm = con.connect(
                    "SELECT\n"
                    + "  Kd_Barang,\n"
                    + "  Kd_Barang_Detail,\n"
                    + "  Merk_Barang,\n"
                    + "  UOM,\n"
                    + "  Harga,\n"
                    + "  b.Nm_Jns,\n"
                    + "  c.Nm_Gol,\n"
                    + "  d.Nm_Kel,\n"
                    + "  e.Nm_Bent,\n"
                    + "  f.Nm_Jns_Obat\n"
                    + "FROM daftar_barang AS a\n"
                    + "LEFT JOIN par_jns_brng AS b\n"
                    + "ON SUBSTRING(a.Kd_Barang,1,2)=b.Kd_Jns\n"
                    + "LEFT JOIN par_gol AS c\n"
                    + "ON SUBSTRING(a.Kd_Barang,3,2)=c.Kd_Gol\n"
                    + "LEFT JOIN par_kel_usia AS d\n"
                    + "ON SUBSTRING(a.Kd_Barang,5,2)=d.Kd_Kel\n"
                    + "LEFT JOIN par_bent AS e\n"
                    + "ON SUBSTRING(a.Kd_Barang,7,2)=e.Kd_Bent\n"
                    + "LEFT JOIN par_jns_obat AS f\n"
                    + "ON SUBSTRING(a.Kd_Barang,9,2)=f.Kd_Jns_Obat\n"
                    + "WHERE CASE WHEN ISNULL(merk_barang) THEN '' ELSE merk_barang END LIKE ?"
            );
            pstm.setString(1, "%" + getMerk() + "%");
            rs = pstm.executeQuery();

            mdlBarang.addColumn("Kode");
            mdlBarang.addColumn("Kode Detail");
            mdlBarang.addColumn("Merk");
            mdlBarang.addColumn("UOM");
            mdlBarang.addColumn("Harga");
            mdlBarang.addColumn("Jenis");
            mdlBarang.addColumn("Gol");
            mdlBarang.addColumn("Kel Usia");
            mdlBarang.addColumn("Bentuk");
            mdlBarang.addColumn("Jenis Obat");

            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getString("Kd_Barang");
                o[1] = rs.getString("Kd_Barang_Detail");
                o[2] = rs.getString("Merk_Barang");
                o[3] = rs.getString("UOM");
                o[4] = rs.getDouble("Harga");
                o[5] = rs.getString("Nm_Jns");
                o[6] = rs.getString("Nm_Gol");
                o[7] = rs.getString("Nm_Kel");
                o[8] = rs.getString("Nm_Bent");
                o[9] = rs.getString("Nm_Jns_Obat");
                mdlBarang.addRow(o);
            }

            return mdlBarang;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public MyTableModel getModelCariAllBarang() {
        try {
            pstm = con.connect(
                    "SELECT\n"
                    + "  Kd_Barang,\n"
                    + "  Kd_Barang_Detail,\n"
                    + "  Merk_Barang,\n"
                    + "  UOM,\n"
                    + "  Harga,\n"
                    + "  b.Nm_Jns,\n"
                    + "  c.Nm_Gol,\n"
                    + "  d.Nm_Kel,\n"
                    + "  e.Nm_Bent,\n"
                    + "  f.Nm_Jns_Obat\n"
                    + "FROM daftar_barang AS a\n"
                    + "LEFT JOIN par_jns_brng AS b\n"
                    + "ON SUBSTRING(a.Kd_Barang,1,2)=b.Kd_Jns\n"
                    + "LEFT JOIN par_gol AS c\n"
                    + "ON SUBSTRING(a.Kd_Barang,3,2)=c.Kd_Gol\n"
                    + "LEFT JOIN par_kel_usia AS d\n"
                    + "ON SUBSTRING(a.Kd_Barang,5,2)=d.Kd_Kel\n"
                    + "LEFT JOIN par_bent AS e\n"
                    + "ON SUBSTRING(a.Kd_Barang,7,2)=e.Kd_Bent\n"
                    + "LEFT JOIN par_jns_obat AS f\n"
                    + "ON SUBSTRING(a.Kd_Barang,9,2)=f.Kd_Jns_Obat\n"
                    + "WHERE CONCAT(CASE WHEN ISNULL(Kd_Barang) THEN '' ELSE Kd_Barang END\n"
                    + "    ,' ',CASE WHEN ISNULL(Kd_Barang_Detail) THEN '' ELSE Kd_Barang_Detail END\n"
                    + "    ,' ',CASE WHEN ISNULL(Merk_Barang) THEN '' ELSE Merk_Barang END)\n"
                    + "      LIKE ?"
            );
            pstm.setString(1, "%" + getMerk() + "%");
            rs = pstm.executeQuery();

            mdlBarang.addColumn("Kode");
            mdlBarang.addColumn("Kode Detail");
            mdlBarang.addColumn("Merk");
            mdlBarang.addColumn("UOM");
            mdlBarang.addColumn("Harga");
            mdlBarang.addColumn("Jenis");
            mdlBarang.addColumn("Gol");
            mdlBarang.addColumn("Kel Usia");
            mdlBarang.addColumn("Bentuk");
            mdlBarang.addColumn("Jenis Obat");
            Object[] o;
            while (rs.next()) {
                o = new Object[10];
                o[0] = rs.getString("Kd_Barang");
                o[1] = rs.getString("Kd_Barang_Detail");
                o[2] = rs.getString("Merk_Barang");
                o[3] = rs.getString("UOM");
                o[4] = rs.getDouble("Harga");
                o[5] = rs.getString("Nm_Jns");
                o[6] = rs.getString("Nm_Gol");
                o[7] = rs.getString("Nm_Kel");
                o[8] = rs.getString("Nm_Bent");
                o[9] = rs.getString("Nm_Jns_Obat");
                mdlBarang.addRow(o);
            }

            return mdlBarang;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean tambahBarang() {
        try {
            pstm = con.connect(
                    "INSERT INTO `test`.`daftar_barang`\n"
                    + "            (`Kd_Barang`,\n"
                    + "             `Kd_Barang_Detail`,\n"
                    + "             `Merk_Barang`,\n"
                    + "             `UOM`,\n"
                    + "             `Harga`)\n"
                    + "VALUES (?,?,?,?,?);"
            );
            pstm.setString(1, getKode());
            pstm.setString(2, getKodeDetail());
            pstm.setString(3, getMerk());
            pstm.setString(4, getUOM());
            pstm.setDouble(5, getHarga());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean ubahBarang() {
        try {
            pstm = con.connect(
                    "UPDATE daftar_barang\n"
                    + "SET \n"
                    + "  Merk_Barang = ?,\n"
                    + "  UOM = ?,\n"
                    + "  Harga = ?\n"
                    + "WHERE Kd_Barang = ?\n"
                    + "    AND Kd_Barang_Detail = ?"
            );
            pstm.setString(1, getMerk());
            pstm.setString(2, getUOM());
            pstm.setDouble(3, getHarga());
            pstm.setString(4, getKode());
            pstm.setString(5, getKodeDetail());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean hapusBarang() {
        try {
            pstm = con.connect(
                    "DELETE\n"
                    + "FROM daftar_barang\n"
                    + "WHERE Kd_Barang = ?\n"
                    + "    AND Kd_Barang_Detail = ?"
            );
            pstm.setString(1, getKode());
            pstm.setString(2, getKodeDetail());
            pstm.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CtrBarang.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
    }
}
