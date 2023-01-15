<template>
  <edit-complete-container
    :apiId="apiId"
    :action="action"
    :addApiModuleId="addApiModuleId"
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
  import {getAction} from "@/common/js/utils";
  import {getApiModuleId} from "@/common/js/utils";
  import {getCurrentUser} from "@/common/js/utils";
  import {getCurrentProjectID} from "@/common/js/utils";
  import {getCurrentProtocol} from "@/common/js/utils";

  export default {
    name: "ApiEditTemplate",
    components: {EditCompleteContainer},
    data() {
      return {
        apiId:String,
        action: String,
        addApiModuleId: String,
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
      this.action = getAction();
      this.addApiModuleId = getApiModuleId();

      //如果是以新窗口方式，打开API编辑页面
      if(this.action){
        //如果是编辑，或copy操作，或运行操作
        if(this.action === 'edit' || this.action === 'copy' || this.action === 'run' ){
          this.$get('/api/definition/get/' + this.apiId, (response) => {
            this.currentApi = response.data;
            this.$get("/api/module/list/" + getCurrentProjectID() + "/" + response.data.protocol, (response2) => {
              this.moduleOptions = response2.data;

              this.currentProtocol = response.data.protocol;
              this.projectId = response.data.projectId;
              this.activeTab = "api";
              this.currentApi.request = JSON.parse(this.currentApi.request);

              if(this.action === 'copy'){
                this.currentApi.name = 'copy_' + this.currentApi.name;
                this.currentApi.isCopy = true;
              }

              if(this.action === 'run'){
                this.activeTab = "test";
              }
              this.reload();
            });
          });
        }else{
          this.currentApi = {
            status: "Underway",
            method: "GET",
            userId: getCurrentUser().id,
            url: "",
            environmentId: "",
            remark: ""
          };
          this.$get('/api/module/getApiModuleById/' + this.addApiModuleId, (response2) => {
            this.moduleOptions = response2.data;
            this.currentApi.moduleId = this.addApiModuleId;
            this.currentProtocol = getCurrentProtocol();
            this.projectId = getCurrentProjectID();
            this.activeTab = "api";
            this.reload();
          });
        }
      }
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
