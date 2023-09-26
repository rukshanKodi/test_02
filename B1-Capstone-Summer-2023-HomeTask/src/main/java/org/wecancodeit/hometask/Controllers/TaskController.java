package org.wecancodeit.hometask.Controllers;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.hometask.Models.HouseholdMember;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Repositories.HouseholdMemberRepository;
import org.wecancodeit.hometask.Repositories.TaskRepository;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.TaskService;

import jakarta.annotation.Resource;

@Controller
public class TaskController {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private TaskService taskService;
    @Resource
    private HouseholdService HHService;


    @GetMapping("/createTask/{familyMemberId}")
    public String createTask(@PathVariable Long familyMemberId, Model model) {
        // add Get Family Member

        model.addAttribute("task", null);
        return "createTaskView";
    }

    @GetMapping({ "/task/{TaskID}" })
    public Task retrieveTaskById(@PathVariable Long TaskID) throws Exception {
        return taskService.retrieveTaskById(TaskID);
    }

    /*
     * Save task from user input
     */
    @PostMapping("/saveTask")
    public String saveTask(@ModelAttribute("task") Task task) {
        return "redirect:/task";
    }

    @GetMapping("/create-task/{username}")
    public String redirectCreateTask() {
        return "AssignTaskView";
    }

    @GetMapping("/createTask/{durationId}")
    public String createDuration(@PathVariable Long durationId, Model model) {
        // add Get Family Member
        model.addAttribute("duration", null);
        return "createDurationView";
    }

    @PostMapping("/saveDuration")
    public String saveDuration(@ModelAttribute("duration") Task task) {
        return "redirect:/task";
    }

    
    @RequestMapping({"/taskInfo/"})
    //Will display detailed information about a task
    public String displayTaskDetails(Task info) {
        return "TaskInfoView";
    }
//RK
    @GetMapping("/add_task/{username}")
     public String ShowHousehildwiseMembers(@PathVariable String username,Model model) throws Exception {
//List<HouseholdMember>HouseholdMemberList = HHMrepo.findAll();
model.addAttribute("household", HHService.retrieveHouseholdByName(username));
        return "AddTask";
}
}