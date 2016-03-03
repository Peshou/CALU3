AngularApp.factory('SearchService', ['$resource', function ($resource) {
    return $resource('api/search/:query', {}, {});
}]);


