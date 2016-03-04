AngularApp.controller('AllBlogController', [
    '$scope', 'Blog', '$state', 'Principal', '$http', function ($scope, Blog, $state, Principal, $http) {
        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });
        $http({
            method: 'GET',
            url: 'api/blogs/all'}).then(function success(response) {
            $scope.blogs = response;
        })
    }]);