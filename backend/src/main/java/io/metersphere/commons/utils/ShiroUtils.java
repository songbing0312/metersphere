package io.metersphere.commons.utils;

import io.metersphere.security.CustomSessionIdGenerator;
import io.metersphere.security.CustomSessionManager;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import java.util.Map;

public class ShiroUtils {

    public static void loadBaseFilterChain(Map<String, String> filterChainDefinitionMap) {

        filterChainDefinitionMap.put("/scenario-test", "anon");
        filterChainDefinitionMap.put("/api/automation/get/*", "anon");
        filterChainDefinitionMap.put("/user/project/member/tester/list", "anon");
        filterChainDefinitionMap.put("/project/getOwnerProjects", "anon");
        filterChainDefinitionMap.put("/plugin/list", "anon");
        filterChainDefinitionMap.put("/environment/group/get/all", "anon");
        filterChainDefinitionMap.put("/environment/group/get/option", "anon");
        filterChainDefinitionMap.put("/workspace/list/userworkspace/*", "anon");
        filterChainDefinitionMap.put("/api/automation/getApiScenario/*", "anon");
        filterChainDefinitionMap.put("/api/automation/follow/*", "anon");
        filterChainDefinitionMap.put("/api/environment/get/*", "anon");
        filterChainDefinitionMap.put("/api/environment/list/*", "anon");
        filterChainDefinitionMap.put("/api/automation/*", "anon");
        filterChainDefinitionMap.put("/api/automation/versions/*", "anon");
        filterChainDefinitionMap.put("/project/getOwnerProjectIds/", "anon");
        filterChainDefinitionMap.put("/api/definition/run/debug", "anon");
        filterChainDefinitionMap.put("/task/center/scenario/*", "anon");
        filterChainDefinitionMap.put("/api/automation/run/debug", "anon");
        filterChainDefinitionMap.put("/api/automation/module/getApiScenarioModuleById/*", "anon");
        filterChainDefinitionMap.put("/api/automation/update", "anon");
        filterChainDefinitionMap.put("/project/list/related", "anon");
        filterChainDefinitionMap.put("/api/definition/list/**", "anon");
        filterChainDefinitionMap.put("/api/module/list/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/list/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/get/caseBLOBs/request", "anon");
        filterChainDefinitionMap.put("/api/automation/module/list/*", "anon");
        filterChainDefinitionMap.put("/project_application/get/config/**", "anon");
        filterChainDefinitionMap.put("/custom/func/list/**", "anon");
        filterChainDefinitionMap.put("/api/automation/list/**", "anon");
        filterChainDefinitionMap.put("/project/get/**", "anon");
        filterChainDefinitionMap.put("/api/module/getUserDefaultApiType", "anon");


        filterChainDefinitionMap.put("/caseEdit", "anon");
        filterChainDefinitionMap.put("/api/definition/get/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/findById/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/follow/**", "anon");
        filterChainDefinitionMap.put("/api/definition/report/getReport/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/update", "anon");
        filterChainDefinitionMap.put("/api/testcase/create", "anon");
        filterChainDefinitionMap.put("/api/definition/run", "anon");
        filterChainDefinitionMap.put("/api/testcase/batch/editByParam", "anon");
        filterChainDefinitionMap.put("/task/center/case/**", "anon");
        filterChainDefinitionMap.put("/api/genPerformanceTestXml", "anon");


        filterChainDefinitionMap.put("/apiEdit", "anon");
        filterChainDefinitionMap.put("/mockConfig/genMockConfig", "anon");
        filterChainDefinitionMap.put("/api/definition/follow/**", "anon");
        filterChainDefinitionMap.put("/api/definition/relationship/count/**", "anon");
        filterChainDefinitionMap.put("/api/definition/update", "anon");
        filterChainDefinitionMap.put("/api/definition/create", "anon");
        filterChainDefinitionMap.put("/api/module/getApiModuleById/**", "anon");
        filterChainDefinitionMap.put("/api/definition/getMockEnvironment/**", "anon");
        filterChainDefinitionMap.put("/api/environment/getTcpMockInfo/**", "anon");
        filterChainDefinitionMap.put("/api/testcase/checkDeleteDatas/", "anon");
        filterChainDefinitionMap.put("/api/testcase/deleteToGc/**", "anon");



        filterChainDefinitionMap.put("/resource/md/get/**", "anon");
        filterChainDefinitionMap.put("/*.worker.js", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/signin", "anon");
        filterChainDefinitionMap.put("/ldap/signin", "anon");
        filterChainDefinitionMap.put("/ldap/open", "anon");
        filterChainDefinitionMap.put("/signout", "anon");
        filterChainDefinitionMap.put("/isLogin", "anon");
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/img/**", "anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/display/info", "anon");
        filterChainDefinitionMap.put("/favicon.ico", "anon");
        filterChainDefinitionMap.put("/display/file/**", "anon");
        filterChainDefinitionMap.put("/jmeter/download/**", "anon");
        filterChainDefinitionMap.put("/jmeter/ping", "anon");
        filterChainDefinitionMap.put("/jmeter/ready/**", "anon");
        filterChainDefinitionMap.put("/authsource/list/allenable", "anon");
        filterChainDefinitionMap.put("/sso/signin", "anon");
        filterChainDefinitionMap.put("/sso/callback/**", "anon");
        filterChainDefinitionMap.put("/license/valid", "anon");
        filterChainDefinitionMap.put("/api/jmeter/download", "anon");
        filterChainDefinitionMap.put("/api/jmeter/download/files", "anon");
        filterChainDefinitionMap.put("/api/jmeter/download/jar", "anon");
        filterChainDefinitionMap.put("/api/jmeter/download/plug/jar", "anon");
        filterChainDefinitionMap.put("/api/automation/stop/batch", "anon");

        // for swagger
        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/swagger-ui/**", "anon");
        filterChainDefinitionMap.put("/v3/api-docs/**", "anon");

        filterChainDefinitionMap.put("/403", "anon");
        filterChainDefinitionMap.put("/anonymous/**", "anon");

        //分享相关接口
        filterChainDefinitionMap.put("/share/info/generateShareInfoWithExpired", "anon");
        filterChainDefinitionMap.put("/share/info/selectApiInfoByParam", "anon");
        filterChainDefinitionMap.put("/share/info/selectHistoryReportById", "anon");
        filterChainDefinitionMap.put("/share/get/**", "anon");
        filterChainDefinitionMap.put("/share/info", "apikey, csrf, authc"); // 需要认证
        filterChainDefinitionMap.put("/document/**", "anon");
        filterChainDefinitionMap.put("/echartPic/**", "anon");
        filterChainDefinitionMap.put("/share/**", "anon");
        filterChainDefinitionMap.put("/sharePlanReport", "anon");
        filterChainDefinitionMap.put("/sharePerformanceReport", "anon");
        filterChainDefinitionMap.put("/shareApiReport", "anon");

        filterChainDefinitionMap.put("/system/theme", "anon");
        filterChainDefinitionMap.put("/system/save/baseurl/**", "anon");
        filterChainDefinitionMap.put("/system/timeout", "anon");

        filterChainDefinitionMap.put("/v1/catalog/**", "anon");
        filterChainDefinitionMap.put("/v1/agent/**", "anon");
        filterChainDefinitionMap.put("/v1/health/**", "anon");
        //mock接口
        filterChainDefinitionMap.put("/mock/**", "anon");
        filterChainDefinitionMap.put("/ws/**", "anon");
    }

    public static void ignoreCsrfFilter(Map<String, String> filterChainDefinitionMap) {
        filterChainDefinitionMap.put("/", "apikey, authc"); // 跳转到 / 不用校验 csrf
        filterChainDefinitionMap.put("/language", "apikey, authc");// 跳转到 /language 不用校验 csrf
        filterChainDefinitionMap.put("/test/case/file/preview/**", "apikey, authc"); // 预览测试用例附件 不用校验 csrf
        filterChainDefinitionMap.put("/mock", "apikey, authc"); // 跳转到 /mock接口 不用校验 csrf
    }

    public static Cookie getSessionIdCookie() {
        SimpleCookie sessionIdCookie = new SimpleCookie();
        sessionIdCookie.setPath("/");
        sessionIdCookie.setName("MS_SESSION_ID");
        return sessionIdCookie;
    }

    public static SessionManager getSessionManager(Long sessionTimeout, CacheManager cacheManager) {
        DefaultWebSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setSessionValidationSchedulerEnabled(true);
        sessionManager.setSessionIdCookie(ShiroUtils.getSessionIdCookie());
        sessionManager.setGlobalSessionTimeout(sessionTimeout * 1000);// 超时时间ms
        sessionManager.setCacheManager(cacheManager);
        AbstractSessionDAO sessionDAO = (AbstractSessionDAO) sessionManager.getSessionDAO();
        sessionDAO.setSessionIdGenerator(new CustomSessionIdGenerator());

        //sessionManager.setSessionIdCookieEnabled(true);
        return sessionManager;
    }
}
