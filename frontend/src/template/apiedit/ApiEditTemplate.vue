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
    v-if="isShow"
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
        isShow:true
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
          this.reload();
        });
      });
    },
    methods:{
      reload() {
        this.isShow = false
        this.$nextTick(() => {
          this.isShow = true
        })
      },
    }
  };
</script>

<style scoped>
</style>
