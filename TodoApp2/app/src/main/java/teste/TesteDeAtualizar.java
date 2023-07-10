/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import controller.ProjectController;
import model.Project;

/**
 *
 * @author 1310227
 */
public class TesteDeAtualizar {
    public static void main(String[] args) {
        
         ProjectController  projectController = new ProjectController();
        
        Project project = new Project();
        
        project.setId(1);
        project.setName("Projeto  ");
        project.setDescription("teste Atualizado OK" );
       
            projectController.update(project);
    }
}
