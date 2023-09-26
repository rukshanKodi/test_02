package org.wecancodeit.hometask.Controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wecancodeit.hometask.Models.Household;
import org.wecancodeit.hometask.Models.HouseholdMember;
import org.wecancodeit.hometask.Models.Task;
import org.wecancodeit.hometask.Repositories.HouseholdMemberRepository;
import org.wecancodeit.hometask.Repositories.TaskRepository;
import org.wecancodeit.hometask.Services.HouseholdService;
import org.wecancodeit.hometask.Services.TaskService;

import jakarta.annotation.Resource;
import org.wecancodeit.hometask.Services.UserService;

@Controller
public class TaskController {

    @Resource
    private TaskRepository taskRepository;
    @Resource
    private TaskService taskService;
    @Resource
    private HouseholdService HHService;
    @Resource
    private UserService userService;


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
    @GetMapping("/add_task")
    public String ShowHousehildwiseMembers(Model model, HttpServletRequest request) throws Exception {
        long userId = userService.getUserId(request);
        if(userId==0){
            throw new Exception("Not logged in");
        }
        Household household = HHService.retrieveHouseholdByUserId(userId);

        List<Map<String, Object>> members = new ArrayList<>();
        for (HouseholdMember member : household.getMembers()) {
            Map<String, Object> memberInfo = new HashMap<>();
            memberInfo.put("householdMemberId", member.getHouseholdMemberId());
            memberInfo.put("name", member.getName()); // Replace with the actual method to get the age
            members.add(memberInfo);
        }

        model.addAttribute("members", members);
        return "AddTask";
    }
}