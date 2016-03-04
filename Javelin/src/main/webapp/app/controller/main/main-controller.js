
AngularApp
    .controller('MainController',['$scope','$rootScope','Principal', function ($scope,$rootScope, Principal) {
        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

    }]);

