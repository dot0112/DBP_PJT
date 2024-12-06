package DBP_equipmentRentalService.main.controller;

import DBP_equipmentRentalService.main.domain.RepairRecord;
import DBP_equipmentRentalService.main.domain.RepairRequest;
import DBP_equipmentRentalService.main.service.RepairRecordService;
import DBP_equipmentRentalService.main.service.RepairRequestService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class manageRepairController {
    private final RepairRequestService repairRequestService;
    private final RepairRecordService repairRecordService;

    @Autowired
    public manageRepairController(RepairRequestService repairRequestService, RepairRecordService repairRecordService){
        this.repairRequestService = repairRequestService;
        this.repairRecordService = repairRecordService;
    }

    @GetMapping("/manageRepair")
    public String manageRepair(Model model, HttpSession session){
        List<RepairRequest> allRepairRequest = repairRequestService.findAll();

        Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        String role = (String) session.getAttribute("role");

        if (isLoggedIn == null){
           isLoggedIn = false;
        }

        model.addAttribute("isLoggedIn", isLoggedIn);
        model.addAttribute("role", role);
        model.addAttribute("contentFragment", "manageRepair");
        model.addAttribute("repairList", allRepairRequest);
        return "layout";
    }

    @GetMapping("/manageRepair/{repairRequestId}")
    public String managingRequest(@PathVariable String repairRequestId, Model model, HttpSession session){
        List<RepairRequest> repairRequestList = repairRequestService.findRequestByRepairRequest(repairRequestId);
        RepairRequest repairRequest = repairRequestList.get(0);

        session.setAttribute("repairRequestId", repairRequest.getRepairRequestId());

        if(repairRequest.getIsRepaired() == 0)
            return "redirect:/repair";
        else
            return "redirect:/repairRecord";
    }

    @GetMapping("/repair")
    public String repair(Model model, HttpSession session) {
        List<RepairRequest> repairRequestList = repairRequestService.findRequestByRepairRequest((String) session.getAttribute("repairRequestId"));
        RepairRequest repairRequest = repairRequestList.get(0);

        model.addAttribute("repairItemId", repairRequest.getItemId());
        model.addAttribute("repairItemName", repairRequest.getItemName());

        return "repair";
    }

    @PostMapping("/repair")
    public String repair(@RequestParam String repairMethod, @RequestParam Integer repairCost, HttpSession session){
        List<RepairRequest> repairRequestList = repairRequestService.findRequestByRepairRequest((String) session.getAttribute("repairRequestId"));
        RepairRequest repairRequest = repairRequestList.get(0);
        RepairRecord repairRecord = new RepairRecord();

        repairRecord.setItemId(repairRequest.getItemId());
        repairRecord.setRepairDate(LocalDate.now());
        repairRecord.setRepairDesc(repairMethod);
        repairRecord.setRepairCost(repairCost);

        repairRecordService.join(repairRecord);
        return "redirect:/";
    }

    @GetMapping("/repairRecord")
    public String repairRecord() {return "repairRecord";}
}
