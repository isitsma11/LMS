/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAOInterface;

import Model.Book;
import java.util.List;

/**
 *
 * @author SYIFA MAULIDA
 */
public interface IDAOBook {
    //read data
    public List<Book> getAll();
    //create data
    public void insert(Book b);
    public void delete(int bookId);
    public void update (Book b);
}
