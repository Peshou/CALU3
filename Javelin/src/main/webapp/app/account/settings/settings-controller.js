AngularApp
    .controller('SettingsController', [
        '$scope',
        'Upload',
        'Principal',
        'Auth',
        function ($scope, Upload, Principal, Auth) {
            $scope.success = null;
            $scope.error = null;
            Principal.identity().then(function (account) {
                $scope.settingsAccount = copyAccount(account);
            });

            $scope.save = function () {
                Auth.updateAccount($scope.settingsAccount).then(function () {
                    $scope.error = null;
                    $scope.success = 'OK';
                    Principal.identity(true).then(function (account) {
                        $scope.settingsAccount = copyAccount(account);
                    });
                }).catch(function () {
                    $scope.success = null;
                    $scope.error = 'ERROR';
                });
            };

            $scope.submit = function () {
                if ($scope.formImg.file.$valid && $scope.file) {
                    $scope.upload($scope.file);
                }
            };
            $scope.upload = function (file) {
                Upload.upload({
                    url: 'api/account/upload',
                    data: {file: file}
                }).then(function (resp) {
                    console.log('Success ' + resp.config.data.file.name + 'uploaded. Response: ' + resp.data);
                    Principal.identity(true).then(function (account) {
                        $scope.settingsAccount = copyAccount(account);
                    });
                }, function (resp) {
                    console.log('Error status: ' + resp.status);
                }, function (evt) {
                    var progressPercentage = parseInt(100.0 * evt.loaded / evt.total);
                    console.log('progress: ' + progressPercentage + '% ' + evt.config.data.file.name);

                });
            };


            /**
             * Store the "settings account" in a separate variable, and not in the shared "account" variable.
             */
            var copyAccount = function (account) {
                return {
                    email: account.email,
                    firstName: account.firstName,
                    lastName: account.lastName,
                    username: account.username,
                    userDescription: account.userDescription,
                    userImage: account.userImage,
                    authorities: account.authorities

                }
            };
        }]);
