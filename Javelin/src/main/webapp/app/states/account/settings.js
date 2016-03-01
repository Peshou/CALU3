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
