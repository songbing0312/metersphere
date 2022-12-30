package io.metersphere.listener;

import io.metersphere.commons.constants.KafkaTopicConstants;
import io.metersphere.commons.utils.LogUtil;
import io.metersphere.dto.ProjectConfig;
import io.metersphere.plan.service.TestPlanReportService;
import io.metersphere.service.BaseProjectApplicationService;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class CleanUpReportListener {
    private static final String UNIT_DAY = "D";
    private static final String UNIT_MONTH = "M";
    private static final String UNIT_YEAR = "Y";
    LocalDate localDate;
    public static final String CONSUME_ID = "clean-up-test-plan-report";

    @Resource
    private BaseProjectApplicationService baseProjectApplicationService;
    @Resource
    private TestPlanReportService testPlanReportService;

    @KafkaListener(id = CONSUME_ID, topics = KafkaTopicConstants.CLEAN_UP_REPORT_SCHEDULE, groupId = "${spring.application.name}")
    public void consume(ConsumerRecord<?, String> record) {
        String projectId = record.value();
        localDate = LocalDate.now();
        ProjectConfig config = baseProjectApplicationService.getProjectConfig(projectId);
        LogUtil.info("track service consume clean_up message, clean able: " + config.getCleanTrackReport());
        if (BooleanUtils.isTrue(config.getCleanTrackReport())) {
            this.cleanUpReport(projectId, config.getCleanTrackReportExpr());
        }
    }

    private void cleanUpReport(String projectId, String expr) {
        LogUtil.info("clean up track report start.");
        long time = getCleanDate(expr);
        if (time == 0) {
            return;
        }
        testPlanReportService.cleanUpReport(time, projectId);
        LogUtil.info("clean up track report end.");
    }

    private long getCleanDate(String expr) {
        LocalDate date = null;
        long timeMills = 0;
        if (StringUtils.isNotBlank(expr)) {
            try {
                String unit = expr.substring(expr.length() - 1);
                int quantity = Integer.parseInt(expr.substring(0, expr.length() - 1));
                if (StringUtils.equals(unit, UNIT_DAY)) {
                    date = localDate.minusDays(quantity);
                } else if (StringUtils.equals(unit, UNIT_MONTH)) {
                    date = localDate.minusMonths(quantity);
                } else if (StringUtils.equals(unit, UNIT_YEAR)) {
                    date = localDate.minusYears(quantity);
                } else {
                    LogUtil.error("clean up expr parse error. expr : " + expr);
                }
            } catch (Exception e) {
                LogUtil.error(e.getMessage(), e);
                LogUtil.error("clean up job. get clean date error.");
            }
        }
        if (date != null) {
            timeMills = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
        }
        return timeMills;
    }
}
