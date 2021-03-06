package view;

import cotroller.CtrTransaksi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import model.Parameter;

/**
 *
 * @author Edi Mariyanto
 */
public class DaftarTransaksiPenjualan extends javax.swing.JInternalFrame {

    /**
     * Creates new form DaftarTransaksi
     */
    public DaftarTransaksiPenjualan() {
        initComponents();
        this.setTitle("Daftar Transaksi Penjualan");
        loadTransaksi();
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblTransaksi = new javax.swing.JTable();
        btnCetak = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblTransaksi.setModel(new javax.swing.table.DefaultTableModel(
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
        tblTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblTransaksiMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblTransaksi);

        btnCetak.setText("Cetak");
        btnCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCetakActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCetak)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCetak)
                    .addComponent(btnRefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        CtrTransaksi ct = new CtrTransaksi();
        ct.cetakDaftarTransaksiJual();
    }//GEN-LAST:event_btnCetakActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        loadTransaksi();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblTransaksiMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblTransaksiMouseReleased
        klikKanan(evt);
    }//GEN-LAST:event_tblTransaksiMouseReleased

    private void klikKanan(java.awt.event.MouseEvent evt) {
        int r = tblTransaksi.rowAtPoint(evt.getPoint());
        if (r >= 0 && r < tblTransaksi.getRowCount()) {
            tblTransaksi.setRowSelectionInterval(r, r);
        } else {
            tblTransaksi.clearSelection();
        }
        int rowindex = tblTransaksi.getSelectedRow();
        if (rowindex < 0) {
            return;
        }
        if (evt.getComponent() instanceof JTable) { //only right click -> (e.isPopupTrigger() && e.getComponent() instanceof JTable)
            JPopupMenu popup = new JPopupMenu();
            JMenuItem previewItem = new JMenuItem("Preview");
            previewItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowindex = tblTransaksi.getSelectedRow();
                    TransaksiDetail td = new TransaksiDetail("jual", tblTransaksi.getValueAt(rowindex, 1).toString());
                }
            });
            JMenuItem cetakItem = new JMenuItem("Cetak");
            cetakItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowindex = tblTransaksi.getSelectedRow();
                    CtrTransaksi ct = new CtrTransaksi();
                    ct.setNomorNota(tblTransaksi.getValueAt(rowindex, 1).toString());
                    ct.cetakDetail("jual");
                }
            });
            JMenuItem deleteItem = new JMenuItem("Hapus");
            deleteItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowindex = tblTransaksi.getSelectedRow();
                    String nomorNota = tblTransaksi.getValueAt(rowindex, 1).toString();
                    if (JOptionPane.showConfirmDialog(null,
                            "Yakin ingin hapus Nomor Nota : " + nomorNota + " ?",
                            "Hapus", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                        CtrTransaksi ct = new CtrTransaksi();
                        ct.setNomorNota(nomorNota);
                        ct.hapusJual();
                    }
                }
            });
            popup.add(previewItem);
            popup.add(cetakItem);
            popup.add(deleteItem);
            popup.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }
    
    private void loadTransaksi() {
        CtrTransaksi ct = new CtrTransaksi();
        tblTransaksi.setModel(ct.getModelSemuaTransaksiJual());
        tblTransaksi.getColumnModel().getColumn(2).setCellRenderer(Parameter.rightRenderer());
        tblTransaksi.getColumnModel().getColumn(0).setCellRenderer(Parameter.dateRenderer());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblTransaksi;
    // End of variables declaration//GEN-END:variables

}
