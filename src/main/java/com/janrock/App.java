package com.janrock;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import com.janrock.kdb.connector;

public class App {
    public static class KxTableModel extends AbstractTableModel {
        private connector.Flip flip;
        public void setFlip(connector.Flip data) {
            this.flip = data;
        }

        public int getRowCount() {
            return Array.getLength(flip.y[0]);
        }

        public int getColumnCount() {
            return flip.y.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return connector.at(flip.y[columnIndex], rowIndex);
        }

//        @Override
//        public String getColumnName(int columnIndex) {
//            return flip.x[columnIndex];
//        }
    }

    public static void main(String[] args) {

        Logger logger = Logger.getLogger(App.class.getName());

        KxTableModel model = new KxTableModel();
        connector c = null;
        try {
            c = new connector("192.168.0.200", 5000); //System.getProperty("user.name")+":mypassword");
            String query="(select from trades)";
            model.setFlip((connector.Flip) c.k(query));
            for (int i=0; i <= model.getRowCount()-1; i++){
                for (int col=0; col <= model.getColumnCount()-1; col++) {
                    System.out.print(model.getValueAt(i, col) + "\t");
                }
                System.out.print("\n");
            }
            logger.info("Table row count: " + model.getRowCount());
        } catch (Exception ex) {
            logger.severe(ex.toString());
        } finally {
            if (c != null) {
                try {
                    c.close();
                }
                catch (IOException ex) {
                    // ignore exception
                }
            }
        }
    }
}
