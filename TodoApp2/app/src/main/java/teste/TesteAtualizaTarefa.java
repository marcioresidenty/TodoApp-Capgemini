/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import controller.TaskController;
import java.util.Date;
import model.Task;

/**
 *
 * @author 1310227
 */
public class TesteAtualizaTarefa {
    public static void main(String[] args) {
        
        TaskController  taskController = new TaskController();
        
        Task task = new Task();
        
         task.setIdProject(1);
         task.setId(14);
         task.setName("vazio");
         task.setDescription("teste Atualizado " );
         task.setDeadline(new Date(02/3/2020));
       
            taskController.update(task);
    }
}

