/**
 *
 * Developed as a part of a project founded by Sorsix
 *
 * @Authors
 *  Tomce Delev
 *  Dragan Sahpaski
 *  Riste Stojanov
 *
 **/
var gulp = require('gulp');
var concat = require('gulp-concat');
var templateCache = require('gulp-angular-templatecache');
var rev = require('gulp-rev-append');
var eslint = require('gulp-eslint');
var connect = require('gulp-connect');
var fs = require("fs");
var url = require('url');
var proxy = require('proxy-middleware');
var runSequence = require('run-sequence');
 var   browserSync = require('browser-sync');

var CSS_LIB = [
    'bower_components/bootstrap/dist/css/bootstrap.css',
    'bower_components/bootstrap-additions/dist/bootstrap-additions.css',
    'bower_components/components-font-awesome/css/font-awesome.min.css',
    'bower_components/angular-toastr/dist/angular-toastr.css',
    'bower_components/angular-loading-bar/build/loading-bar.css',
    'bower_components/angular-ui-select/dist/select.css',
    'bower_components/bootstrap-switch/dist/css/bootstrap3/bootstrap-switch.css',
    'bower_components/ngQuickDate/dist/ng-quick-date.css',
    'bower_components/ngQuickDate/dist/ng-quick-date-plus-default-theme.css',
    'bower_components/angular-bootstrap-colorpicker/css/colorpicker.css',
    'bower_components/angular-xeditable/dist/css/xeditable.css',
    'bower_components/AngularJS-Toaster/toaster.min.css'
];

var CSS_APP = [
    'css/main.css'
];

var JS_LIB = [
    'bower_components/jquery/dist/jquery.min.js',
    'bower_components/angular/angular.js',

    'bower_components/bootstrap/dist/js/bootstrap.min.js',
    'bower_components/bootstrap-switch/dist/js/bootstrap-switch.min.js',
    'bower_components/angular-cookies/angular-cookies.js',
    'bower_components/angular-animate/angular-animate.js',
    'bower_components/angular-aria/angular-aria.js',
    'bower_components/angular-loading-bar/build/loading-bar.js',
    'bower_components/angular-local-storage/dist/angular-local-storage.js',
    'bower_components/angular-resource/angular-resource.min.js',
    'bower_components/angular-sanitize/angular-sanitize.min.js',
    'bower_components/angular-ui-router/release/angular-ui-router.min.js',
    'bower_components/angular-toastr/dist/angular-toastr.js',
    'bower_components/angular-toastr/dist/angular-toastr.tpls.js',
    'bower_components/angular-smart-table/dist/smart-table.js',
    'bower_components/angular-cache-buster/angular-cache-buster.js',
    '/bower_components/ng-password-strength/dist/scripts/ng-password-strength.js',
    'bower_components/ng-file-upload/ng-file-upload.js',
    'bower_components/ngQuickDate/dist/ng-quick-date.js',
    'bower_components/angular-ui-select/dist/select.js',
    'bower_components/momentjs/moment.js',
    'bower_components/moment-timezone/builds/moment-timezone-with-data-2010-2020.js',
    'bower_components/AngularJS-Toaster/toaster.min.js',
    'bower_components/select2/select2.js',
    'bower_components/ngInfiniteScroll/build/ng-infinite-scroll.js',
    'bower_components/is_js/is.min.js',

    'bower_components/angular-base64/angular-base64.js',
    'bower_components/sockjs/sockjs.js',
    'bower_components/stomp-websocket/lib/stomp.js',
    'bower_components/stompie/stompie.min.js',
    'bower_components/ng-stomp/ng-stomp.min.js',
    'bower_components/angular-bootstrap-colorpicker/js/bootstrap-colorpicker-module.js',
    'bower_components/angular-bootstrap-switch/dist/angular-bootstrap-switch.min.js',
    'bower_components/angular-xeditable/dist/js/xeditable.js',
    'bower_components/angular-bootstrap/ui-bootstrap.js',
    'bower_components/angular-bootstrap/ui-bootstrap-tpls.js'


];

var JS_APP = [
    'app/**/*.js'
];


/**
 *   The location of the resources for deploy
 */
var DESTINATION = 'dest/';
/**
 * The single page initial html file. It will be altered
 * by this script.
 */
var INDEX_FILE = 'index.html';
/**
 * The name of the angular module
 */
var MODULE_NAME = 'angularDemoApp';
/**
 * The URL of the back-end API
 */
var API_URL = 'http://localhost:8080/api';
var opt = {
    app: '',
    port: 8000,
    apiPort: 8080,
    liveReloadPort: 35729
}
/**
 * Route to which the API calls will be mapped
 */
var API_ROUTE = '/api';

/**
 * Task for concatenation of the js libraries used
 * in this project
 */
gulp.task('concat_js_lib', function () {
    return gulp.src(JS_LIB) // which js files
        .pipe(concat('lib.js')) // concatenate them in lib.js
        .pipe(gulp.dest(DESTINATION)) // save lib.js in the DESTINATION folder
});

