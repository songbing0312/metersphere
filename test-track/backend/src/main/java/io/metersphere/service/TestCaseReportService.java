package io.metersphere.service;

import io.metersphere.base.domain.TestCaseReport;
import io.metersphere.base.domain.TestCaseReportExample;
import io.metersphere.base.domain.TestCaseReportTemplate;
import io.metersphere.base.domain.TestPlanWithBLOBs;
import io.metersphere.base.mapper.TestCaseReportMapper;
import io.metersphere.base.mapper.TestCaseReportTemplateMapper;
import io.metersphere.base.mapper.TestPlanMapper;
import io.metersphere.commons.utils.BeanUtils;
import io.metersphere.commons.utils.JSON;
import io.metersphere.commons.utils.SessionUtils;
import io.metersphere.log.utils.ReflexObjectUtil;
import io.metersphere.log.vo.DetailColumn;
import io.metersphere.log.vo.OperatingLogDetails;
import io.metersphere.log.vo.track.TestPlanReference;
import io.metersphere.request.testCaseReport.CreateReportRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
public class TestCaseReportService {

    @Resource
    TestCaseReportMapper testCaseReportMapper;

    @Resource
    TestPlanMapper testPlanMapper;

    @Resource
    TestCaseReportTemplateMapper testCaseReportTemplateMapper;

    public List<TestCaseReport> listTestCaseReport(TestCaseReport request) {
        TestCaseReportExample example = new TestCaseReportExample();
        if (StringUtils.isNotBlank(request.getName())) {
            example.createCriteria().andNameEqualTo(request.getName());
        }
        return testCaseReportMapper.selectByExample(example);
    }

    public TestCaseReport getTestCaseReport(String id) {
        return testCaseReportMapper.selectByPrimaryKey(id);
    }

    public void addTestCaseReport(TestCaseReport testCaseReport) {
        testCaseReport.setId(UUID.randomUUID().toString());
        testCaseReportMapper.insert(testCaseReport);
    }

    public void editTestCaseReport(TestCaseReport TestCaseReport) {
        testCaseReportMapper.updateByPrimaryKeyWithBLOBs(TestCaseReport);
    }

    public int deleteTestCaseReport(String id) {
        return testCaseReportMapper.deleteByPrimaryKey(id);
    }

    public String addTestCaseReportByTemplateId(CreateReportRequest request) {
        TestCaseReportTemplate template = testCaseReportTemplateMapper.selectByPrimaryKey(request.getTemplateId());
        TestCaseReport report = new TestCaseReport();
        BeanUtils.copyBean(report, template);
        TestPlanWithBLOBs testPlan = testPlanMapper.selectByPrimaryKey(request.getPlanId());
        report.setName(testPlan.getName());
        report.setId(request.getId());
        report.setCreateUser(SessionUtils.getUserId());
        testCaseReportMapper.insert(report);
        testPlan.setReportId(report.getId());
        testPlanMapper.updateByPrimaryKeySelective(testPlan);
        return report.getId();
    }

    public String getLogDetails(String id) {
        TestCaseReport report = testCaseReportMapper.selectByPrimaryKey(id);
        if (report != null) {
            List<DetailColumn> columns = ReflexObjectUtil.getColumns(report, TestPlanReference.reportColumns);
            OperatingLogDetails details = new OperatingLogDetails(JSON.toJSONString(report.getId()), null, report.getName(), report.getCreateUser(), columns);
            return JSON.toJSONString(details);
        }
        return null;
    }
}
