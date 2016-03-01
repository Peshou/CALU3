AngularApp
    .factory('errorHandlerInterceptor',[
        '$q','$rootScope',
        function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('angularDemoApp.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    }]);