
AngularApp.config(['$stateProvider',function($stateProvider){
    $stateProvider.state('blogs',{
        parent: 'account',
        url: '/blogs',
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User\'s Blogs'
        },
        views: {
            'content@': {
                templateUrl: 'views/account/blog.html',
                controller: 'BlogController'
            }
        },
        resolve: {

        }

    });
}]);