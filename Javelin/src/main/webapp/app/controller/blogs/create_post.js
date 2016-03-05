AngularApp.controller('CreateBlogPostController', [
    '$scope', 'BlogPost', '$state', 'Principal', '$http', '$stateParams', function ($scope, BlogPost, $state, Principal, $http, $stateParams) {

        $scope.blogpost = {};
        $scope.blogpost.blogId=$stateParams.id
        console.log($stateParams);
        $scope.createPost = function () {
            $http({
                method: 'POST',
                url: "api/blogs/posts/new",
                data: $scope.blogpost

            }).then(function (response) {
               console.log(response);
            });


        };
    }]);