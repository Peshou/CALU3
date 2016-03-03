/*
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
*/
