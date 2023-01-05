<template>
  <edit-complete-container
    :apiId="apiId"
    :syncTabs="syncTabs"
    :current-api="currentApi"
    :project-id="projectId"
    :currentProtocol="currentProtocol"
    :moduleOptions="moduleOptions"
    :activeDom="activeTab"
    ref="apiConfig"
  />
</template>

<script>
  import EditCompleteContainer from "@/business/components/api/definition/components/EditCompleteContainer";
  import {getApiId} from "@/common/js/utils";



  export default {
    name: "ApiEditTemplate",
    components: {EditCompleteContainer},
    data() {
      return {
        apiId:String,
        currentApi: {},
        projectId:String,
        currentProtocol:String,
        moduleOptions:[],
        syncTabs: [],
        activeTab: String,
      };
    },
    created() {
      //从url地址取得apiId
      this.apiId = getApiId();

      this.$get('/api/definition/get/' + this.apiId, (response) => {
        this.currentApi = response.data;
        this.$get('/api/module/getApiModuleById/' + this.currentApi.moduleId, (response2) => {
          this.moduleOptions = response2.data;

          this.currentProtocol = response.data.protocol;
          this.projectId = response.data.projectId;
          this.activeTab = "api";

          this.currentApi.request = JSON.parse(this.currentApi.request);

          console.log("ApiEditTemplate>>>>>created>>>>>>")
          console.log(this.apiId)
          console.log(this.currentApi)
          console.log(this.currentProtocol)
          console.log(this.projectId)
          console.log(this.moduleOptions)
          console.log(this.activeTab)

        });

      });
    },
  };
</script>

<style scoped>
</style>
