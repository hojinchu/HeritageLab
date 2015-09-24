package com.hojin.com.hojin.model;

import javax.swing.table.AbstractTableModel;
import java.util.Objects;

/**
 * Created by hojin on 15. 9. 23.
 */
public class HeritageModel extends AbstractTableModel {
    String[] columnNames={"문화재 번호","문화재 이름"};
    Object[][] objects={{" "," "}};

    public HeritageModel(Object[][] objects) {
        this.objects = objects;
    }

    @Override
    public int getRowCount() {
        return objects.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return objects[rowIndex][columnIndex];
    }

    public String getColumnName(int column){
        return columnNames[column];
    }

    boolean[] columnEditables=new boolean[]{
            false, true, true, true
    };

    public boolean isCellEditable(int row, int column){
        return  columnEditables[column];
    }

    public void setValueAt(Object value,int row,int col){
        objects[row][col]=value;
        fireTableCellUpdated(row,col);
    }
}













