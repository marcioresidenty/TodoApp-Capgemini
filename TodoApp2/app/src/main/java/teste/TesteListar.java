/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package teste;

import controller.ProjectController;
import java.util.List;
import model.Project;

/**
 *
 * @author 1310227
 */
public class TesteListar {
    public static void main(String[] args) {
        
        
        ProjectController  projectController = new ProjectController();
        
        Project project = new Project();
        
        List<Project> projects = projectController.getAll();
        System.out.println("Total de projetos = "+ projects.size());
    }
}
