package com.udemy.ppmtool.service;

import com.udemy.ppmtool.domain.Project;
import com.udemy.ppmtool.exceptions.ProjectIdException;
import com.udemy.ppmtool.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.plaf.synth.SynthOptionPaneUI;

@Service
public class ProjectService {
@Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project) {
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            //System.out.println("Project Identifier : "+project.getProjectIdentifier());
            return projectRepository.save(project);
        } catch (Exception e) {
            throw new ProjectIdException("Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists.");
        }
    }
    public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project==null){
            throw new ProjectIdException("Project Id '" + projectId.toUpperCase() + "' does not exist.");
        }
        return project;
    }
    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }
    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project==null){
            throw new ProjectIdException("Project Id can not be deleted '" + projectId+ "' This project does not exist.");
        }
         projectRepository.delete(project);
    }

}


