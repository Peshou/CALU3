/*
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
*/
