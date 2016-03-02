/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.factory('Blog', ['$resource', function Blog($resource) {
    return $resource('api/blogs');

}]);
