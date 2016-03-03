AngularApp.factory('SearchService', ['$resource', function ($resource) {
    return $resource('api/search', {}, {
        'getSearch': {method: 'POST', isArray: true}
    });
}]);


