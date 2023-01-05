<template>
  <api-case-list :currentApi="selectCase" :caseId="caseId" ref="caseList"/>
</template>

<script>
import ApiCaseList from "@/business/components/api/definition/components/case/ApiCaseList";
import {getCaseId} from "@/common/js/utils";



export default {
  name: "CaseEditTemplate",
  components: {ApiCaseList},
  data() {
    return {
      selectCase: {},
      caseId: String,
    };
  },
  created() {
    //从url地址取得caseId，并传递给ApiCaseList页面
    this.caseId = getCaseId();
  },
};
</script>

<style scoped>
</style>
