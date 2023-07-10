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
import model.Task;
import util.ConnectionFactory;

/**
 *
 * @author 1310227
 */
public class TaskController {
    
    public void save(Task task){
        
     String sql = "INSERT INTO tasks ("
             + "idProject, "
             + "name, "
             + "description, "
             + "completed, "
             + "notes, "
             + "deadline, "
             + "createdAt, "
             + "updatedAt ) VALUES (?,?,?,?,?,?,?,?) ";
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isIsCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa "
            + ex.getMessage(), ex);
            
        } finally {
            ConnectionFactory.closeConnection(connection, statement);
          
        }
        
    }
 
public void update(Task task){
        
    String sql = "UPDATE tasks SET "
            + "IdProject = ?, "
            + "name = ?, "
            + "description = ?, "
            + "notes = ?, "
            + "completed = ?, "
            + "deadline = ?, "
            + "createdAt = ?, "
            + "updatedAt = ? "
            + "WHERE id = ?";
       
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
        //estabelecendo a conexao com o bandco de dados
        connection = ConnectionFactory.getConnection();
        // preparando a query
        statement = connection.prepareStatement(sql);
        //setando os valores do statement
        statement.setInt(1, task.getIdProject());
        statement.setString(2, task.getName());
        statement.setString(3, task.getDescription());
        statement.setString(4, task.getNotes());
        statement.setBoolean(5, task.isIsCompleted());
        statement.setDate(6, new Date(task.getDeadline().getTime()));
        statement.setDate(7, new Date(task.getCreatedAt().getTime()));
        statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
        statement.setInt(9, task.getId());
       // Executando a query 
        statement.execute();
        
    } catch (Exception ex) {
         throw new RuntimeException("Erro ao atualizar tarefa" +ex.getMessage(), ex);
    }
            
}
 
 public void removeById(int taskId) {
        
        String sql = "DELETE FROM tasks WHERE id = ?";
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try {
            // Cria a conexao com o banco de dados 
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql); // Objeto prepareStatement: prepara o comando a ser executado no banco 
            statement.setInt(1, taskId); // substitui o 1 pelo ? da variavel SQL da linha 31
            // Executa a query
            statement.execute();
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar tarefa" + ex.getMessage(), ex);
             } finally {
            ConnectionFactory.closeConnection(connection);
        }
    }
 
 
  public List<Task> getAll(int idProject){
        
      
      String sql = "SELECT *FROM tasks WHERE idProject = ?";
      
      Connection connection = null;
      PreparedStatement statement = null;
      ResultSet resultSet = null;
      
      //Lista de tarefas que sera devolvida quando a chamada do metodo acontecer
      List<Task> tasks = new ArrayList<Task>();
      
      try {
          // cria a conexao com o banco de dados 
          connection = ConnectionFactory.getConnection();
          // prepara o statement
          statement = connection.prepareStatement(sql);
          //setando o valor que corresponde o filtro de busca
          statement.setInt(1, idProject);
          //Valor retornado pela execusao da query
          resultSet = statement.executeQuery();
          
          //pecorrer enquanto houver valores a serem pecorridos no resultset
          while (resultSet.next()){
              Task task = new Task();
              task.setId(resultSet.getInt("id"));
              task.setIdProject(resultSet.getInt("idProject"));
              task.setName(resultSet.getString("name"));
              task.setDescription(resultSet.getString("description"));
              task.setNotes(resultSet.getString("notes"));
              task.setIsCompleted(resultSet.getBoolean("completed"));
              task.setDeadline(resultSet.getDate("deadline"));
              task.setCreatedAt(resultSet.getDate("createdAt"));
              task.setUpdatedAt(resultSet.getDate("updatedAt"));
              
              tasks.add(task);
          }
      } catch (Exception ex) {
        throw new RuntimeException("Erro ao adicionar tarefa" + ex.getMessage(), ex);
      } finally {
          ConnectionFactory.closeConnection(connection, statement, resultSet);
      }
 
   // Lista de tarefa que foi criada e carregada do banco de dados
    return tasks;
  }
}

