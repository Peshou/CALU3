/**
 * Created by Stefan on 2/29/2016.
 */
AngularApp.controller('UserManagementController', ['$scope', 'User', 'Principal', 'toastr', function ($scope, User, Principal, toastr) {
    Principal.identity().then(function (account) {
        $scope.currentAccount = account;
    });
    $scope.itemsByPage = 5;
    $scope.userList = User.query();
    $scope.displayedUsers = [].concat($scope.userList);

    $scope.success = null;
    $scope.error = null;

    $scope.deleteUser = function (user) {
        User.delete({username: user.username}), function (result) {
            $scope.userList = User.query();
            $scope.success = 'OK';
            $scope.error = null;
        }, function (fail) {
            console.log(fail);
            $scope.success = null;
            $scope.error = 'ERROR';
        }
    }

  /*  var editUserDialog = $modal({
        scope: $scope,
        title: 'true',
        template: 'views/modal/modal-dialog.html',
        contentTemplate: '/views/modal/impl/updateUserModal.html',
        show: false
    });
    $scope.updateUser = function (user) {
        editUserDialog.show();
        $scope.updateUsername = user.username;
    }
    $scope.update = function (username) {
        User.update({username: username}, function (result) {
            toastr.success('User updated!');
            $scope.userList = User.query();
            editUserDialog.hide();
        }, function () {
            toastr.error('course not removed! Error occurred.');
        });
    }*/

}]);