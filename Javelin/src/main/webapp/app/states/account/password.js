/*
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
*/
