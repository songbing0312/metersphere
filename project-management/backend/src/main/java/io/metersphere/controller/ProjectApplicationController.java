package io.metersphere.controller;

import io.metersphere.base.domain.ProjectApplication;
import io.metersphere.commons.constants.OperLogConstants;
import io.metersphere.commons.constants.OperLogModule;
import io.metersphere.dto.ProjectConfig;
import io.metersphere.log.annotation.MsAuditLog;
import io.metersphere.request.ProjectApplicationRequest;
import io.metersphere.service.ProjectApplicationService;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping(value = "/project_application")
public class ProjectApplicationController {
    @Resource
    private ProjectApplicationService projectApplicationService;

    @PostMapping("/update")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#projectApplication)", content = "#msClass.getLogDetails(#projectApplication)", msClass = ProjectApplicationService.class)
    public void updateProject(@RequestBody ProjectApplication projectApplication) {
        projectApplicationService.updateProjectApplication(projectApplication);
    }

    @PostMapping("/update/batch")
    @MsAuditLog(module = OperLogModule.PROJECT_PROJECT_MANAGER, type = OperLogConstants.UPDATE, beforeEvent = "#msClass.getLogDetails(#projectApplication)", content = "#msClass.getLogDetails(#projectApplication)", msClass = ProjectApplicationService.class)
    public void updateProjectConfigBatch(@RequestBody ProjectApplicationRequest request) {
        projectApplicationService.updateProjectConfigBatch(request);
    }

}
