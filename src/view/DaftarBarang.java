package view;

import cotroller.CtrBarang;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import model.Parameter;

public class DaftarBarang extends javax.swing.JInternalFrame {

    public DaftarBarang() {
        initComponents();
        this.setTitle("Daftar Barang");
        cariBarang();
        setVisible(true);
        setEditState(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblDaftarBarang = new javax.swing.JTable();
        lblKode = new javax.swing.JLabel();
        lblKodeDetail = new javax.swing.JLabel();
        lblMerk = new javax.swing.JLabel();
        lblUOM = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        txtKodeDetail = new javax.swing.JTextField();
        txtMerk = new javax.swing.JTextField();
        txtUOM = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnTambah = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnUbah = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        btnBatal = new javax.swing.JButton();

        tblDaftarBarang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblDaftarBarang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDaftarBarangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDaftarBarang);

        lblKode.setText("Kode :");

        lblKodeDetail.setText("Kode Detail :");

        lblMerk.setText("Merk :");

        lblUOM.setText("UOM :");

        lblHarga.setText("Harga :");

        txtHarga.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnUbah.setText("Ubah");
        btnUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUbahActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblKode, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblKodeDetail, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblMerk, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblUOM, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblHarga, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTambah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnClear)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnUbah)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnHapus)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBatal))
                            .addComponent(txtMerk)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtKodeDetail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addComponent(txtKode, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtHarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                                .addComponent(txtUOM, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addGap(0, 153, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKode)
                    .addComponent(txtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKodeDetail)
                    .addComponent(txtKodeDetail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMerk)
                    .addComponent(txtMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUOM)
                    .addComponent(txtUOM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCari)
                    .addComponent(btnTambah)
                    .addComponent(btnClear)
                    .addComponent(btnUbah)
                    .addComponent(btnHapus)
                    .addComponent(btnBatal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariBarang();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        CtrBarang cb = new CtrBarang();
        cb.setKode(txtKode.getText());
        cb.setKodeDetail(txtKodeDetail.getText());
        cb.setMerk(txtMerk.getText());
        cb.setUOM(txtUOM.getText());
        cb.setHarga(Double.parseDouble(txtHarga.getText()));
        cb.tambahBarang();
        clearInput();
        cariBarang();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearInput();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblDaftarBarangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDaftarBarangMouseClicked
        if (evt.getClickCount() == 1) {
            JTable target = (JTable) evt.getSource();
            int row = target.getSelectedRow();
            setEditState(true);
            txtKode.setText(target.getModel().getValueAt(row, 0).toString());
            txtKodeDetail.setText(target.getModel().getValueAt(row, 1).toString());
            txtMerk.setText(target.getModel().getValueAt(row, 2).toString());
            txtUOM.setText(target.getModel().getValueAt(row, 3).toString());
            txtHarga.setText(target.getModel().getValueAt(row, 4).toString().replaceAll(",", ""));
        }
    }//GEN-LAST:event_tblDaftarBarangMouseClicked

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        clearInput();
        setEditState(false);
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUbahActionPerformed
        CtrBarang cb = new CtrBarang();
        cb.setKode(txtKode.getText());
        cb.setKodeDetail(txtKodeDetail.getText());
        cb.setMerk(txtMerk.getText());
        cb.setUOM(txtUOM.getText());
        cb.setHarga(Double.parseDouble(txtHarga.getText()));
        cb.ubahBarang();
        setEditState(false);
        clearInput();
        cariBarang();
    }//GEN-LAST:event_btnUbahActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        CtrBarang cb = new CtrBarang();
        cb.setKode(txtKode.getText());
        cb.setKodeDetail(txtKodeDetail.getText());
        cb.setMerk(txtMerk.getText());
        cb.setUOM(txtUOM.getText());
        cb.setHarga(Double.parseDouble(txtHarga.getText()));
        cb.hapusBarang();
        setEditState(false);
        clearInput();
        cariBarang();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void setEditState(boolean state) {
        txtKode.setEnabled(!state);
        txtKodeDetail.setEnabled(!state);
        btnCari.setEnabled(!state);
        btnClear.setEnabled(!state);
        btnTambah.setEnabled(!state);
        btnUbah.setEnabled(state);
        btnHapus.setEnabled(state);
        btnBatal.setEnabled(state);
    }

    private void cariBarang() {
        CtrBarang cb = new CtrBarang();
        cb.setMerk(txtMerk.getText());
        tblDaftarBarang.setModel(cb.getModelCariBarang());
        tblDaftarBarang.getColumnModel().getColumn(4).setCellRenderer(Parameter.rightRenderer());
        if (tblDaftarBarang.getModel().getRowCount() > 0) {
            tblDaftarBarang.setRowSelectionInterval(0, 0);
        }
    }

    private void clearInput() {
        txtKode.setText("");
        txtKodeDetail.setText("");
        txtMerk.setText("");
        txtUOM.setText("");
        txtHarga.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JButton btnUbah;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblKodeDetail;
    private javax.swing.JLabel lblMerk;
    private javax.swing.JLabel lblUOM;
    private javax.swing.JTable tblDaftarBarang;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtKodeDetail;
    private javax.swing.JTextField txtMerk;
    private javax.swing.JTextField txtUOM;
    // End of variables declaration//GEN-END:variables
}
