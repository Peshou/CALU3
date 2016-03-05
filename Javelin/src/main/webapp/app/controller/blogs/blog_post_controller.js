AngularApp.controller('BlogPostController', [
    '$scope', 'BlogPost', 'Principal', '$stateParams', '$http', function ($scope, BlogPost, Principal, $stateParams, $http) {

        Principal.identity().then(function (account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
            if ($scope.isAuthenticated()) {
                $http({
                    method: 'GET',
                    url: 'api/blogs/' + $stateParams.id + "/posts/" + $stateParams.blogPostId + "/me"
                }).then(function success(response) {
                    if (response.data == true) {
                        $scope.postMaker = true;

                    } else {
                        $scope.postMaker = false;
                    }
                });
            }
        });
        $scope.savePost = function () {
            //    console.log($stateParams.id);
            //     console.log($scope.post.id);
            console.log($scope.editPost);
            $scope.blogPost = {
                id: $scope.editPost.id,
                name: $scope.editPost.name,
                timeAdded: $scope.editPost.timeAdded,
                text: $scope.editPost.text
            };
            console.log($scope.blogPost);
            $http({
                method: 'PUT',
                url: "api/blogs/" + $stateParams.id + "/posts/" + $stateParams.blogPostId,
                data: $scope.blogPost

            }).then(function (response) {
                $scope.editMode = false;
            });
            //  BlogPost.update({id: $stateParams.id, postId: $scope.post.id}, $scope.blogPost, function (response) {
            //       $scope.editMode = false;
            // });

        };


        console.log($scope.postMaker);
        $scope.post = BlogPost.get({id: $stateParams.id, postId: $stateParams.blogPostId}, function (response) {

            $scope.editPost = copyPost(response);
            console.log($scope.editPost);
        });
        $scope.editPostMode = function () {
            $scope.editMode = true;
        };
        $scope.cancelEditPostMode = function () {
            $scope.editMode = false;
        };
        var copyPost = function (post) {
            return {
                id: post.id,
                name: post.name,
                timeAdded: post.timeAdded,
                text: post.text,
            }
        };
    }]);