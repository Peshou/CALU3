/**
 * Created by Intel on 02.03.2016.
 */
AngularApp.controller('SingleBlogController', [
    '$scope','Blog','$stateParams', function ($scope,Blog,$stateParams) {
        $scope.blog = Blog.get({id: $stateParams.blogId});
        console.log($stateParams);
    }]);