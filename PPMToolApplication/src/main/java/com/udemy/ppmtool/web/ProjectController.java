package com.udemy.ppmtool.web;

import com.udemy.ppmtool.domain.Project;
import com.udemy.ppmtool.service.MapValidationErrorService;
import com.udemy.ppmtool.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

        @Autowired
        private ProjectService projectService;

        @Autowired
        private MapValidationErrorService mapValidationErrorService;

        @PostMapping("")
        public ResponseEntity<?> createdNewProject(@Valid @RequestBody Project project,BindingResult result){

        ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationError(result);
            if (errorMap!=null) return errorMap;
             Project project1=  projectService.saveOrUpdateProject(project);
             return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
        }
        @GetMapping("/{projectId}")
        public ResponseEntity<?> getProjectById(@PathVariable String projectId){
            Project project= projectService.findProjectByIdentifier(projectId);
            return new ResponseEntity<Project>(project,HttpStatus.OK);
        }
        @GetMapping("/all")
        public Iterable<Project> getAllProjects(){
            return projectService.findAllProjects();
        }
        @DeleteMapping("/{projectId}")
        public ResponseEntity<?> deleteProjectByIdentifier(@PathVariable String projectId){
            projectService.deleteProjectByIdentifier(projectId);
            return new ResponseEntity<String>("Project with Id '"+projectId+"'was deleted",HttpStatus.OK);
        }
}

