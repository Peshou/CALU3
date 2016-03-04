AngularApp.controller('BlogController', [
    '$scope','Blog','$state', function ($scope,Blog,$state) {
        $scope.blogs = Blog.query();
      //  console.log($scope.blogs);
      //  console.log($state.$stateParams);
       // console.log($scope.account);
    }]);