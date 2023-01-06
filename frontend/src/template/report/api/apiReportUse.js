import Vue from 'vue';
import ElementUI, {Button, ButtonGroup, Card, Col, Form, FormItem, Input, Main, Popover, Row, Table, TableColumn} from 'element-ui';
import '@/assets/theme/index.css';
import '@/common/css/menu-header.css';
import '@/common/css/main.css';
import i18n from "@/i18n/i18n";
import chart from "@/common/js/chart";
import filters from "@/common/js/filter";
import icon from "@/common/js/icon";
import message from "@/common/js/message";
import ajax from "@/common/js/ajax";
import store from "../../../store";
import VueFab from 'vue-float-action-button'
import router from "../../../business/components/common/router/router";
import JsonSchemaEditor from '../../../business/components/common/json-schema/schema/editor/index'



function apiReportUse(id, template) {
  Vue.use(ElementUI, {
    i18n: (key, value) => i18n.t(key, value)
  });

  Vue.use(VueFab);
  Vue.use(JsonSchemaEditor);
  Vue.use(Row);
  Vue.use(Col);
  Vue.use(Form);
  Vue.use(FormItem);
  Vue.use(Input);
  Vue.use(Button);
  Vue.use(ButtonGroup);
  Vue.use(chart);
  Vue.use(Main);
  Vue.use(Card);
  Vue.use(TableColumn);
  Vue.use(Table);
  Vue.use(filters);
  Vue.use(icon);
  Vue.use(message);
  Vue.use(ajax);
  Vue.use(Popover);

  // 添加全局事件总线
  Vue.prototype.$EventBus = new Vue();

  new Vue({
    el: id,
    store,
    router,
    i18n,
    render: h => h(template)
  });
}

export default apiReportUse;
