/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DAOInterface.IDAOBook;
import Helper.KoneksiDB;
import Model.Book;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author SYIFA MAULIDA
 */
public class DAOBook implements IDAOBook {

    public DAOBook() {
        con = KoneksiDB.connection();
    }

    @Override
    public List<Book> getAll() {
        List<Book> listBook = null;
        try {
            listBook = new ArrayList<Book>();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(strRead);
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublication_year(rs.getInt("publication_year"));
                listBook.add(book);
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        return listBook;
    }

    @Override
    public void insert(Book b) {
        PreparedStatement statement = null;

        try {
            statement = con.prepareStatement(strInsert);
            statement.setString(1, b.getTitle());
            statement.setString(2, b.getAuthor());
            statement.setInt(3, b.getPublication_year());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Gagal Input");
        } finally {
            try {
                statement.close();
            } catch (SQLException ex) {
                System.out.println("Gagal Input");
            }
        }
    }
    
    @Override
    public void delete(int bookId) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(strDelete);
            statement.setInt(1, bookId);
            int rowsAffected = statement.executeUpdate(); // Use executeUpdate to get the number of affected rows
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully.");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to delete book: " + e);
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException ex) {
                System.out.println("Failed to close statement: " + ex);
            }
        }
    }     
    
    @Override
    public void update(Book b) {
        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(strUpdate);
            statement.setString(1, b.getTitle());
            statement.setString(2, b.getAuthor());
            statement.setInt(3, b.getPublication_year());
            statement.setInt(4, b.getId());
            statement.execute();
        } catch (SQLException e) {
            System.out.println("Failed to update book: " + e);
        } finally {
           try {
               statement.close();
           } catch (SQLException ex) {
               System.out.println("Failed to close statement: " + ex);
           }
        }
    }
    Connection con;
    String strRead = "SELECT * FROM book;";
    String strInsert = "INSERT INTO book (title, author, publication_year) values (?, ?, ?);";
    String strDelete = "DELETE FROM book WHERE id = ?;";
    String strUpdate = "UPDATE book SET title = ?, author = ?, publication_year = ? WHERE id = ?";
}
