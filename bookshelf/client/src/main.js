import Vue from 'vue'
import App from './App.vue'

Vue.config.productionTip = false

import VueLogger from 'vuejs-logger'
Vue.use(VueLogger, {
  isEnabled: true,
  logLevel : 'debug',
  stringifyArguments : false,
  showLogLevel : true,
  showMethodName : false,
  separator: '|',
  showConsoleColors: true
});

new Vue({
  render: h => h(App),
}).$mount('#app')
