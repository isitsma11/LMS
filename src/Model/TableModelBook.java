/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author SYIFA MAULIDA
 */
public class TableModelBook extends AbstractTableModel {

    public TableModelBook(List<Book> listBook) {
        this.listBook = listBook;
    }

    @Override
    public int getRowCount() {
        return this.listBook.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "ID";
            case 1:
                return "Title";
            case 2:
                return "Author";
            case 3:
                return "Publication Year";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return listBook.get(rowIndex).getId();
            case 1:
                return listBook.get(rowIndex).getTitle();
            case 2:
                return listBook.get(rowIndex).getAuthor();
            case 3:
                return listBook.get(rowIndex).getPublication_year();
            default:
                return null;
        }
    }

    List<Book> listBook;
}
