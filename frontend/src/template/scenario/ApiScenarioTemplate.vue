<template>
  <edit-api-scenario
    :currentScenario="currentScenario"
    :custom-num="customNum" :moduleOptions="moduleOptions" :scenarioId="scenarioId" ref="autoScenarioConfig"/>
</template>

<script>
  import EditApiScenario from "@/business/components/api/automation/scenario/EditApiScenario";
  import {getScenarioId} from "@/common/js/utils";


export default {
  name: "ApiScenarioTemplate",
  components: {EditApiScenario},
  data() {
    return {
      currentScenario: {},
      customNum: false,
      scenarioId: String,
      moduleOptions: [],
    };
  },
  created() {
    //从url地址取得scenarioId，并传递给EditApiScenario页面
    this.scenarioId = getScenarioId();
  },
};
</script>

<style scoped>
</style>
