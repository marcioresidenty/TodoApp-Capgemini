/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import controller.ProjectController;
import java.util.Date;
import model.Project;

/**
 *
 * @author 1310227
 */
public class TesteDeSalvar {

   
    
    public static void main(String[] args) {
       
        ProjectController  projectController = new ProjectController();
        
        Project project = new Project();
        
        project.setName("Projeto teste2");
        project.setDescription("teste2 OK" );
       
            projectController.save(project);
        
      
            
    }
    
}
