package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import models.Cliente;
import models.Pet;

public class PetController {
    // CRUD
    public static void create(Connection conn, Pet pet) throws SQLException {
        String sql = "INSERT INTO Pet (nome, especie, raca, idade, tutor_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getNome());
            pstmt.setString(2, pet.getEspecie());
            pstmt.setString(3, pet.getRaca());
            pstmt.setInt(4, pet.getIdade());
            pstmt.setInt(5, pet.getTutor().getId());
            pstmt.executeUpdate();
            System.out.println("Pet cadastrado com sucesso.");
        }
    }
    public static ArrayList<Pet> readAll(Connection conn) throws SQLException {
        ArrayList<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM Pet";
        try (Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                Pet pet = new Pet();
                pet.setId(rs.getInt("id"));
                pet.setNome(rs.getString("nome"));
                pet.setEspecie(rs.getString("especie"));
                pet.setRaca(rs.getString("raca"));
                pet.setIdade(rs.getInt("idade"));
                        
                Cliente tutor = new Cliente();
                tutor.setId(rs.getInt("tutor_id"));
                pet.setTutor(tutor);
        
                pets.add(pet);
            }
        }
        return pets;
    }
        
    public static void update(Connection conn, Pet pet) throws SQLException {
        String sql = "UPDATE Pet SET nome = ?, especie = ?, raca = ?, idade = ?, tutor_id = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pet.getNome());
            pstmt.setString(2, pet.getEspecie());
            pstmt.setString(3, pet.getRaca());
            pstmt.setInt(4, pet.getIdade());
            pstmt.setInt(5, pet.getTutor().getId());
            pstmt.setInt(6, pet.getId());
            pstmt.executeUpdate();
            System.out.println("Dados do pet atualizados com sucesso.");
        }
    }
        
    public static void delete(Connection conn, int petId) throws SQLException {
        String sql = "DELETE FROM Pet WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, petId);
            pstmt.executeUpdate();
            System.out.println("Pet excluído com sucesso.");
        }
    }
}
        