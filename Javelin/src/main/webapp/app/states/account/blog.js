/*
 AngularApp.config(['$stateProvider', function ($stateProvider) {
 $stateProvider.state('blogs', {
 parent: 'account',
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

 }).state('blogs.blog',{
 parent:'blogs',
 url:'/{blogId}',
 data: {
 authorities: ['ROLE_USER'],
 },
 views:{
 'content@':{
 templateUrl: 'views/account/blog.html',
 controller: 'SingleBlogController'
 }
 }

 });
 $stateProvider.state('blogs.blog.post', {
 parent: 'blog',
 url: '/{blogPostId}',
 data: {
 authorities: []
 },
 views: {
 'content@': {
 templateUrl: 'views/account/blog_post.html',
 controller: 'BlogPostController'
 }
 },
 resolve: {}

 });

 }]);
 */