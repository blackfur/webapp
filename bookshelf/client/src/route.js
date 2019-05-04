import Vue from 'vue'

import VueRouter from 'vue-router'
Vue.use(VueRouter)

const Home  = {
   template: '<div>Home</div>'
}
const Login = {
   template: '<div>Login</div>'
}
const About = {
   template: '<div>About</div>'
}
const router = new VueRouter({
   routes: [
      { path: '/', component: Home },
      { path: '/login', component: Login },
      { path: '/about', component: About }
   ]
})

export default router
