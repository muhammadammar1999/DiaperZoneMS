/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diapershopms;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author muhammadammar
 */
    public class InventoryItem {
    
    Long id;
    String name;
    String description;
    Long qty;
    Double purchasing_price;
    Double retail_price;
    String date;
 Long outlet_id;
 CheckBox select_checkbox;
    InventoryItem(Long id,  String name, String description, Long qty, Double purchasing_price, Double retail_price, String date,Long outletid) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.qty = qty;
        this.purchasing_price = purchasing_price;
        this.retail_price = retail_price;
        this.date = date;
this.outlet_id=outletid;
    this.select_checkbox = new CheckBox();
    }
 

    InventoryItem(ResultSet rs) throws SQLException, ClassNotFoundException {
        this.id = rs.getLong("id");
        this.name = rs.getString("name");
        this.description = rs.getString("description");
        this.qty = rs.getLong("qty");
        this.purchasing_price = rs.getDouble("purchasing_price");
        this.retail_price = rs.getDouble("purchasing_price");
        this.date = rs.getString("date");
       this.outlet_id = rs.getLong("outlet_id");
         this.select_checkbox = new CheckBox();
    }

    /**
     * Gets all rows from inventory table
     *
     * @return a list of object from class InventoryItem
     */
 
    static ObservableList<InventoryItem> getAll() {

        // initialize an empty list that contains objects of class InventoryItem
        ObservableList<InventoryItem> oblist = FXCollections.observableArrayList();
        try {
            // execute query on the database
            System.out.println("  getall");
        
            ResultSet rs = DbConnection.executeQuery("SELECT * FROM Inventory;");

            // iterate over every row that is returned from the executed
            // query above. next() method returns next row untill there are
            // no rows left
            while (rs.next()) {
                // add InventoryItem object in the empty list we created above
                oblist.add(new InventoryItem(rs));
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        // returning the list. This list
        // now contains all the rows fetched from the database
        return oblist;

    }

    static InventoryItem getOne(Long id) throws SQLException, ClassNotFoundException, NoSuchElementException {

        // execute query on the database
        ResultSet rs = DbConnection.executeQuery("SELECT * FROM Inventory WHERE id=" + id);
        if (rs.first()) {
            return new InventoryItem(rs);
        } else {
            throw new NoSuchElementException();
        }
    } 

    static void updateOne(InventoryItem item) throws SQLException, ClassNotFoundException {
              // execute query on the database
        DbConnection.executeUpdate("UPDATE Inventory SET name = ''"+item.name+" WHERE  id =62");
    }       
    
    static void addOne(InventoryItem item) throws SQLException, ClassNotFoundException {
               // execute query on the database
        DbConnection.executeUpdate(" insert into Inventory (name,description,qty,purchasing_price,retail_price,date, outlet_id)"
        + " values ('royal ','stage4', 6,630.0,200.0, '10',2)");

    }
    
    static void deleteOne(Long id) throws SQLException, ClassNotFoundException {
               // execute query on the database
        DbConnection.executeUpdate("DELETE from Inventory WHERE id=" + id );
    }
 static void transferItem(Long id,Long new_outletid) throws SQLException, ClassNotFoundException {
               // execute query on the database
        DbConnection.executeUpdate("UPDATE Inventory SET outlet_id  = ''"+new_outletid+" WHERE  id ="+id);
    }
    
}
