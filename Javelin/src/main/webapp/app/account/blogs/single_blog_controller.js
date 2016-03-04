AngularApp.controller('SingleBlogController', [
    '$scope','Blog','$stateParams', function ($scope,Blog,$stateParams) {
        $scope.blog = Blog.get({id: $stateParams.blogId});
      //  console.log($stateParams);
        console.log($scope.blog);
       //console.log($scope.blog.blogPosts);
    }]);