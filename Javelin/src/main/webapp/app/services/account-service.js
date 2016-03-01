/**
 * Created by Stefan on 2/27/2016.
 */
AngularApp.factory('Account', ['$resource', function Account($resource) {
    return $resource('api/account', {}, {
        'get': {
            method: 'GET', params: {}, isArray: false,
            interceptor: {
                response: function (response) {
                    return response;
                }
            }
        }
    });
}]);
