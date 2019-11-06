const Koa = require('koa');
const app = new Koa();

const path = require('path')
const Pug = require('koa-pug')
const pug = new Pug({
  viewPath: path.resolve(__dirname, './views'),
  app: app // Binding `ctx.render()`, equals to pug.use(app)
})

app.use(async (ctx, next) => {
  if (ctx.path !== '/') return await next();
   ctx.body = 'we are home now';
});

app.use(async (ctx, next) => {
  if (ctx.path !== '/about.html') return await next();
   await ctx.render('about')
});

app.listen(3030)