/**
 * Task for concatenation of the css libraries used
 * in this project
 */
gulp.task('concat_css_lib', function () {
    return gulp.src(CSS_LIB) // which css files
        .pipe(concat('lib.css')) // concat them in lib.css
        .pipe(gulp.dest(DESTINATION)) // save lib.css in the DESTINATION folder
});

/**
 * Task for concatenation of the js code defined
 * in this project
 */
gulp.task('concat_js_app', function () {
    return gulp.src(JS_APP)
        .pipe(concat('src.js'))
        .pipe(gulp.dest(DESTINATION))
});

/**
 * Task for concatenation of the css code defined
 * in this project
 */
gulp.task('concat_css_app', function () {
    return gulp.src(CSS_APP)
        .pipe(concat('app.css'))
        .pipe(gulp.dest(DESTINATION))
});

/**
 * Task for concatenation of the html templates defined
 * in this project
 */
gulp.task('templates', function () {
    return gulp.src('views/**/**.html') // which html files
        .pipe(
            templateCache('templates.js', { // compile them as angular templates 
                module: MODULE_NAME,        // from module MODULE_NAME 
                root: 'app'                 // of the app
            }))
        .pipe(gulp.dest(DESTINATION));
});

/**
 * Task for adding the revision as parameter
 * for cache braking
 */
gulp.task('cache-break', function () {
    return gulp.src(INDEX_FILE) // use the INDEX_FILE as source
        .pipe(rev())            // append the revision to all resources
        .pipe(gulp.dest('.'));  // save the modified file at the same destination
});

var tasks = [
    'concat_js_lib',
    'concat_css_lib',
    'concat_js_app',
    'concat_css_app',
    'templates'
];

gulp.task('build', tasks, function () {
    gulp.start('cache-break');
});



var endsWith = function (str, suffix) {
    return str.indexOf('/', str.length - suffix.length) !== -1;
};

gulp.task('serve', function() {
        var baseUri = 'http://localhost:' + opt.apiPort;
        // Routes to proxy to the backend. Routes ending with a / will setup
        // a redirect so that if accessed without a trailing slash, will
        // redirect. This is required for some endpoints for proxy-middleware
        // to correctly handle them.
        var proxyRoutes = [
            '/api'
         //   '/health','/configprops','/v2/api-docs','/swagger-ui','/configuration/security','/configuration/ui','/swagger-resources',  '/metrics','/websocket/tracker', '/dump',   '/oauth/token'
        ];

        var requireTrailingSlash = proxyRoutes.filter(function (r) {
            return endsWith(r, '/');
        }).map(function (r) {
            // Strip trailing slash so we can use the route to match requests
            // with non trailing slash
            return r.substr(0, r.length - 1);
        });

        var proxies = [
            // Ensure trailing slash in routes that require it
            function (req, res, next) {
                requireTrailingSlash.forEach(function(route){
                    if (url.parse(req.url).path === route) {
                        console.log(route);
                        res.statusCode = 301;
                        res.setHeader('Location', route + '/');
                        res.end();
                    }
                });

                next();
            }
        ].concat(
            // Build a list of proxies for routes: [route1_proxy, route2_proxy, ...]
            proxyRoutes.map(function (r) {
                var options = url.parse(baseUri + r);
                options.route = r;
                options.preserveHost = true;
                console.log(options);
                return proxy(options);
            }));

        browserSync({
            open: true,
            port: 9000,
            server: {
                baseDir: opt.app
            },
            middleware: proxies

        });
    gulp.start('watch');
});

/*gulp.task('watch', function () {
    gulp.watch('app/!**!/!**.js', ['concat_js_app', 'cache-break']);
    gulp.watch('views/!**!/!**.html', ['templates', 'cache-break']);
    gulp.watch('css/!**!/!**.css', ['concat_css_app', 'cache-break']);
    gulp.watch(['views/!**!/!**.html','app/!**!/!**.js','css/!**!/!**.css','i18n/!**','images/!**','fonts/!**','index.html']).on('change', browserSync.reload);
});*/
gulp.task('watch', function () {
    gulp.watch('app/**/**.js', ['concat_js_app', 'cache-break']);
    gulp.watch('views/**/**.html', ['templates', 'cache-break']);
    gulp.watch('css/**/**.css', ['concat_css_app', 'cache-break']);
    gulp.watch(['views/*','app/*','css/*','i18n/**','images/*','fonts/*','index.html']).on('change', browserSync.reload);
});

/*
gulp.task('serve', function () {
    connect.server({
        port: 8000,
        livereload: true,
        middleware: function (connect, opt) {
            return [
                (function () {
                    var url = require('url');
                    var proxy = require('proxy-middleware');
                    var options = url.parse(API_URL);
                    options.route = API_ROUTE;
                    console.log(options);
                    return proxy(options);
                })()
            ];
        }
    });
});


gulp.task('default', ['serve', 'watch']);
*/



gulp.task('default', function() {
    runSequence('serve');
});
