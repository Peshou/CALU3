AngularApp.factory('Register', ['$resource', function ($resource) {
    return $resource('api/register', {}, {});
}]);


