/**
 * Created by llaine on 30/03/15.
 */

var browserSync = require('browser-sync');
var reload = browserSync.reload;
var gulp = require("gulp");
var sass = require('gulp-sass');
var prefix = "src/main/webapp/";


// watch files for changes and reload
gulp.task('serve', function() {
    browserSync({
        server: {
            baseDir: prefix
        }
    });
});

gulp.task('sass', function () {
    gulp.src( prefix + 'scss/*.scss')
        .pipe(sass())
        .pipe(gulp.dest(prefix + 'css'));
});

gulp.watch([prefix + '*.html', prefix + 'app/**/*.html', prefix + 'app/**/*.js', prefix + 'scss/**/*.scss'], reload);

gulp.task('default', ['serve', 'sass']);