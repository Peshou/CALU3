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
    }]);*/
