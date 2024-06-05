/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.DAOBook;
import DAOInterface.IDAOBook;
import Model.Book;
import Model.TableModelBook;
import View.FormBook;
import View.FormCreateBook;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author SYIFA MAULIDA
 */
public class ControllerBook {

    public ControllerBook(FormBook frmBook) {
        this.frmBook = frmBook;
        iBook = new DAOBook();
    }
    
    public ControllerBook(FormCreateBook frmCBook) {
        this.frmCBook = frmCBook;
        iBook = new DAOBook();
    }


    public void isiTable() {
        listBook = iBook.getAll();
        TableModelBook tableBook = new TableModelBook(listBook);
        frmBook.getTabelData().setModel(tableBook);
    }

    public void insert() {
        Book b = new Book();
        b.setTitle(frmCBook.gettxtTitle().getText());
        b.setAuthor(frmCBook.gettxtAuthor().getText());
        b.setPublication_year(Integer.parseInt(frmCBook.gettxtPublicationYear().getText()));
        iBook.insert(b);
        JOptionPane.showConfirmDialog(null, "Input Berhasil");
    }
    
    public void deleteBook() {
        int selectedRowIndex = frmBook.getTabelData().getSelectedRow();
        if (selectedRowIndex != -1) {
            int bookId = (int) frmBook.getTabelData().getValueAt(selectedRowIndex, 0);
            iBook.delete(bookId);
            isiTable();
            JOptionPane.showMessageDialog(frmBook, "Book Deleted!");
        } else {
            JOptionPane.showMessageDialog(frmBook, "Please Select a book to delete");
        }
    }
            
public void updateBook() {
    int selectedRowIndex = frmBook.getTabelData().getSelectedRow();
    if (selectedRowIndex != -1) {
        int bookId = (int) frmBook.getTabelData().getValueAt(selectedRowIndex, 0);
        
        // Open the form to update book details
        FormCreateBook updateForm = new FormCreateBook();
        
        // Get the existing details of the selected book
        String title = frmBook.getTabelData().getValueAt(selectedRowIndex, 1).toString();
        String author = frmBook.getTabelData().getValueAt(selectedRowIndex, 2).toString();
        int publicationYear = Integer.parseInt(frmBook.getTabelData().getValueAt(selectedRowIndex, 3).toString());
        
        // Set the existing details in the update form
        updateForm.gettxtTitle().setText(title);
        updateForm.gettxtAuthor().setText(author);
        updateForm.gettxtPublicationYear().setText(String.valueOf(publicationYear));
        
        // Show the update form
        updateForm.setVisible(true);
        
        // After the user updates the information in the form and submits it, the FormCreateBook class should handle the update process by calling the update method in the controller or directly in the DAO class.
    } else {
        JOptionPane.showMessageDialog(frmBook, "Please select a book to update");
    }
}
    FormBook frmBook;
    FormCreateBook frmCBook;
    IDAOBook iBook;
    List<Book> listBook;
}
