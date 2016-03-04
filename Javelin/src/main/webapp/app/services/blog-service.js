
AngularApp.factory('Blog', ['$resource', function Blog($resource) {
    return $resource('api/blogs/:id');
}]);
AngularApp.factory('BlogPost', ['$resource', function BlogPost($resource) {
    return $resource('api/blogs/:id/posts/:postId', {userId:'@id',postId:'@postId'},{
        'getPosts': {method: 'GET', isArray: true}
    });

}]);
