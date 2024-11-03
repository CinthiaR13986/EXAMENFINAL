/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.examenfinal;

/**
 *
 * @author Cinthia Robles
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/tu_base_datos";
    private static final String USER = "tu_usuario";
    private static final String PASSWORD = "tu_contraseña";

    private Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Método para insertar un producto
    public void insertarProducto(String nombre, String descripcion, double precio, int cantidad) {
        String sql = "INSERT INTO productos (nombre, descripcion, precio, cantidad) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.executeUpdate();
            System.out.println("Producto insertado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar el producto: " + e.getMessage());
        }
    }

    // Método para obtener todos los productos
    public void listarProductos() {
        String sql = "SELECT * FROM productos";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Descripción: " + rs.getString("descripcion"));
                System.out.println("Precio: " + rs.getDouble("precio"));
                System.out.println("Cantidad: " + rs.getInt("cantidad"));
                System.out.println("-------------");
            }
        } catch (SQLException e) {
            System.err.println("Error al listar los productos: " + e.getMessage());
        }
    }

    // Método para actualizar un producto
    public void actualizarProducto(int id, String nombre, String descripcion, double precio, int cantidad) {
        String sql = "UPDATE productos SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, descripcion);
            stmt.setDouble(3, precio);
            stmt.setInt(4, cantidad);
            stmt.setInt(5, id);
            stmt.executeUpdate();
            System.out.println("Producto actualizado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar el producto: " + e.getMessage());
        }
    }

    // Método para eliminar un producto
    public void eliminarProducto(int id) {
        String sql = "DELETE FROM productos WHERE id = ?";

        try (Connection conn = conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("Producto eliminado exitosamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar el producto: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();

        // Inserción de un producto
        dao.insertarProducto("Producto 1", "Descripción del producto 1", 100.00, 10);

        // Listado de productos
        dao.listarProductos();

        // Actualización de un producto
        dao.actualizarProducto(1, "Producto 1 Actualizado", "Nueva descripción", 120.00, 8);

        // Eliminación de un producto
        dao.eliminarProducto(1);
    }
}

