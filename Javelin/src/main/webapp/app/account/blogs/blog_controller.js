AngularApp.controller('BlogController', [
    '$scope','Blog','$state', function ($scope,Blog,$state) {
        $scope.blogs = Blog.query();
        console.log($state.$stateParams);
    }]);