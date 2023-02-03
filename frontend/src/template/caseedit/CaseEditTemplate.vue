<template>
  <api-case-list :currentApi="selectCase" :caseId="caseId" :apiId="apiId"
                 :action="action" :addCaseModuleId="addCaseModuleId" ref="caseList"/>
</template>

<script>
import ApiCaseList from "@/business/components/api/definition/components/case/ApiCaseList";
import {getCaseId} from "@/common/js/utils";
import {getAction} from "@/common/js/utils";
import {getCaseModuleId} from "@/common/js/utils";
import {getApiId} from "@/common/js/utils";
import {registerRequestHeaders} from "@/common/js/ajax";





export default {
  name: "CaseEditTemplate",
  components: {ApiCaseList},
  data() {
    return {
      selectCase: {},
      caseId: String,
    };
  },
  beforeCreate() {
    registerRequestHeaders();
  },
  created() {
    //从url地址取得caseId，并传递给ApiCaseList页面
    this.caseId = getCaseId();
    this.action = getAction();
    this.addCaseModuleId = getCaseModuleId();
    this.apiId = getApiId();
  },
};
</script>

<style scoped>
</style>
