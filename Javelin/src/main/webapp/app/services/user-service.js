AngularApp
    .factory('User', function ($resource) {
        return $resource('api/users/:username', {}, {
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' },
        });
    });
