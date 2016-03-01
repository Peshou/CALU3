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
