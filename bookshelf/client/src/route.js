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
   props: ['author'],
  template: '<div>Post(print from props): <router-view></router-view>(from children) from {{ author }}</div>',
  beforeRouteUpdate(to, from, next) {
    this.$log.debug(`Updating from ${from.fullPath} to ${to.fullPath}`)
    next() //make sure you always call next()
  }
}
const PostSlug = {
   props: ['post_slug'],
   template: '<span>{{post_slug}}</span>'
}
const router = new VueRouter({
   routes: [
      { path: '/', component: Home },
      { path: '/login', component: Login },
      { path: '/about', component: About },
      { path: '/post/:author', component: Post, props: true,
         children: [
            {path: ':post_slug',component: PostSlug, props: true}
         ]
      }
   ]
})

export default router
