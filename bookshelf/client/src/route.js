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
const Post = {
  template: '<div>Post: {{ $route.params.post_slug }} from {{ $route.params.author }}</div>',
  beforeRouteUpdate(to, from, next) {
    this.$log.debug(`Updating slug from ${from} to ${to}`)
    next() //make sure you always call next()
  }
}
const router = new VueRouter({
   routes: [
      { path: '/', component: Home },
      { path: '/login', component: Login },
      { path: '/about', component: About },
      { path: '/post/:author/:post_slug', component: Post }
   ]
})

export default router
