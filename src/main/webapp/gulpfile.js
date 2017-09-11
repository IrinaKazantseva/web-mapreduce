var gulp = require('gulp');
var concat = require('gulp-concat');
var gulpFilter = require('gulp-filter');
var flatten = require('gulp-flatten');
var order = require("gulp-order");
var stripDebug = require('gulp-strip-debug');
var gulpIf = require('gulp-if');
var angularTemplateCache = require('gulp-angular-templatecache');
var angularFilesort = require('gulp-angular-filesort');
var angularInjector = require('gulp-angular-injector');

var PATH_JS = '../resources/static/js';
var PATH_CSS = '../resources/static/css';

gulp.task('build', ["bundle:vendor", "bundle:app"], function() {});
gulp.task('bundle:vendor', ["bundle:vendor:js", "bundle:vendor:css"], function() {});
gulp.task('bundle:app', ["bundle:app:js", "bundle:app:css", "bundle:app:templates"], function() {});

var options = {
    mangle: false,
    compress: true
};

gulp.task('bundle:vendor:js', function () {
    console.log("Vendor JS rebuild...");
    var filterJS = gulpFilter('**/*.js', { restore: true });
    var mainBowerFiles = require('gulp-main-bower-files');
    gulp.src('./bower.json').pipe(mainBowerFiles({debugging: false}))
        .pipe(filterJS)
        .pipe(order(['**/angular.js', "*"]))
        .pipe(concat('vendor-bundle.js'))
        .pipe(gulp.dest(PATH_JS));
});

gulp.task('bundle:vendor:css', function () {
    console.log("Vendor CSS rebuild...");
    var filterJS = gulpFilter(['**/*.css'], { restore: true });
    var mainBowerFiles = require('gulp-main-bower-files');
    gulp.src('./bower.json').pipe(mainBowerFiles({debugging: false}))
        .pipe(filterJS)
        .pipe(concat('vendor-bundle.css'))
        .pipe(gulp.dest(PATH_CSS));
});

gulp.task('bundle:app:js', function () {
    console.log("App JS rebuild...");
    gulp.src('app/**/*.js')
        .pipe(angularInjector())
        .pipe(angularFilesort())
        .pipe(concat('app-bundle.js'))
        .pipe(gulp.dest(PATH_JS));
});

gulp.task('bundle:app:css', function () {
    console.log("App CSS rebuild...");
    gulp.src(['app/**/*.css'])
        .pipe(concat('app-bundle.css'))
        .pipe(gulp.dest(PATH_CSS));
});

gulp.task('bundle:app:templates', function() {
    console.log("App templates rebuild...");
    return gulp.src('app/**/*.html')
        .pipe(angularTemplateCache('templates.js', {
            module: 'app',
            root: ''
        })).pipe(gulp.dest(PATH_JS));
});

gulp.task("serve", ["build"], function() {
    gulp.watch('app/**/*.js', ['bundle:app:js']);
    gulp.watch('app/**/*.html', ['bundle:app:templates']);
    gulp.watch('app/**/*.css', ['bundle:app:css']);
});