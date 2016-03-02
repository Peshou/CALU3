
AngularApp.config(['$stateProvider',function($stateProvider){
    $stateProvider.state('blogs.blog_post',{
        parent: 'blogs',
        url: '/{blogId}',
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'User\'s Blogs'
        },
        views: {
            'content@': {
                templateUrl: 'views/account/blog_post.html',
                controller: 'BlogPostController'
            }
        },
        resolve: {

        }

    });
}]);