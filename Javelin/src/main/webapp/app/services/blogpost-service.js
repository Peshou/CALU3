AngularApp.factory('BlogPost', ['$resource', function BlogPosts($resource) {
    return $resource('api/{id}/blog_posts/{idBlog}');

}]);
