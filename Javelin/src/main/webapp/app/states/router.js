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
            })
            .state('about', {
                parent: 'site',
                url: '/about',
                data: {
                    authorities: [],
                    pageTitle: 'About'
                },
                views: {
                    'content@': {
                        templateUrl: 'views/about/about.html'
                    }
                }
            })
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
            })
            .state('admin', {
                abstract: true,
                parent: 'site'
            })
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
                resolve: {}
            })
            .state('account', {
                abstract: true,
                parent: 'site'
            })
            .state('search', {
                parent: 'site',
                url: '/search',
                params: {searchInput: null},
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
                resolve: {}
            })
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
                resolve: {}
            })
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
                resolve: {}
            })
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
                resolve: {}
            })
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
            })
            .state('allBlogs',{
            parent: 'site',
                url:'/allBlogs',
                data:{
                    authorities: [],
                    pageTitle: 'All Blogs'
                },
                params:{
                    searchInput: null,
                },
                views:{
                    'content@':{
                        templateUrl: 'views/account/allBlogs.html',
                        controller: 'AllBlogController',
                        reloadOnSearch: false

                    }
                }
        })
            .state('blogs', {
                parent: 'site',
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
            })
            .state('blogs.blog', {
                parent: 'blogs',
                url: '/{blogId}',
                data: {authorities: []},
                views: {
                    'content@': {
                        templateUrl: 'views/account/blog.html',
                        controller: 'SingleBlogController'
                    }
                }
            })
            .state('post', {
                url: '/blogs/{id}/{blogPostId}',
                parent: 'site',
                data: {authorities: []},
                views: {
                    'content@': {
                        templateUrl: 'views/account/blog_post.html',
                        controller: 'BlogPostController'
                    }
                },
                resolve: {}

            }).
            state('createPost',{
            url:'/createPost/{id}',
            parent: 'site',
            data:{authorities: []},
            views:{
                'content@':{
                    templateUrl: 'views/account/create_post.html',
                    controller: 'CreateBlogPostController'
                }
            }

        });
    }]);


