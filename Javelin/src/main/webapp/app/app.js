/**
 * Created by Stefan on 2/26/2016.
 */
var AngularApp = angular.module('angularDemoApp',
    [
        'LocalStorageModule',
        'ngResource',
        'ngCookies',
        'ngAria',
        'ngFileUpload',
        'smart-table',
        'ui.bootstrap',
        'toastr',
        'ui.router',
        'angular-loading-bar',
        'ngAnimate'
    ]);
AngularApp.run([
    '$rootScope',
    '$location',
    '$window',
    '$http',
    '$state',
    'Auth',
    'Principal',
    function ($rootScope, $location, $window, $http, $state, Auth, Principal) {
        $rootScope.$on('$stateChangeStart', function (event, toState, toStateParams) {
            $rootScope.toState = toState;
            $rootScope.toStateParams = toStateParams;
            if (Principal.isIdentityResolved()) {
                Auth.authorize();
            }
        });
        $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
            var title = 'Demoz';
            if (toState.name != 'login' && $rootScope.previousStateName) {
                $rootScope.previousStateName = fromState.name;
                $rootScope.previousStateParams = fromParams;
            }

            if (toState.data.pageTitle) {
                title = toState.data.pageTitle;
            }
            $window.document.title = title;
        });
        $rootScope.back = function () {
            if ($state.get($rootScope.previousStateName) === null) {
                $state.go('home');
            } else {
                $state.go($rootScope.previousStateName, $rootScope.previousStateParams);
            }
        }
    }]);
AngularApp.config([
    '$stateProvider',
    '$urlRouterProvider',
    '$httpProvider',
    '$locationProvider',
 //   '$compileProvider',
    function ($stateProvider, $urlRouterProvider, $httpProvider, $locationProvider) {
        $urlRouterProvider.otherwise('/');
        $stateProvider.state('site', {
            'abstract': true,
            views: {
                'navbar@': {
                    templateUrl: 'views/navbar/navbar.html',
                    controller: 'NavbarController'
                }
            },
            resolve: {
                authorize: ['Auth',
                    function (Auth) {
                        return Auth.authorize();
                    }
                ]
            }
        });
        $httpProvider.interceptors.push('errorHandlerInterceptor');
        $httpProvider.interceptors.push('authExpiredInterceptor');
        $httpProvider.interceptors.push('authInterceptor');
       // $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|file|ftp|blob):|data:image\//);
    }]);