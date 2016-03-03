/*
AngularApp
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('home', {
                parent: 'site',
                url: '/',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'views/main/main.html',
                        controller: 'MainController'
                    }
                },
                resolve: {}
            });
    }]);
AngularApp
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('error', {
                parent: 'site',
                url: '/error',
                data: {
                    authorities: [],
                    pageTitle: 'Error page!'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/error/error.html'
                    }
                },
                resolve: {}
            })
            .state('accessdenied', {
                parent: 'site',
                url: '/accessdenied',
                data: {
                    authorities: []
                },
                views: {
                    'content@': {
                        templateUrl: 'views/error/accessdenied.html'
                    }
                },
                resolve: {}
            });
    }]);

AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('admin', {
                abstract: true,
                parent: 'site'
            });
    }]);

AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('account', {
                abstract: true,
                parent: 'site'
            });
    }]);
AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('search', {
                parent: 'site',
                url: '/search',
                data: {
                    authorities: [],
                    pageTitle: 'Search Blogs'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/search.html',
                        controller: 'SearchController'
                    }
                },
                resolve: {

                }
            });
    }]);
AngularApp
    .config(['$stateProvider',
        function ($stateProvider) {
            $stateProvider
                .state('settings', {
                    parent: 'account',
                    url: '/settings',
                    data: {
                        authorities: ['ROLE_USER'],
                        pageTitle: 'Settings'
                    },
                    views: {
                        'content@': {
                            templateUrl: 'views/account/settings.html',
                            controller: 'SettingsController'
                        }
                    },
                    resolve: {

                    }
                });
        }]);
AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('register', {
                parent: 'account',
                url: '/register',
                data: {
                    authorities: [],
                    pageTitle: 'Registration'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/account/register.html',
                        controller: 'RegisterController'
                    }
                },
                resolve: {

                }
            });
    }]);
AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('password', {
                parent: 'account',
                url: '/password',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'Password'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/account/password.html',
                        controller: 'PasswordController'
                    }
                },
                resolve: {

                }
            });
    }]);
AngularApp
    .config(['$stateProvider', function ($stateProvider) {
        $stateProvider
            .state('login', {
                parent: 'account',
                url: '/login',
                data: {
                    authorities: [],
                    pageTitle: 'Sign in'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/account/login.html',
                        controller: 'LoginController'
                    }
                },
                resolve: {}
            });
    }]);
AngularApp.config(['$stateProvider', function ($stateProvider) {
    $stateProvider.state('blogs', {
        parent: 'account',
        url: '/blogs',
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User\'s Blogs'
        },
        views: {
            'content@': {
                templateUrl: 'views/account/blogs.html',
                controller: 'BlogController'
            }
        },
        resolve: {}

    }).state('blogs.blog',{
        parent:'blogs',
        url:'/{blogId}',
        data: {
            authorities: ['ROLE_USER'],
        },
        views:{
            'content@':{
                templateUrl: 'views/account/blog.html',
                controller: 'SingleBlogController'
            }
        }

    }).state('blogs.blog.post', {
        parent: 'blog',
        url: '/{blogPostId}',
        data: {
            authorities: []
        },
        views: {
            'content@': {
                templateUrl: 'views/account/blog_post.html',
                controller: 'BlogPostController'
            }
        },
        resolve: {}

    });

}]);
AngularApp
    .config(['$stateProvider',function ($stateProvider) {
        $stateProvider
            .state('user-management', {
                parent: 'admin',
                url: '/user-management',
                data: {
                    authorities: ['ROLE_ADMIN'],
                    pageTitle: 'Users'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/admin/user-management.html',
                        controller: 'UserManagementController'
                    }
                },
                resolve: {

                }
            });
    }]);
*/
