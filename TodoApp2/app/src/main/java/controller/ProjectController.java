/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.Connection;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;
import model.Project;
import util.ConnectionFactory;


public class ProjectController {
  
    public void save(Project project){
        
     String sql = "INSERT INTO Projects(name,"
             + "description, "
             + "createdAt, "
             + "updatedAt) "
             + " VALUES (?,?,?,?) ";
        
     Connection connection = null;
     PreparedStatement statement = null;
        
        try {
            //Cria a conexao com o banco de dados 
            connection = ConnectionFactory.getConnection();
            // Cria um preparedStatment, classe usada para executar a query
            statement = connection.prepareStatement(sql); 
                       
            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
           
            //Executa a sql para inserçao dos dados
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa ", ex);
            
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }
    
    
    public void update(Project project){

        String sql = "UPDATE projects SET "
                +"name = ?, "
                +"description = ?, "
                +"createdAt = ?, "
                +"updatedAt = ? "
                +"WHERE id = ?";

            Connection connection = null;
            PreparedStatement statement = null;

            try {
            //estabelecendo a conexao com o bandco de dados
            connection = ConnectionFactory.getConnection();
            // preparando a query
            statement = connection.prepareStatement(sql);
            //setando os valores do statement

            statement.setString(1, project.getName());
            statement.setString(2, project.getDescription());
            statement.setDate(3, new Date(project.getCreatedAt().getTime()));
            statement.setDate(4, new Date(project.getUpdatedAt().getTime()));
            statement.setInt(5, project.getId());
           // Executando a query 
            statement.execute();

            } catch (SQLException ex) {
                 throw new RuntimeException("Erro ao atualizar tarefa" , ex);
             } finally {
                    ConnectionFactory.closeConnection(connection, statement);
                }
    }
    
    
    public List<Project> getAll(){

     String sql = "SELECT * FROM projects";
     
     List<Project> projects = new ArrayList<>();
     
      Connection connection = null;
      PreparedStatement statement = null;
      //classe que vai rescuperar os dados do banco de dados 
      ResultSet resultSet = null;
      
      try {
            //estabelecendo a conexao com o bandco de dados
            connection = ConnectionFactory.getConnection();
            // preparando a query
            statement = connection.prepareStatement(sql);
           
            resultSet = statement.executeQuery();
            
            
             while (resultSet.next()){
            
             Project project = new Project();
             
              project.setId(resultSet.getInt("id"));
              project.setName(resultSet.getString("name"));
              project.setDescription(resultSet.getString("description"));
              project.setCreatedAt(resultSet.getDate("createdAt"));
              project.setCreatedAt(resultSet.getDate("updatedAt"));
              
              projects.add(project);
          }
      }catch (SQLException ex) {
                 throw new RuntimeException("Erro ao listar tarefa" , ex);
             } finally {
                    ConnectionFactory.closeConnection(connection, statement, resultSet);
                }
      return projects;
    }
    
    
 public void removeById(int idProject) {
        
        String sql = "DELETE FROM projects WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Cria a conexao com o banco de dados 
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql); // Objeto prepareStatement: prepara o comando a ser executado no banco 
            statement.setInt(1, idProject); // substitui o 1 pelo ? da variavel SQL da linha 31
            // Executa a query
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar tarefa " , ex);
             } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
    
    
    
 }
