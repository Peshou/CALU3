/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.factory('Blog', ['$resource', function Blog($resource) {
    return $resource('api/blogs/:id');

}]);
AngularApp.factory('BlogPost', ['$resource', function BlogPost($resource) {
    return $resource('api/:id/:blog');

}]);
