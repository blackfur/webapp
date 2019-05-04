import Vue from 'vue'

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

import RouteApp from './RouteApp.vue'
import router from './route.js'

new Vue({
   router,
  render: h => h(RouteApp),
}).$mount('#app')
