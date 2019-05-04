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
   props: ['post_slug', 'author'],
  template: '<div>Post(print from props): {{ post_slug }} from {{ author }}</div>',
  beforeRouteUpdate(to, from, next) {
    this.$log.debug(`Updating from ${from.fullPath} to ${to.fullPath}`)
    next() //make sure you always call next()
  }
}
const router = new VueRouter({
   routes: [
      { path: '/', component: Home },
      { path: '/login', component: Login },
      { path: '/about', component: About },
      { path: '/post/:author/:post_slug', component: Post, props: true }
   ]
})

export default router
