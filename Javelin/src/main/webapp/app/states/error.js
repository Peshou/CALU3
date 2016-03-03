/*
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
*/
