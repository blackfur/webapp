// Gulp.js configuration
// series for Synchronize
// parallel for concurrent
//const {task, src,dest, series, parallel} = require('gulp');

function img(notify){
   console.info('Proccessing img.');
   notify();
}
function html(notify){
   console.info('Proccessing html Template.');
   notify();
}
//exports.build = series(img, html);

var
  // modules
gulp = require('gulp'),

   newer = require('gulp-newer'),
  imagemin = require('gulp-imagemin'),
   htmlclean = require('gulp-htmlclean');

  // development mode?
  devBuild = (process.env.NODE_ENV !== 'production'),

  // folders
  folder = {
    src: 'src/',
    build: 'build/'
  }
;

exports.default = function (notify){
   console.log('default Task');
   notify();
}

function minify(cb) {
  // body omitted
  cb();
}


function transpile(cb) {
  // body omitted
  cb();
}

function livereload(cb) {
  // body omitted
  cb();
}

if (process.env.NODE_ENV === 'production') {
  //exports.build = series(transpile, minify);
} else {
  //exports.build = series(transpile, livereload);
}

// image processing
// $ gulp images
gulp.task('images', 
   function() { 
      var out = folder.build + 'images/'; 
      return gulp.src(folder.src + 'images/**/*') .pipe(newer(out)) .pipe(imagemin({ optimizationLevel: 5 })) .pipe(gulp.dest(out)); 
   }); 

// HTML processing
// The [images] argument states that our images task must be run before processing the HTML
gulp.task('html', ['images'], function() {
  var
    out = folder.build + 'html/',
    page = gulp.src(folder.src + 'html/**/*') .pipe(newer(out));

  // minify production code
  if (!devBuild) {
    page = page.pipe(htmlclean());
  }

  return page.pipe(gulp.dest(out));
});
