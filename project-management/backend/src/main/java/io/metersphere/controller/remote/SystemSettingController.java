package io.metersphere.controller.remote;

import io.metersphere.remote.service.PlatformPluginService;
import io.metersphere.service.remote.BaseSystemSettingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = {
        "/platform/plugin",
})
public class SystemSettingController {
    @Resource
    BaseSystemSettingService baseSystemSettingService;
    @Resource
    PlatformPluginService platformPluginService;

    @PostMapping("/**")
    public Object list(HttpServletRequest request, @RequestBody Object param) {
        return baseSystemSettingService.post(request, param);
    }

    @GetMapping("/**")
    public Object get(HttpServletRequest request) {
        return baseSystemSettingService.get(request);
    }

    @GetMapping("/resource/{pluginId}")
    public void getPluginResource(@PathVariable("pluginId") String pluginId, @RequestParam("fileName") String fileName, HttpServletResponse response) {
        platformPluginService.getPluginResource(pluginId, fileName, response);
    }
}
